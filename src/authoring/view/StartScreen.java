package authoring.view;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import game_object.GameObjectManager;
import game_player.GamePlayer;
import game_player.SinglePlayerGamePlayer;
import gui_elements.texts.StartScreenText;
import gui_elements.factories.ButtonFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import server_client.ServerClient;

public class StartScreen implements AuthoringView {
	
	public static final String STYLE_PATH = "gui_elements/css/AuthoringView.css";
	public static final String TITLE = "Rap Tilt Swagger";
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    
	private Stage myStage;
	private StackPane myPane;
	private Scene myScene; 

	public StartScreen(Stage primaryStage) {
		myStage = primaryStage;
		setupScreen();
		setupContent();
		setupStage();
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(DEFAULT_BACKGROUND, null, null)));
		myPane.setId("start_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupContent() {
		VBox box = new VBox();
		box.getChildren().addAll(
				new StartScreenText(),
				ButtonFactory.makeButton("Make Game", e -> new MakeGameSelect(myStage), "image_button"),
				//new MakeGameButton(myStage), 
				ButtonFactory.makeButton("Load Game", e -> {FileChooser myFC = new FileChooser();
															File myFile = myFC.showOpenDialog(new Stage());
															try {
																new MakeGameScreen(myStage, myFile);
															} catch (ClassNotFoundException | IOException e2) {
																
															}
															
				}, "image_button"),
				ButtonFactory.makeButton("Play Game", e -> new ServerClient(new Stage()), "image_button"),
				ButtonFactory.makeButton("Single Player", e -> singlePlayerGame(), "image_button"));
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(SPACING_SMALL);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle(TITLE);
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
	
	private void singlePlayerGame() {
		Stage mystage = new Stage();
		GameObjectManager myGom = new GameObjectManager();
		SinglePlayerGamePlayer myGP = new SinglePlayerGamePlayer(myGom, new HashSet<>());
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                act -> step(SECOND_DELAY, myGP, myGom));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
		myGP.setTimeline(animation);
		Scene scene = myGP.getScene();
		mystage.setScene(scene);
		mystage.show();
	}
	
	private void step(double timeElapsed, SinglePlayerGamePlayer gp, GameObjectManager gom) {
		gp.update();
		gom.runGameObjectLoop(SECOND_DELAY);
	}
}