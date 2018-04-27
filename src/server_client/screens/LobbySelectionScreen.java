package server_client.screens;


import java.io.ObjectInputStream;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import server.LobbyManager;

public class LobbySelectionScreen extends ClientScreen {
	public static final String CLASS_REF = "LobbySelection";
	
	public static final String STYLE_PATH = "gui_elements/css/ServerClient.css";

	public static final String TITLE = "Server Client";
	public static final Color INITIAL_COLOR = Color.WHITE;
	public static final int INITIAL_SCENE_WIDTH = 1200;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	private ListView<LobbyDisplay> currentLobbies;
	private StackPane myPane;
	private Scene myScene; 
	

	public LobbySelectionScreen(Stage primaryStage, Socket clientSocket) {
		super(primaryStage, clientSocket);
		
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myPane.setId("lobby_selection_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
		
	}
	private void setupContent() {
		currentLobbies = new ListView<LobbyDisplay>();
		currentLobbies.setMaxSize(800, 400);
		myPane.getChildren().add(currentLobbies);
	}
	
	
	private void setupStage() {
		getStage().setScene(myScene);
		getStage().setTitle(TITLE);
		getStage().setWidth(INITIAL_SCENE_WIDTH);
		getStage().setHeight(INITIAL_SCENE_HEIGHT);
		getStage().setResizable(false);
		getStage().show();
	}

	@Override
	protected void setUp() {
		setupScreen();
		setupContent();
		setupStage();
	}

	@Override
	public String updateSelf() {
		Object obj;
		try {
			ObjectInputStream in = getInputStream();
			if(in == null)
				return CLASS_REF;
			obj = in.readObject();
			System.out.println(obj.getClass().getName());
			if(obj instanceof String)
				return CurrentLobbyScreen.CLASS_REF;
			LobbyManager lobbies = (LobbyManager) obj;
			int numLobbies = lobbies.getElements().size();
			while(numLobbies < currentLobbies.getItems().size()) {
				currentLobbies.getItems().remove(0);
			}
			while(numLobbies > currentLobbies.getItems().size()) {
				currentLobbies.getItems().add(new LobbyDisplay());
			}
			for(int x = 0; x < lobbies.getElements().size(); x++) {
				currentLobbies.getItems().get(x).update(lobbies.getElements().get(x));
			}
			System.out.println("1");
			return CLASS_REF;
		} catch (Exception e) {
			System.out.println("2");
			return CLASS_REF;
		}
		
		
	}	
}