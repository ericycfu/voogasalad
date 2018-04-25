package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.ButtonFactory;
import authoring.backend.GameEntity;
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
		this.getChildren().add(ButtonFactory.makeButton("Play Game", e -> playGame()));
	}
	
	private void playGame() {
		
	}

}
