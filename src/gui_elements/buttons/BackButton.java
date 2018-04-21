package gui_elements.buttons;

import javafx.scene.control.Button;

public class BackButton extends Button {
	public BackButton() {
		addText();
	}
	
	private void addText() {
		this.setText("Back");
		this.getStyleClass().add("back_button");
	}
}
