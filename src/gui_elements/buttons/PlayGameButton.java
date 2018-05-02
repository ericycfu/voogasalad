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
import authoring.backend.MapSettings;
import authoring.backend.SaveAuthoringGameState;
import authoring.support.DraggableImageView;
import game_data.AuthoringToGameObject;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import resources.Resources;
import transform_library.Vector2;
/**
 * saves the current environment locally and remotely for continued use of both the authoring environment and the rest of the project
 * @author shichengrao
 *
 */
public class PlayGameButton extends Button {

	private AuthoringController authoring_controller;
	private GameEntity game_entity;
	private Writer myWriter = new Writer();
	
	public PlayGameButton(AuthoringController authoring_controller, GameEntity game_entity) {
		this.authoring_controller = authoring_controller;
		this.game_entity = game_entity;
		setupText();
		setAction();
	}
	
	public PlayGameButton(Stage stage) {
		setupText();
	}
	
	public PlayGameButton(Stage stage, String style) {
		setupText();
		this.getStyleClass().add(style);

	}
	
	private void setupText() {
		this.setText("Play Game");
		
	}
	
	protected void setAction() {
		this.setOnAction(value -> {
			System.out.print("Playing game!");
			new SaveAuthoringGameState(authoring_controller, game_entity);
		});
	}
}