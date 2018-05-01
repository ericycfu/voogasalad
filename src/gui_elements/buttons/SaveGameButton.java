package gui_elements.buttons;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.AuthoringController;
import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.backend.GameEntity;
import authoring.backend.SaveAuthoringGameState;
import authoring.support.DraggableImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import game_data.AuthoringToGameObject;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import resources.Resources;
import transform_library.Vector2;

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