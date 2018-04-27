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
	private AuthoringController authoringcontroller;

	public DraggableScrollPane(AuthoringController ac, GameEntity game) {
		super();
		this.authoringcontroller = ac;
		this.createdmaps = game.getCreatedMaps();
		initializeDefault();
	}
	
	private void initializeDefault() {
		MapEntity map = createdmaps.makeNewMap();
		authoringcontroller.updateMap(map);
		this.setContent(map);
		authoringcontroller.getCreatedMapsView().update();
		
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	}

}
