package authoring.backend;

import javafx.scene.control.ScrollPane;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import authoring.backend.DraggableImageView;

public class DraggableScrollPane extends ScrollPane {
//	private MapEntity myMap;
	private CreatedMaps createdmaps;

	public DraggableScrollPane(AuthoringController ac, GameEntity game) {
		super();
		this.createdmaps = game.getCreatedMaps();
		MapEntity map = createdmaps.makeNewMap();
		ac.addToAuthoringController(map);
		this.setContent(map);
	}

}
