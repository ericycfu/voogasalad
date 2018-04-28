package gui_elements.buttons;

import authoring.backend.GameEntity;
import authoring.view.MakeGameScreen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewGameButton extends Button {
	public NewGameButton(int i) {
		setupText(i);
	}
	
	public NewGameButton(int i, Stage stage) {
		setupText(i);
		this.setOnAction(e -> new MakeGameScreen(stage));
	}
	
	private void setupText(int i) {
		this.setText("New Game " + i);
		this.getStyleClass().add("new_game_button");
	}
}
