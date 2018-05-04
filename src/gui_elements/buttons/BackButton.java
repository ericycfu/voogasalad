package gui_elements.buttons;

import authoring.view.StartScreen;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class BackButton extends Button {
	public BackButton(Stage stage) {
		addText();
		this.setOnAction(e -> new StartScreen(stage));
	}
	
	private void addText() {
		this.setText("Back");
		this.getStyleClass().add("back_button");
	}

	
}
