package gui_elements.tabs;

import authoring.backend.DraggableScrollPane;
import javafx.scene.control.Tab;

public class PlaceTab extends Tab {
	private DraggableScrollPane myMap;
	public PlaceTab() {
		this.setText("Place");
		myMap = new DraggableScrollPane();
		this.setContent(myMap);
	}
	
	public DraggableScrollPane getMap() {
		return myMap;
	}
	
}
