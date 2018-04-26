package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.ButtonFactory;
import authoring.backend.GameEntity;
import game_data.Writer;
import gui_elements.buttons.PlayGameButton;
import gui_elements.buttons.SaveGameButton;
import javafx.scene.layout.HBox;

public class DisplayMenu extends HBox {
	private AuthoringController ac;
	private GameEntity gameEntity;
	public DisplayMenu(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.gameEntity = game;
		initializeButtons();
	}
	
	private void initializeButtons() {
//		this.getChildren().add(ButtonFactory.makeButton("Play Game", e -> playGame()));
		this.getChildren().add(new PlayGameButton(ac, gameEntity));
		this.getChildren().add(new SaveGameButton(ac, gameEntity)); ////////////////////
	}
	

}
