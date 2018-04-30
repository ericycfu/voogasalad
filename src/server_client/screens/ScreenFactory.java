package server_client.screens;
/**
 * Responsible for initializing the correct ClientScreen from the given String
 * @author andrew
 */
import java.net.Socket;

import javafx.stage.Stage;
import server.RTSServerException;

public class ScreenFactory {
	private Socket dedicatedConnection;
	private Stage createdStage;
	public ScreenFactory(Socket connection, Stage primaryStage) {
		dedicatedConnection = connection;
		createdStage = primaryStage;
	}
	public ClientScreen get(String className) {
		switch(className) {
			case LobbySelectionScreen.CLASS_REF: return new LobbySelectionScreen(createdStage, dedicatedConnection);
			case CurrentLobbyScreen.CLASS_REF: return new CurrentLobbyScreen(createdStage, dedicatedConnection);
			// case "GamePlayer": return new GamePlayer(createdStage,dedicatedConnection);
			default: throw new RTSServerException("class not found");
		}
	}
}
