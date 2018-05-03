package gui_elements.buttons;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.backend.SaveAuthoringGameState;
import game_data.Writer;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import server_client.ServerClient;
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
			new SaveAuthoringGameState(authoring_controller, game_entity);
		});
		
	}
}