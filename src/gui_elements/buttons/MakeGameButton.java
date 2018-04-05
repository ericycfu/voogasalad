package gui_elements.buttons;

import authoring.MakeGameSelect;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MakeGameButton extends Button {
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
