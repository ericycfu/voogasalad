package gui_elements.tabs;

import gui_elements.buttons.PlayGameButton;
import gui_elements.buttons.SaveGameButton;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import authoring.backend.AuthoringController;
import authoring.backend.DraggableScrollPane;
import javafx.scene.control.Tab;

public class PlaceTab extends Tab {

	private DraggableScrollPane myScroll;
	private Group place_root;
	private AuthoringController ac;

	public PlaceTab(AuthoringController ac) {
		this.ac = ac;
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
		myScroll = new DraggableScrollPane(ac);
		this.setContent(myScroll);
	}
	
	private void setButtons() {
		place_root.getChildren().addAll(new SaveGameButton().getButton(),
										new PlayGameButton().getButton());
	}

	public DraggableScrollPane getScroll() {
		return myScroll;
	}	
}
