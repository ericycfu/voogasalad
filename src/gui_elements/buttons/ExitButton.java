package gui_elements.buttons;

import authoring.support.AnimationHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExitButton extends Button {

	private AnimationHandler animationhandler;

	public ExitButton() {
		setupText();
	}

	public ExitButton(Stage stage) {
		setupText();
		this.setOnAction(e -> animationhandler.exit());
	}

	private void setupText() {
		this.setText("Exit");
	}
	
	public Button getButton() {
		return this;
	}
}