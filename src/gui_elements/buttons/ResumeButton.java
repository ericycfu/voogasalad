package gui_elements.buttons;

import authoring.backend.AnimationHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ResumeButton extends Button {
	private AnimationHandler animationhandler;

	public ResumeButton() {
		setupText();
	}

	public ResumeButton(Stage stage) {
		setupText();
		this.setOnAction(e -> animationhandler.play());
	}

	private void setupText() {
		this.setText("Resume");
	}
	
	public Button getButton() {
		return this;
	}
}