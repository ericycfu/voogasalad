package gui_elements.buttons;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.backend.SaveAuthoringGameState;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class SaveGameButton extends Button {

	private static final String FILENAME = "save_game_button.properties";
	private static final String ALERT_TITLE = "Game Design Saved";
	private static final String ALERT_MESSAGE = "Your game design has been saved!";
//	private static final boolean EXPLICIT_SET_ACTION = false;
	private AuthoringController authoring_controller;
	private GameEntity game_entity;
	
	public SaveGameButton(AuthoringController authoring_controller, GameEntity game_entity) {
//		super(FILENAME, EXPLICIT_SET_ACTION);
		this.authoring_controller = authoring_controller;
		this.game_entity = game_entity;
		this.setText("Save Game");
		setAction();
		this.getStyleClass().add("map_setting_buttons");
	}
	

	private void setAction() {
		this.setOnAction(value -> {
			new SaveAuthoringGameState(authoring_controller, game_entity);
			createAlert();
		});
	}

	private void createAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ALERT_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(ALERT_MESSAGE);
		alert.showAndWait();
	}
}