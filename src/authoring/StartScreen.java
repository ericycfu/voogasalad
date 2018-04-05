package authoring;

import gui_elements.buttons.MakeGameButton;
import gui_elements.buttons.PlayGameButton;
import gui_elements.texts.StartScreenText;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StartScreen {
	private static final Color INITIAL_COLOR = Color.WHITE;
	private static final int INITIAL_SCENE_WIDTH = 1200;
	private static final int INITIAL_SCENE_HEIGHT = 700;
	private static final String TITLE = "RapTiltSwagger";
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
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myScene = new Scene(myPane);
	}
	
	private void setupContent() {
		VBox box = new VBox();
		box.getChildren().addAll(
				new StartScreenText(), 
//				new MakeGameButton(),
				new MakeGameButton(myStage),
				new PlayGameButton());
		box.setAlignment(Pos.CENTER);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle(TITLE);
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.show();
	}	
}