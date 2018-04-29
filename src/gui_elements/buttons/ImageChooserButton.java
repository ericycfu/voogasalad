package gui_elements.buttons;

import java.io.File;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImageChooserButton extends Button {
	private FileChooser filechooser;
	private File file;

	public ImageChooserButton() {
		this.setText("Choose Image");
		setAction();
	}
	
	private void setAction() {
		filechooser = new FileChooser();
		this.setOnAction(e -> {
			file = filechooser.showOpenDialog(new Stage());
			
		});
	}
	
	public String getFilePath() {
		return file.getName();
	}
}
