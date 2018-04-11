package authoring.backend;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import authoring.backend.DraggableImageView;

public class DraggableScrollPane extends ScrollPane {
	private MapEntity myMap;
	public DraggableScrollPane(AuthoringController ac) {
		super();
		myMap = new MapEntity();
		ac.addToAuthoringController(myMap);
		this.setContent(myMap);
//		this.setContent();
	}

}
