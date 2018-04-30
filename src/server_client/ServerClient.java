package server_client;

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
	//todo figure out the backend for this crap AND THE FRONT END FEELSGOODMAN
	private ScreenFactory myScreenFactory;
	@Override
	public void start(Stage primaryStage) throws Exception {
		Socket clientSocket = null;
		System.out.println(System.currentTimeMillis());
		do {
			try {
				clientSocket = new Socket(RTSServer.SERVER_IP, RTSServer.PORT_NUMBER);
			}
			catch(Exception e){
				System.out.println("Fail");
			}
		} while (clientSocket == null);
		System.out.println(System.currentTimeMillis());
		myScreenFactory = new ScreenFactory(clientSocket,primaryStage);
		currentScreen = myScreenFactory.get(LobbySelectionScreen.CLASS_REF);
		new Thread(() -> {
			while(true) {
				String newClass = currentScreen.updateSelf();
				if(!currentScreen.getClass().getSimpleName().startsWith(newClass)) {
					Platform.runLater(() -> currentScreen = myScreenFactory.get(newClass));
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
