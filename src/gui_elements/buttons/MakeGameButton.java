package gui_elements.buttons;

import authoring.MakeGameSelect;
import game_view.buttons.ImageButton;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MakeGameButton extends ImageButton {
	public MakeGameButton() {
		setupText();
	}
	
	public MakeGameButton(Stage stage) {
		setupText();
		this.setOnAction(e -> new MakeGameSelect(stage));
	}
	
	private void setupText() {
		this.setText("Make Game");

	}
	
	
}
