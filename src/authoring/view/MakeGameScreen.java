package authoring.view;

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

public class MakeGameScreen {
	public static final Color INITIAL_COLOR = Color.LIGHTSKYBLUE;

	private Stage myStage;
	private Scene myScene;
	private SplitPane myPane;
	public MakeGameScreen (Stage stage) {
		myStage = stage;
		setupScreen();
	}
	
	private void setupScreen() {
		myPane = new SplitPane(
				new MakeGameTabs(),
				new CreatedObjectsTabs());
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myScene = new Scene(myPane);
		myStage.setScene(myScene);
	}
	
}
