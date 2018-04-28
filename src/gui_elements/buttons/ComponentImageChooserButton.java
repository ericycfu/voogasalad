package gui_elements.buttons;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import gui_elements.labels.ComponentImageChoiceTextLabel;
import gui_elements.labels.MainLabel;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class ComponentImageChooserButton extends MainButton {

	private static final String FILENAME = "component_image_chooser_button.properties";
	private FileChooser filechooser;
	private File file;
	private ComponentImageChoiceTextLabel component_image_choice_text_label;
	private Desktop desktop;
	
	private static final boolean EXPLICIT_SET_ACTION = true;
	
	public ComponentImageChooserButton(MainLabel component_image_choice_text_label) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.component_image_choice_text_label = (ComponentImageChoiceTextLabel) component_image_choice_text_label;
		desktop = Desktop.getDesktop();
		setAction();
	}

	@Override
	protected void setAction() {
		filechooser = new FileChooser();
		filechooser.getExtensionFilters().addAll(
				new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
		getButton().setOnAction(e -> {
			file = filechooser.showOpenDialog(new Stage());
			if(file != null) {
				component_image_choice_text_label.setText(
//						file.toString().split("src")[1]);
						file.toString().substring(file.toString().lastIndexOf(File.separator) + 1));
				try {
					desktop.open(file);
				} catch (IOException e1) {
					System.err.println("Canoot open image file on desktop!");
				}
			}
		});
	}
	
	public String getFileName() {
		return file.toString();
	}
}