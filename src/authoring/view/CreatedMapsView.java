package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.CreatedMaps;
import javafx.scene.control.ScrollPane;

public class CreatedMapsView extends ScrollPane {
	public static final int THUMBNAIL_WIDTH = 150;
	public static final int THUMBNAIL_HEIGHT = 150;
	private CreatedMaps createdmaps;
	
	public CreatedMapsView(AuthoringController ac, CreatedMaps cm) {
		this.createdmaps = cm;
	}

}
