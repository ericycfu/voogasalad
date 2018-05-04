package server_client;
import java.io.IOException;
/**
 * Responsible for running the Client side of the client-server connection
 * @author andrew
 */
import java.net.Socket;

import game_player.alert.AlertMaker;
import javafx.application.Platform;
import javafx.stage.Stage;
import server.RTSServer;
import server_client.screens.ClientScreen;
import server_client.screens.LobbySelectionScreen;
import server_client.screens.ScreenFactory;

public class ServerClient {
	public static final String TITLE = "Server Client";
	public static final String DISCONNECT_TITLE = "ERROR!";
	public static final String DISCONNECT_BODY = "Error occured when closing server client";
	public static final String INITIALIZATION_BODY = "Cannot connect to server; try again!";
	public static final int INITIAL_SCENE_WIDTH = 1200;
	public static final int INITIAL_SCENE_HEIGHT = 800;
	private ClientScreen currentScreen;
	private ScreenFactory myScreenFactory;
	private Socket clientSocket;
	private boolean running;
	/**
	 * Connects the client to the server and starts the communications thread
	 */
	public ServerClient(Stage myStage) {
		running = true;
		setUp(myStage);
	}
	public void setUp(Stage primaryStage) {
		clientSocket = null;
		try {
			clientSocket = new Socket(RTSServer.DEFAULT_SERVER_IP, RTSServer.PORT_NUMBER);
			myScreenFactory = new ScreenFactory(clientSocket,primaryStage);
			currentScreen = myScreenFactory.get(LobbySelectionScreen.CLASS_REF);
			setUpStage(primaryStage);
			start();
		}
		catch(Exception e){
			new AlertMaker(DISCONNECT_TITLE,INITIALIZATION_BODY);
		}

	}
	private void start() {
		new Thread(() -> {
			while(running) {
				String newClass = currentScreen.updateSelf();
				if(!currentScreen.getClass().getSimpleName().startsWith(newClass)) {
					Platform.runLater(() -> {currentScreen = myScreenFactory.get(newClass);});
					try {
						Thread.sleep(2500);
					} catch (InterruptedException e) {
					}
				}
			}
			try {
				clientSocket.close();
			} catch (IOException e) {
				new AlertMaker(DISCONNECT_TITLE,DISCONNECT_BODY);
			}
		}).start();
	}

	private void setUpStage(Stage primaryStage) {
		primaryStage.setTitle(TITLE);
		primaryStage.setWidth(INITIAL_SCENE_WIDTH);
		primaryStage.setHeight(INITIAL_SCENE_HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {running = false;});
		primaryStage.show();
	}
}
