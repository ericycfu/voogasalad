package authoring.view;

import gui_elements.buttons.BackButton;
import gui_elements.buttons.NewGameButton;
import gui_elements.texts.MakeGameText;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MakeGameSelect implements AuthoringView {
	public static final String STYLE_PATH = "gui_elements/css/AuthoringView.css";
	public static final Color INITIAL_COLOR = Color.ALICEBLUE;
	public static final int NUM_GAMES = 6;
	private BorderPane myPane;
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
		myPane.setId("start_screen");
		// # -> setId
		// . -> getStyleClass.add
		Scene scene = new Scene(myPane);
		scene.getStylesheets().add(STYLE_PATH);
		myStage.setScene(scene);
	}
	
	private void setupTitle() {
		HBox box = new HBox();
		box.getChildren().addAll(new MakeGameText(), new BackButton());
		myPane.setTop(box);
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
	
}
