package gui_elements.buttons;

import game_data.Writer;

import java.io.IOException;

import authoring.backend.CreatedObjects;
import resources.Resources;

public class SaveGameButton extends MainButton {

	private static final String FILENAME = "save_game_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private Writer myWriter;

	public SaveGameButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
	}

	@Override
	protected void setAction() {
//		getButton().setOnAction(value -> {
//			myWriter = new Writer();
//			try {
//				myWriter.write(Resources.getString("AUTHOR_LOCATION"), CreatedObjects.getAuthoringObjects());
//			} catch (IOException e) {
//				System.err.println("Could not save created authoring objects");
//			}
//		});
	}	
}