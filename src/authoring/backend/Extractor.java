package authoring.backend;

import gui_elements.buttons.ImageChooserButton;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class Extractor {
	public static String extractTextField(Node n) {
		if (n instanceof TextField) {
			return ((TextField) n).getText();
		}
		return "";
	}
	
	public static String extractComboBox(Node n) {
		if (n instanceof ComboBox) {
			String symbol = (String) ((ComboBox) n).getValue();
			return symbol;
		}
		return "";
	}
	
	public static String extractImagePath(Node n) {
		if (n instanceof ImageChooserButton) {
			return ((ImageChooserButton) n).getFilePath();
		}
		return "";
	}
}
