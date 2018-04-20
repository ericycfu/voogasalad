package server;
/**
 * This class is the Server. The Server doesn't really have a front-end but it does 
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import game_engine.GameInstance;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RTSServer {
	public static final String SERVER_IP = "10.197.12.152";
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
				alert.setHeaderText("");
				alert.setContentText("Unable to listen in on port " + PORT_NUMBER);
				alert.show();
				try {
					Thread.sleep(RETRY_CONNECTION_DELAY);
				} catch (InterruptedException e1) {}
			}
		}
		myLobbyManager = new LobbyManager();
	}
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
}
