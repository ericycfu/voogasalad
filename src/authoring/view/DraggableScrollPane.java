package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.CreatedMaps;
import authoring.backend.GameEntity;
import authoring.backend.MapEntity;
import javafx.scene.control.ScrollPane;

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
		MapEntity initialMap;
		if (createdmaps.getSize() < 1) {
			initialMap = createdmaps.makeNewMap();
		}
		else {
			initialMap = createdmaps.getObjectByIndex(0);
		}
		authoringcontroller.updateMap(initialMap);
		this.setContent(initialMap);
		authoringcontroller.getCreatedMapsView().update();
		this.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
	}

}
