package server_client;

import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;
import server.RTSServer;

public class ServerClient  extends Application {
	//todo figure out the backend for this crap

	@Override
	public void start(Stage primaryStage) throws Exception {
		Socket clientSocket = new Socket(RTSServer.SERVER_IP, RTSServer.PORT_NUMBER);
		new LobbySelectionScreen(primaryStage,clientSocket);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
