package authoring.backend;

import javafx.scene.control.Label;

public class LabelFactory {
	public static Label makeLabel(String text, String styleclass) {
		Label l = new Label();
		l.setText(text);
		l.getStyleClass().add(styleclass);
		return l;
	}
}
