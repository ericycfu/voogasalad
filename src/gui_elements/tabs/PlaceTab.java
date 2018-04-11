package gui_elements.tabs;

import authoring.backend.AuthoringController;
import authoring.backend.CreatedMaps;
import authoring.backend.DraggableScrollPane;
import authoring.backend.GameEntity;
import javafx.scene.control.Tab;

public class PlaceTab extends Tab {
	private DraggableScrollPane myScroll;
	public PlaceTab(AuthoringController ac, GameEntity game) {
		this.setText("Place");
		myScroll = new DraggableScrollPane(ac, game);
		this.setContent(myScroll);
	}
	
	public DraggableScrollPane getScroll() {
		return myScroll;
	}
	
	
}
