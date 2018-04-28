package server_client.screens;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
	private ImageView background;
	private ListView<String> teamList;
	public CurrentLobbyScreen(Stage primaryStage, Socket clientSocket) {
		super(primaryStage, clientSocket);
	}

	@Override
	protected void setUp() {
		setUpScene();
		setUpContent();
		
	}

	private void setUpContent() {
		LeaveLobbyButton leave = new LeaveLobbyButton(getOutputStream());
		myPane.getChildren().add(leave);
		leave.setLayoutX(620);
		leave.setLayoutY(520);
		Button change = new ChangeTeamButton();
		change.setOnAction(e -> {
			try {
				getOutputStream().writeObject("Change");
				getOutputStream().writeInt(0);
			} catch (IOException e1) {
			}
	});
		myPane.getChildren().add(change);
		change.setLayoutX(20);
		change.setLayoutY(520);
		Button play = new PlayButton(getOutputStream());
		myPane.getChildren().add(play);
		change.setLayoutX(320);
		change.setLayoutY(520);
		background = new ImageView();
		myPane.getChildren().add(background);
		background.setLayoutX(800);
		background.setLayoutY(200);
		background.setFitHeight(300);
		background.setFitWidth(300);
		
	}

	private void setUpScene() {
		myPane = new Pane();
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myPane.setId("lobby_selection_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
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
			if(obj instanceof String) {
				switch((String)(obj)) {
					case "Leave": return LobbySelectionScreen.CLASS_REF;
					case "Start": return null;//todo return GamePlayer thing
				}
			}
			GameLobby currentLobby = (GameLobby) obj;
			System.out.println("1");
			return CLASS_REF;
		} catch (Exception e) {
			System.out.println("2");
			return CLASS_REF;
		}
	}

}
