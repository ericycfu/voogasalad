package gui_elements.buttons;

import authoring.support.AnimationHandler;
import javafx.scene.control.Button;

public class PauseButton extends Button {
	private AnimationHandler animationhandler;

	public PauseButton() {
		setupText();
		this.setOnAction(e -> animationhandler.pause());
	}

	private void setupText() {
		this.setText("Pause");
	}

	public Button getButton() {
		return this;
	}	
}