package authoring.view;

import javax.swing.JFileChooser;

import authoring.backend.ButtonFactory;
import authoring.backend.LabelFactory;
import authoring.backend.MapSettings;
import gui_elements.buttons.ImageChooserButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MapSettingsView extends Pane implements AuthoringView {
	
	public MapSettingsView(MapSettings settings) {
		this.getStyleClass().add(STYLE_PATH);
		initializeAll();
	}
	
	private void initializeAll() {
		initializeTitle();
		
		HBox box = new HBox();
		initializeLabelBox(box);
		initializeContent(box);
		box.setPadding(new Insets(50, 50, 0, 50));
		this.getChildren().add(box);
		
		setupButton();
	}
	
	private void initializeTitle() {
		this.getChildren().add(LabelFactory.makeLabel("Game Settings", DEFAULT_TITLE));
	}
	
	private void initializeLabelBox(HBox rootBox) {
		VBox box = new VBox();
		String[] labels = {
				"Number of players:", 
				"Loss condition:", 
				"Image selection:",
				"Map width:",
				"Map height:"};
		for (int i=0; i<labels.length; i++) {
			box.getChildren().addAll(newLabel(labels[i]));
		}
		standardBox(box);
		box.setSpacing(40);
		rootBox.getChildren().add(box);

	}
	
	private void initializeContent(HBox rootBox) {
		VBox box = new VBox();
		box.getChildren().addAll(
				new TextField(),
				new ComboBox(),
				new ImageChooserButton(),
				new TextField(),
				new TextField());
		standardBox(box);
		box.setSpacing(32);
		rootBox.getChildren().add(box);
	}
	
	private void setupButton() {
		HBox box = new HBox();
		Button saveButton = ButtonFactory.makeButton("Save", e -> saveConditions());
		box.getChildren().addAll(saveButton);
//		box.setPadding(new Insets(0, 0, 0, 0));
		this.getChildren().add(box);
	}
	
	private void saveConditions() {
		
	}
	
	private Label newLabel(String text) {
		return LabelFactory.makeLabel(text, DEFAULT_LABEL);
	}
	
	private void standardBox(VBox box) {
		box.setPadding(new Insets(0, 25, 0, 25));
	}
}
