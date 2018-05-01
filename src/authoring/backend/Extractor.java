package authoring.backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import conditions.CustomCondition;
import gui_elements.buttons.ImageChooserButton;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import scenemanager.EndCondition;

public class Extractor {
	public static String extractTextField(Node n) {
		if (n instanceof TextField) {
			return ((TextField) n).getText();
		}
		return "";
	}
	
	public static int extractTextFieldInt(Node n) {
		if (n instanceof TextField) {
			String s = ((TextField) n).getText();
			return Integer.parseInt(s);
		}
		return 0;
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
	
	public static String extractConditionName(CustomCondition c) {
		Pattern pattern = Pattern.compile("\\.(.+?)@");
		Matcher matcher = pattern.matcher(c.toString());
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
	
	public static String extractConditionName(EndCondition c) {
		Pattern pattern = Pattern.compile("\\.(.+?)@");
		Matcher matcher = pattern.matcher(c.toString());
		if (matcher.find()) {
			return matcher.group(1);
		}
		return "";
	}
}
