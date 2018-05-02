package server_client;
/**
 * Responsible for running the Client side of the client-server connection
 * @author andrew
 */
import java.net.Socket;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import server.RTSServer;
import server_client.screens.ClientScreen;
import server_client.screens.LobbySelectionScreen;
import server_client.screens.ScreenFactory;

public class ServerClient  extends Application {
	private ClientScreen currentScreen;
	private ScreenFactory myScreenFactory;
	/**
	 * Connects the client to the server and starts the communications thread
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Socket clientSocket = null;
		do {
			try {
				clientSocket = new Socket(RTSServer.SERVER_IP, RTSServer.PORT_NUMBER);
			}
			catch(Exception e){
			}
		} while (clientSocket == null);
		myScreenFactory = new ScreenFactory(clientSocket,primaryStage);
		currentScreen = myScreenFactory.get(LobbySelectionScreen.CLASS_REF);
		start();
	}
	private void start() {
		new Thread(() -> {
			while(true) {
				String newClass = currentScreen.updateSelf();
				if(!currentScreen.getClass().getSimpleName().startsWith(newClass)) {
					System.out.println("Switching to " + newClass);
					Platform.runLater(() -> currentScreen = myScreenFactory.get(newClass));
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
