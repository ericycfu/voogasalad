package gui_elements.tabs;

import javafx.scene.Group;
import javafx.scene.control.Tab;
import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.view.DraggableScrollPane;

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
		ac.addToAuthorController(myScroll);
		this.setContent(myScroll);
	}
	
	private void setButtons() {
		place_root.getChildren().addAll(
//				new SaveGameButton(game).getButton()
//										new PlayGameButton().getButton()
										);
	}

	public DraggableScrollPane getScroll() {
		return myScroll;
	}	
}
