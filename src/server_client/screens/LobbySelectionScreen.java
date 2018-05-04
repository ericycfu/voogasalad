package server_client.screens;
/**
 * Handles graphical display and server communication while the player is lobbyless
 * @author andrew
 */

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Set;

import authoring.backend.MapSettings;
import game_data.Reader;
import game_engine.GameInfo;
import game_engine.GameInstance;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.alert.AlertMaker;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import scenemanager.SceneManager;
import server.GameLobby;
import server.LobbyManager;
import server_client.buttons.CreateLobbyButton;
import server_client.buttons.JoinLobbyButton;
import server_client.screens.display.GridPaneDisplay;
import server_client.screens.display.LobbyDisplay;
import server_client.screens.display.LobbyListView;

public class LobbySelectionScreen extends ClientScreen {
	public static final String CLASS_REF = "LobbySelection";

	public static final String DATA_PATH = "data/";
	public static final String STYLE_PATH = "gui_elements/css/ServerClient.css";

	
	public static final String GAME_CHOOSER_TITLE = "Choose a game to load!";
	public static final String MAP_CHOOSER_TITLE = "Choose a map to run!";
	public static final Color INITIAL_COLOR = Color.WHITE;

	public static final String IOALERTHEAD = "ERROR!";

	public static final String IOALERTBODY = "Unable to create lobby!";

	private FileChooser myGameChooser;
	private LobbyListView currentLobbies;
	private Pane myPane;
	private Scene myScene; 
	private Reader myReader = new Reader();

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
	}
	/**
	 * sets up the information and buttons that are displayed
	 */
	private void setupContent() {
		setUpLobbyDisplays();
		HBox hbox = new HBox();
		hbox.setLayoutX(150);
		hbox.setLayoutY(520);
		hbox.getChildren().add(setUpJoinButton());
		hbox.getChildren().add(setUpCreateButton());
		myPane.getChildren().add(hbox);
	}
	/**
	 * Sets up the button that allows the user to create the new Lobby
	 */
	private Button setUpCreateButton() {
		CreateLobbyButton create = new CreateLobbyButton();
		myPane.getChildren().add(create);
		create.setOnAction(e -> {
			File chosenGame = myGameChooser.showOpenDialog(getStage());
			try {
				List<Object> gameObjects = myReader.read(chosenGame.getCanonicalPath());
				GameObjectManager gom = (GameObjectManager)gameObjects.get(0);
				@SuppressWarnings("unchecked")
				Set<GameObject> possibleUnits = ((Set<GameObject>) gameObjects.get(1));
				GameInfo currentGameInfo = new GameInfo();
				for(GameObject g: possibleUnits) {
					currentGameInfo.addReferenceGameObject(g);
				}
				MapSettings mapset = (MapSettings)gameObjects.get(2);
				SceneManager sc = (SceneManager)gameObjects.get(3);
				GameInstance newGame = new GameInstance(currentGameInfo, gom, mapset, sc);
				ObjectOutputStream out = getOutputStream();
				out.writeInt(-1);
				out.writeObject(newGame);
				out.flush();
			} catch (Exception e2) {
				AlertMaker.makeAlert(IOALERTHEAD, IOALERTBODY);
			}
		});
		return create;
	}
	/**
	 * Sets up the Join Lobby button 
	 */
	private Button setUpJoinButton() {
		JoinLobbyButton join = new JoinLobbyButton();
		join.setOnAction(e -> {
			LobbyDisplay current = currentLobbies.getSelectionModel().getSelectedItem();
			if(current != null)
				try {
					ObjectOutputStream out = getOutputStream();
					out.writeInt(current.getID());
					out.flush();
				} catch (IOException e1) {
				}
		});
		return join;

	}
	/**
	 * Sets up the ListView that displays Lobby information
	 */
	private void setUpLobbyDisplays() {
		currentLobbies = new LobbyListView();
		currentLobbies.setLayoutX(GridPaneDisplay.DEFAULT_POS);
		currentLobbies.setLayoutY(GridPaneDisplay.DEFAULT_POS);
		myPane.getChildren().add(currentLobbies);

	}
	/**
	 * Sets up the Stage
	 */
	private void setupStage() {
		getStage().setScene(myScene);
		
	}

	@Override
	protected void setUp() {
		setupScreen();
		setupContent();
		setupStage();
	}

	@Override
	public String updateSelf() {
		try {
			ObjectInputStream in = getInputStream();
			if(in == null)
				return CLASS_REF;
			Object obj = in.readObject();
			if(obj instanceof GameLobby)
				return CurrentLobbyScreen.CLASS_REF;
			updateDisplay((LobbyManager) obj);
			return CLASS_REF;
		} catch (Exception e) {
			return CLASS_REF;
		}
	}	
	private void updateDisplay(LobbyManager lobbies) {
		int numLobbies = lobbies.getElements().size();
		
		Platform.runLater(() -> { 
		while(numLobbies < currentLobbies.getItems().size())
			currentLobbies.remove();
		while(numLobbies > currentLobbies.getItems().size())
			currentLobbies.add();
		for(int x = 0; x < lobbies.getElements().size(); x++)
			currentLobbies.update(x, lobbies.getElements().get(x));
		});
	}
}