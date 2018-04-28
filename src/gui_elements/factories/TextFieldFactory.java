package gui_elements.factories;

import javafx.scene.control.TextField;

public class TextFieldFactory {
	public static TextField makeTextField(String text) {
		TextField t = new TextField();
		t.setText(text);
		return t;
	}
}
