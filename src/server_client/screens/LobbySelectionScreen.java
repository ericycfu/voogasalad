package server_client.screens;


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import server.LobbyManager;
import server_client.buttons.CreateLobbyButton;
import server_client.buttons.JoinLobbyButton;

public class LobbySelectionScreen extends ClientScreen {
	public static final String CLASS_REF = "LobbySelection";

	public static final String DATA_PATH = "data/";
	public static final String STYLE_PATH = "gui_elements/css/ServerClient.css";

	public static final String TITLE = "Server Client";
	public static final String GAME_CHOOSER_TITLE = "Choose a game to load!";
	public static final String MAP_CHOOSER_TITLE = "Choose a map to run!";
	public static final Color INITIAL_COLOR = Color.WHITE;
	public static final int INITIAL_SCENE_WIDTH = 1200;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	private FileChooser myGameChooser;
	private FileChooser myMapChooser;
	private ListView<LobbyDisplay> currentLobbies;
	private Pane myPane;
	private Scene myScene; 

	public LobbySelectionScreen(Stage primaryStage, Socket clientSocket) {
		super(primaryStage, clientSocket);

	}

	private void setupScreen() {
		myPane = new Pane();
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myPane.setId("lobby_selection_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
		myGameChooser = new FileChooser();
		myGameChooser.setTitle(GAME_CHOOSER_TITLE);
		myGameChooser.setInitialDirectory(new File(DATA_PATH));
		myMapChooser = new FileChooser();
		myMapChooser.setTitle(MAP_CHOOSER_TITLE);
		myGameChooser.setInitialDirectory(new File(DATA_PATH));
	}
	private void setupContent() {
		setUpLobbyDisplays();
		setUpJoinButton();
		setUpCreateButton();
	}



	private void setUpCreateButton() {
		CreateLobbyButton create = new CreateLobbyButton();
		myPane.getChildren().add(create);
		create.setLayoutX(280);
		create.setLayoutY(520);
		create.setOnAction(e -> {
			try {
				File chosenGame = myGameChooser.showOpenDialog(getStage());
				File chosenMap = myMapChooser.showOpenDialog(getStage());
				
			}
			catch(Exception e1) {
				
			}
		});

	}
	private void setUpJoinButton() {
		JoinLobbyButton join = new JoinLobbyButton();
		join.setOnAction(e -> {
			LobbyDisplay current = currentLobbies.getSelectionModel().getSelectedItem();
			if(current != null)
				try {
					getOutputStream().writeInt(current.getID());
				} catch (IOException e1) {
				}
		});
		myPane.getChildren().add(join);
		join.setLayoutX(620);
		join.setLayoutY(520);

	}
	private void setUpLobbyDisplays() {
		currentLobbies = new ListView<LobbyDisplay>();
		currentLobbies.setPrefSize(800, 400);
		currentLobbies.setLayoutX(200);
		currentLobbies.setLayoutY(100);
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
			return CLASS_REF;
		} catch (Exception e) {
			return CLASS_REF;
		}


	}	
}