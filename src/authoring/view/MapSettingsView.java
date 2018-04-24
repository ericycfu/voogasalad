package authoring.view;

import authoring.backend.LabelFactory;
import authoring.backend.MapSettings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MapSettingsView extends Pane implements AuthoringView {
	
	public MapSettingsView(MapSettings settings) {
		this.getStyleClass().add(STYLE_PATH);
		initializeAll();
	}
	
	public void initializeAll() {
		initializeLabelBox();
	}
	
	public void initializeLabelBox() {
		VBox box = new VBox();
		String[] labels = {
				"Number of players", 
				"Loss Condition", 
				"Image Selection",
				"Map Width",
				"Map Height"};
		for (int i=0; i<labels.length; i++) {
			box.getChildren().addAll(newLabel(labels[i]));
		}
		box.setPadding(new Insets(100, 50, 100, 50));
		box.setSpacing(40);
		this.getChildren().add(box);
	}
	
	public Label newLabel(String text) {
		return LabelFactory.makeLabel(text, DEFAULT_LABEL);
	}
}
