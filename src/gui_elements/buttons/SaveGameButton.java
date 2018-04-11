package gui_elements.buttons;

import java.io.Writer;

import authoring.backend.CreatedObjects;
import resources.Resources;

public class SaveGameButton extends MainButton {

	private static final String FILENAME = "save_game_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = true;
	private Writer myWriter;

	public SaveGameButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
	}

	@Override
	protected void setAction() {
		myWriter.write(Resources.getString(AUTHOR_LOCATION)), CreatedObjects.getAuthoringObjects());
	}
}
