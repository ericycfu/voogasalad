package server;
/**
 * This class is the Server. The Server doesn't really have a front-end but it does 
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import game_engine.GameInstance;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RTSServer {
	public static final String SERVER_IP = "10.188.208.79";
	public static final int PORT_NUMBER = 9098;
	public static final int RETRY_CONNECTION_DELAY = 10000;
	private ServerSocket myServerSocket;
	private LobbyManager myLobbyManager;
	public RTSServer() {
		initialize();
	}
	public void initialize() {
		createServer();
		listenForConnections();	 
	}
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
		System.out.println("Success!");
		try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Your current IP address : " + ip);
		}
		catch(Exception e) {}
		
		myLobbyManager = new LobbyManager();
	}
	private void listenForConnections() {
		new Thread(() -> {
            while (true) {
                try {
                    Socket socket = myServerSocket.accept();
                    System.out.println("Connection accepted");
                    ClientHandler task = new ClientHandler(this, socket);
                    Thread newThread = new Thread(task);
                    newThread.start();
                } catch (IOException e) {
                }
                cleanLobbyManager();
            }
       }).start();
	}
	private void cleanLobbyManager() {
		for(GameLobby g: myLobbyManager.getElements())
			if(g.getCurrentSize() == 0)
				myLobbyManager.removeElement(g);
	}
	public void addLobby(Socket initial, GameInstance gi) {
		myLobbyManager.addElementToManager(new GameLobby(initial, gi));
	}
	public void addToLobby(int ID, Socket s) {
		myLobbyManager.get(ID).addPlayer(s);
	}
	public GameLobby findPlayer(Socket s) {
		return myLobbyManager.find(s);
	}
	public LobbyManager getLobbyManager() {
		return myLobbyManager;
	}
}
