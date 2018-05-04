package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import gui_elements.buttons.PlayGameButton;
import gui_elements.buttons.SaveGameButton;
import gui_elements.factories.ButtonFactory;
import javafx.scene.control.Button;
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
		Button AddMapButton = ButtonFactory.makeButton("Add Map", e -> makeNewMap());
		AddMapButton.getStyleClass().add("map_setting_buttons");

		this.getChildren().addAll(
				new PlayGameButton(ac, gameEntity),
				new SaveGameButton(ac, gameEntity),
				AddMapButton
				);
	}
	
	private void makeNewMap() {
		gameEntity.getCreatedMaps().makeNewMap();
	}
	

}
