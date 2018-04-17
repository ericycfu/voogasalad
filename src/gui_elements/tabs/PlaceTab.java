package gui_elements.tabs;

import gui_elements.buttons.PlayGameButton;
import gui_elements.buttons.SaveGameButton;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import authoring.backend.AuthoringController;
import authoring.backend.CreatedMaps;
import authoring.backend.DraggableScrollPane;
import authoring.backend.GameEntity;
import javafx.scene.control.Tab;

public class PlaceTab extends Tab {

	private DraggableScrollPane myScroll;
	private Group place_root;
	private AuthoringController ac;
	private GameEntity game;

	public PlaceTab(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.game = game;
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
		myScroll = new DraggableScrollPane(ac, game);
		place_root.getChildren().add(myScroll);
	}
	
	private void setButtons() {
		place_root.getChildren().addAll(new SaveGameButton().getButton()
//										new PlayGameButton().getButton()
										);
	}

	public DraggableScrollPane getScroll() {
		return myScroll;
	}	
}
