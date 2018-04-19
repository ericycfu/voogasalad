package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MakeGameScreen implements AuthoringView {
	public static final Color INITIAL_COLOR = Color.LIGHTSKYBLUE;

	private Stage myStage;
	private Scene myScene;
	private SplitPane myPane;
	private GameEntity myGame;
	private AuthoringController myAuthoringController;
	public MakeGameScreen (Stage stage, GameEntity game) {
		myStage = stage;
		myGame = game;
		setupScreen();
	}
	
	private void setupScreen() {
		myAuthoringController = new AuthoringController();
//		myPane = new SplitPane(
//				new MakeGameTabs(myAuthoringController, myGame),
//				new CreatedObjectsTabs(myAuthoringController, myGame));
//		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		HBox box = new HBox();
//		box.setId("start_screen");
		box.getChildren().addAll(
				new MakeGameTabs(myAuthoringController, myGame),
				new CreatedObjectsTabs(myAuthoringController, myGame));
		box.setPadding(new Insets(10, 10, 10, 10));
		box.setSpacing(10);
		myScene = new Scene(box);
		myScene.getStylesheets().add(STYLE_PATH);
		myStage.setScene(myScene);
	}
	
}
