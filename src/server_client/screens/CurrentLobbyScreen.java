package server_client.screens;

import java.net.Socket;

import javafx.stage.Stage;

public class CurrentLobbyScreen extends ClientScreen {
	public static final String CLASS_REF = "CurrentLobby";
	public CurrentLobbyScreen(Stage primaryStage, Socket clientSocket) {
		super(primaryStage, clientSocket);
	}

	@Override
	protected void setUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String updateSelf() {
		// TODO Auto-generated method stub
		return null;
	}

}
