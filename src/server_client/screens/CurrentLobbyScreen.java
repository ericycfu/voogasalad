package server_client.screens;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import server.GameLobby;
import server.LobbyManager;
import server_client.buttons.ChangeTeamButton;
import server_client.buttons.LeaveLobbyButton;
import server_client.buttons.PlayButton;

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

	private void setUpContent() {
		teamList = new ListView<>();
		myPane.getChildren().add(teamList);
		teamList.setPrefSize(800, 400);
		teamList.setLayoutX(200);
		teamList.setLayoutY(100);
		teamList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TeamDisplay>() {
			@Override
			public void changed(ObservableValue<? extends TeamDisplay> arg0, TeamDisplay arg1, TeamDisplay arg2) {
			}
		});
		setUpText();
		
	}
	private void setUpText() {
		playerID = new Text();
		playerID.setLayoutX(100);
		playerID.setLayoutY(50);
		playerID.setFill(Color.WHITE);
		playerID.setFont(new Font("Verdana",40));
		myPane.getChildren().add(playerID);
		currentTeam = new Text();
		currentTeam.setLayoutX(600);
		currentTeam.setLayoutY(50);
		currentTeam.setFill(Color.WHITE);
		currentTeam.setFont(new Font("Verdana",40));
		myPane.getChildren().add(currentTeam);
		
	}

	private void setUpButtons() {
		LeaveLobbyButton leave = new LeaveLobbyButton(getOutputStream());
		myPane.getChildren().add(leave);
		leave.setLayoutX(700);
		leave.setLayoutY(520);
		Button change = new ChangeTeamButton();
		change.setOnAction(e -> {
			TeamDisplay current = teamList.getSelectionModel().getSelectedItem();
			if(current != null)
				try {
					System.out.println("Changing");
					ObjectOutputStream out = getOutputStream();
					out.writeObject("Change");
					out.writeInt(current.getID());
					out.flush();
					System.out.println(current.getID());
				} catch (IOException e1) {
				}
		});
		myPane.getChildren().add(change);
		change.setLayoutX(60);
		change.setLayoutY(520);
		Button play = new PlayButton(getOutputStream());
		myPane.getChildren().add(play);
		play.setLayoutX(380);
		play.setLayoutY(520);
	}

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
			if(in == null)
				return CLASS_REF;
			obj = in.readObject();
			if(obj instanceof String || obj instanceof LobbyManager) {
				return LobbySelectionScreen.CLASS_REF;
			}
			GameLobby lobby = (GameLobby) obj;
			Platform.runLater(() -> {
			while(lobby.getNumTeams() > teamList.getItems().size())
				teamList.getItems().add(new TeamDisplay(teamList.getItems().size() + 1));
			for(int x = 0; x < lobby.getNumTeams(); x++) {
				teamList.getItems().get(x).update(lobby);
			}
			});
			currentTeam.setText("Current team: " + in.readInt());
			playerID.setText("Player Number: "+ in.readInt());
			return CLASS_REF;
		} catch (Exception e) {
			return CLASS_REF;
		}
	}

}
