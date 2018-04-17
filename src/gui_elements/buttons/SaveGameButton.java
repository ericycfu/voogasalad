package gui_elements.buttons;

import game_data.Reader;
import game_data.Writer;
import gui_elements.tabs.DesignTab;

import java.io.IOException;
import java.util.List;

import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import resources.Resources;

public class SaveGameButton extends MainButton {

	private static final String FILENAME = "save_game_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private Writer myWriter;
	private Reader myReader;

	public SaveGameButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			myWriter = new Writer();
			myReader = new Reader();
			try {
				myWriter.write(Resources.getString("AUTHOR_LOCATION"), CreatedObjects.getAuthoringObjects());
			} catch (IOException e) {
				System.err.println("Could not save created authoring objects");
			}
			
			CreatedObjects.getAuthoringObjects().clear();
			
			try {
				CreatedObjects.setAuthoringObjects(myReader.read(Resources.getString("AUTHOR_LOCATION")));
				DesignTab.setAuthoringObject(CreatedObjects.getAuthoringObjects().get(2));
				DesignTab.assignComponents();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
			}
		});
	}
}