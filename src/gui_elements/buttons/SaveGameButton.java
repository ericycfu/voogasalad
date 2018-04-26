package gui_elements.buttons;

import game_data.Writer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import authoring.backend.CreatedObjects;
import resources.Resources;

public class SaveGameButton extends MainButton {

	private static final String FILENAME = "save_game_button.properties";
	private static final String RESOURCES_STRING = "AUTHOR_LOCATION_OBJECTS";
	private static final String ALERT_TITLE = "COmponent Saved";
	private static final String ALERT_MESSAGE = "Your component has been saved!";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private Writer myWriter;
//	private Reader myReader;

	public SaveGameButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			myWriter = new Writer();
//			myReader = new Reader();
			try {
				myWriter.write(Resources.getString(RESOURCES_STRING), CreatedObjects.getAuthoringObjects());
				createAlert();
			} catch (IOException e) {
				System.err.println("Could not save created authoring objects");
			}
			
			CreatedObjects.getAuthoringObjects().clear();			
		});
	}
	
	private void createAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ALERT_TITLE);
		alert.setContentText(ALERT_MESSAGE);
		alert.showAndWait();
	}
}