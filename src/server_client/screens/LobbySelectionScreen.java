package server_client.screens;


import java.io.IOException;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LobbySelectionScreen extends ClientScreen{
	public static final String CLASS_REF = "LobbySelection";
	
	public static final String STYLE_PATH = "gui_elements/css/ServerClient.css";

	public static final String TITLE = "Client";
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
		setUpDisplay();
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	private void setUpDisplay() {
		
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
		setupStage();
	}

	@Override
	public String updateSelf() {
		Object obj;
		try {
			obj = getInputStream().readObject();
			if(obj instanceof String)
				return CurrentLobbyScreen.CLASS_REF;
			return CLASS_REF;
		} catch (Exception e) {
			return CLASS_REF;
		}
		
	}	
}