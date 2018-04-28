package authoring.backend;

import java.io.IOException;

import game_data.Reader;
import resources.Resources;

public class LoadGameState {
	
	private static final String RESOURCES_STRING = "AUTHOR_LOCATION_OBJECTS";
	private Reader myReader;
	
	public LoadGameState() {
		try {
			CreatedObjects.setAuthoringObjects(myReader.read(Resources.getString(RESOURCES_STRING)));
		} catch (ClassNotFoundException e) {
			System.err.println("No resource file stores this authoring object.");
		} catch (IOException e) {
			System.err.println("IO error, could not open resource file.");
		}
	}

}
