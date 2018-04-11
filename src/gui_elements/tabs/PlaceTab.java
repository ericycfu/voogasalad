package gui_elements.tabs;

import gui_elements.buttons.PlayGameButton;
import gui_elements.buttons.SaveGameButton;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import authoring.backend.DraggableScrollPane;
import javafx.scene.control.Tab;

public class PlaceTab extends Tab {

	private DraggableScrollPane myMap;
	private Group place_root;

	public PlaceTab() {
		initialize();
	}
	
	private void initialize() {
		setGroup();
		setText();
		setButtons();
	}
	
	private void setGroup() {
		place_root = new Group();
		this.setContent(place_root);		
	}
	
	private void setText() {
		this.setText("Place");
		myMap = new DraggableScrollPane();
		this.setContent(myMap);
	}
	
	private void setButtons() {
		place_root.getChildren().addAll(new SaveGameButton().getButton(),
										new PlayGameButton().getButton());
	}

	public DraggableScrollPane getMap() {
		return myMap;
	}	
}