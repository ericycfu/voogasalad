package server;
/**
 * This class is the Server. The Server doesn't really have a front-end other than 1 alert but it does manage all the lobbies and all client connections
 * Server logging will come at a future date
 * @author andrew
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game_engine.GameInstance;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RTSServer {
	public static final String SERVER_IP = "10.197.114.35";
	public static final int PORT_NUMBER = 9098;
	public static final int RETRY_CONNECTION_DELAY = 10000;
	private ServerSocket myServerSocket;
	private LobbyManager myLobbyManager;
	public RTSServer() {
		initialize();
	}
	/**
	 * Initializes the server and starts server threads
	 */
	public void initialize() {
		createServer();
		listenForConnections();	 
	}
	/**
	 * Initializes the ServerSocket and LobbyManager
	 */
	private void createServer() {
		myServerSocket = null;
		while(myServerSocket == null) {
			try {
				myServerSocket = new ServerSocket(PORT_NUMBER);
			}
			catch(IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Error");
				alert.setContentText("Unable to listen in on port " + PORT_NUMBER);
				alert.show();
				try {
					Thread.sleep(RETRY_CONNECTION_DELAY);
				} catch (InterruptedException e1) {}
			}
		}
		
		myLobbyManager = new LobbyManager();
	}
	/**
	 * Starts and runs the thread that listens for clients and starts the ClientHandler
	 */
	private void listenForConnections() {
		new Thread(() -> {
            while (true) {
                try {
                    Socket socket = myServerSocket.accept();
                    ClientHandler task = new ClientHandler(this, socket);
                    Thread newThread = new Thread(task);
                    newThread.start();
                } catch (IOException e) {
                }
            }
       }).start();
	}
	/**
	 * Clears LobbyManager of empty lobbies
	 */
	public void cleanLobbyManager() {
		for(GameLobby g: myLobbyManager.getElements())
			if(g.getCurrentSize() == 0)
				myLobbyManager.removeElement(g);
	}
	/**
	 * Adds a new Lobby with the given host and the loaded GameInstance
	 * @param initial Player who creates the Lobby
	 * @param gi game to create lobby from
	 */
	public void addLobby(Socket initial, GameInstance gi) {
		myLobbyManager.addElementToManager(new GameLobby(initial, gi));
	}
	/**
	 * Adds the player to the Lobby with the given ID
	 * @param ID ID of lobby to add to
	 * @param s player to add
	 */
	public void addToLobby(int ID, Socket s) {
		myLobbyManager.get(ID).addPlayer(s);
	}
	/**
	 * Find the player in the LobbyManager
	 * @param s player to find
	 * @return Lobby containing the player
	 */
	public GameLobby findPlayer(Socket s) {
		return myLobbyManager.find(s);
	}
	public LobbyManager getLobbyManager() {
		return myLobbyManager;
	}
}
