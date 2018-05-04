package server_client.screens;
/**
 * This screen denotes how the Client displays and communicates when the player is in the lobby
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game_player.GamePlayer;
import gui_elements.factories.ButtonFactory;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.GameLobby;
import server.LobbyManager;
import server.communications_handler.LobbyHandler;
import server_client.buttons.ChangeTeamButton;
import server_client.buttons.LeaveLobbyButton;
import server_client.screens.display.GridPaneDisplay;
import server_client.screens.display.TeamDisplay;
import server_client.screens.display.TeamListView;

public class CurrentLobbyScreen extends ClientScreen {
	public static final String CLASS_REF = "CurrentLobby";
	public static final Color INITIAL_COLOR = Color.WHITE;
	public static final String STYLE_PATH = "gui_elements/css/ServerClient.css";
	private Pane myPane;
	private Scene myScene;
	private Text currentTeam;
	private Text playerID;
	private ListView<TeamDisplay> teamList;
	public CurrentLobbyScreen(Stage primaryStage, Socket clientSocket) {
		super(primaryStage, clientSocket);
	}

	@Override
	protected void setUp() {
		setUpScene();
		setUpButtons();
		setUpContent();
		
	}
	/** Sets up the TeamList
	 * 
	 */
	private void setUpContent() {
		teamList = new TeamListView();
		myPane.getChildren().add(teamList);
		teamList.setLayoutX(GridPaneDisplay.DEFAULT_POS);
		teamList.setLayoutY(2.*GridPaneDisplay.DEFAULT_POS);
		setUpText();
		
	}
	/**
	 * Sets up the text info on the Screen
	 */
	private void setUpText() {
		VBox textBox = new VBox(10);
		playerID = new Text();
		playerID.setId("lobby_team_text");
		currentTeam = new Text();
		currentTeam.setId("lobby_team_text");
		
		textBox.getChildren().add(currentTeam);
		textBox.getChildren().add(playerID);
		myPane.getChildren().add(textBox);
		
	}
	/**
	 * Sets up all buttons on the screen
	 */
	private void setUpButtons() {
		HBox buttonBox = new HBox(20);
		myPane.getChildren().add(buttonBox);
		buttonBox.setLayoutX(50);
		buttonBox.setLayoutY(520);
		LeaveLobbyButton leave = new LeaveLobbyButton(getOutputStream());
		
		Button change = new ChangeTeamButton();
		change.setOnAction(e -> {
			TeamDisplay current = teamList.getSelectionModel().getSelectedItem();
			if(current != null)
				try {
					ObjectOutputStream out = getOutputStream();
					out.writeObject("Change");
					out.writeInt(current.getID());
					out.flush();
				} catch (IOException e1) {
				}
		});
		myPane.getChildren().add(change);
		Button play = ButtonFactory.makeButton("Play Game", e -> {
			try {
				ObjectOutputStream out = getOutputStream();
				out.writeObject(LobbyHandler.START_GAME);
				out.flush();
			} catch (IOException e1) {
			}},"lobby_selection_button");
		myPane.getChildren().add(play);
		buttonBox.getChildren().add(change);
		buttonBox.getChildren().add(play);
		buttonBox.getChildren().add(leave);
	}
	/**
	 * Sets up the pane and Scene and switches the Screen
	 */
	private void setUpScene() {
		myPane = new Pane();
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myPane.setId("lobby_selection_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
		getStage().setScene(myScene);
	}

	@Override
	public String updateSelf() {
		Object obj;
		try {
			ObjectInputStream in = getInputStream();
			obj = in.readObject();
			if(obj.equals(LobbyHandler.LEAVE_MESSAGE) || obj instanceof LobbyManager) {
				return LobbySelectionScreen.CLASS_REF;
			}
			if(obj.equals("Start")) {
				ObjectOutputStream out = getOutputStream();
				out.writeObject(LobbyHandler.ENTER_GAME);
				out.flush();
				return GamePlayer.CLASS_REF;
			}
			updateDisplay((GameLobby) obj, in.readInt(), in.readInt());
			return CLASS_REF;
		} catch (Exception e) {
			return CLASS_REF;
		}
	}
	private void updateDisplay(GameLobby lobby, int team, int player) {
		Platform.runLater(() -> {
			while(lobby.getNumTeams() > teamList.getItems().size())
				teamList.getItems().add(new TeamDisplay(teamList.getItems().size() + 1));
			for(int x = 0; x < lobby.getNumTeams(); x++) {
				if(teamList.getItems().get(x).getNumPlayers() != lobby.getNumPlayers(x + 1))
					teamList.getItems().get(x).update(lobby);
			}
			currentTeam.setText("Current Team: " + team);
			playerID.setText("Player #: " + player);
			});
	}

}
