package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import gui_elements.buttons.PlayGameButton;
import gui_elements.buttons.SaveGameButton;
import gui_elements.factories.ButtonFactory;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DisplayMenu extends HBox {
	private AuthoringController ac;
	private GameEntity gameEntity;
	private Stage stage;
	public DisplayMenu(AuthoringController ac, GameEntity game, Stage stage) {
		this.ac = ac;
		this.gameEntity = game;
		this.stage = stage;
		initializeButtons();
	}
	
	private void initializeButtons() {
//		this.getChildren().add(ButtonFactory.makeButton("Play Game", e -> playGame()));
		Button AddMapButton = ButtonFactory.makeButton("Add Map", e -> makeNewMap());
		AddMapButton.getStyleClass().add("map_setting_buttons");
		Button HomeButton = ButtonFactory.makeButton("Home", e -> new StartScreen(stage));
		HomeButton.getStyleClass().add("save_button");
		this.getChildren().addAll(
				new PlayGameButton(ac, gameEntity),
				new SaveGameButton(ac, gameEntity),
				AddMapButton,
				HomeButton
			);
	}
	
	private void makeNewMap() {
		gameEntity.getCreatedMaps().makeNewMap();
	}
	

}
