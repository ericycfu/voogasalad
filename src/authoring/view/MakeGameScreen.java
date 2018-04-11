package authoring.view;

import authoring.backend.AuthoringController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MakeGameScreen implements AuthoringView {
	public static final Color INITIAL_COLOR = Color.LIGHTSKYBLUE;

	private Stage myStage;
	private Scene myScene;
	private SplitPane myPane;
	private AuthoringController myAuthoringController;
	public MakeGameScreen (Stage stage) {
		myStage = stage;
		setupScreen();
	}
	
	private void setupScreen() {
		myAuthoringController = new AuthoringController();
		myPane = new SplitPane(
				new MakeGameTabs(myAuthoringController),
				new CreatedObjectsTabs(myAuthoringController));
		
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myScene = new Scene(myPane);
		myStage.setScene(myScene);
	}
	
}
