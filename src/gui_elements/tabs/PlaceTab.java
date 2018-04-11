package gui_elements.tabs;

import authoring.backend.AuthoringController;
import authoring.backend.DraggableScrollPane;
import javafx.scene.control.Tab;

public class PlaceTab extends Tab {
	private DraggableScrollPane myScroll;
	public PlaceTab(AuthoringController ac) {
		this.setText("Place");
		myScroll = new DraggableScrollPane(ac);
		this.setContent(myScroll);
	}
	
	public DraggableScrollPane getScroll() {
		return myScroll;
	}
	
}
