package gui_elements.factories;

import javafx.scene.control.TextField;

public class TextFieldFactory {
	public static TextField makeTextField(String text) {
		TextField t = new TextField();
		t.setText(text);
		return t;
	}
	
	public static TextField makeTextField(String text, String prompt) {
		TextField t = new TextField();
		t.setText(text);
		t.setPromptText(prompt);
		return t;
	}
	
	public static TextField makeTextFieldPrompt(String text) {
		TextField t = new TextField();
		t.setPromptText(text);
		return t;
	}

}
