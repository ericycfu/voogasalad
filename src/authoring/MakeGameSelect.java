package authoring;

import gui_elements.buttons.NewGameButton;
import gui_elements.texts.MakeGameText;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MakeGameSelect {
	public static final Color INITIAL_COLOR = Color.ALICEBLUE;
	public static final int NUM_GAMES = 6;
	private BorderPane myPane;
	private Scene myScene;
	private Stage myStage;
	public MakeGameSelect(Stage stage) {
		myStage = stage;
		setupScreen();
		setupTitle();
		setupGameSelect();
	}
	
	private void setupScreen() {
		myPane = new BorderPane();
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myScene = new Scene(myPane);
		myStage.setScene(myScene);
	}
	
	private void setupTitle() {
		myPane.setTop(new MakeGameText());
	}
	
	private void setupGameSelect() {
		HBox row1 = new HBox();
		row1.setAlignment(Pos.CENTER);
		for (int i=0; i<NUM_GAMES/2; i++) {
			row1.getChildren().add(new NewGameButton(i+1, myStage));
		}
		HBox row2 = new HBox();
		row2.setAlignment(Pos.CENTER);
		for (int j=NUM_GAMES/2; j<NUM_GAMES; j++) {
			row2.getChildren().add(new NewGameButton(j+1, myStage));
		}
		VBox box = new VBox();
		box.getChildren().addAll(row1, row2);
		box.setAlignment(Pos.CENTER);
		myPane.setCenter(box);
	}
	
	private Button newGameSelect(int index) {
		Button b = new Button("Testing " + index);
		return b;
	}
}
