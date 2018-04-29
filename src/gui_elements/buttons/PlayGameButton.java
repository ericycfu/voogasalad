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
import authoring.backend.DraggableImageView;
import authoring.backend.GameEntity;
import authoring.backend.MapSettings;
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
	private AuthoringController ac;
	private GameEntity gameEntity;
	private Writer myWriter = new Writer();
	public PlayGameButton(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.gameEntity = game;
		setupText();
		setAction();
	}
	
	public PlayGameButton(Stage stage) {
		setupText();
	//	this.setOnAction(e -> );
	}
	
	private void setupText() {
//		this.getStyleClass().add("make_game_button");
		this.setText("Play Game");
	}
	
	protected void setAction() {
		this.setOnAction(value -> {
			System.out.print("Playing game!");
			
			Map<AuthoringObject, List<DraggableImageView>> map = ac.getCurrentMap().getLocations();
			Map<AuthoringObject, List<Vector2>> changedMap = turnImageViewToVector2(map);
			List<Object> listForAuthor = new ArrayList<>();
			List<Object> listForGame = new ArrayList<>();
			try {
				listForAuthor.add(gameEntity.getCreatedObjects().getAuthoringObjects());
				listForAuthor.add(changedMap);
				listForAuthor.add(ac.getCurrentMap().getMapSettings());
				listForAuthor.add(gameEntity.getResourceManager());
				myWriter.write(Resources.getString("AUTHOR_LOCATION"), listForAuthor);
				GameObjectManager myGOM = AuthoringToGameObject.convertMap(map,gameEntity.getResourceManager());
				List<GameObject> possibleObjectsList = AuthoringToGameObject.convertList(CreatedObjects.getAuthoringObjects());
				Set<GameObject> possibleObjects = new HashSet<>();
				possibleObjects.addAll(possibleObjectsList);
				listForGame.add(myGOM);
				listForGame.add(possibleObjects);
				listForGame.add(ac.getCurrentMap().getMapSettings());
				myWriter.write(Resources.getString("INITIALIZATION_LOCATION"),listForGame);
				System.out.println("Object saved");
			} catch (IOException e) {
				System.err.println("Could not save created authoring objects");
			}
			
		});
	}
	
	private Map<AuthoringObject, List<Vector2>> turnImageViewToVector2(Map<AuthoringObject, List<DraggableImageView>> originalMap) {
		Map<AuthoringObject, List<Vector2>> newMap = new HashMap<>();
		for (AuthoringObject obj: originalMap.keySet()) {
			List<DraggableImageView> list = originalMap.get(obj);
			List<Vector2> newList = new ArrayList();
			for (DraggableImageView img: list) {
				Vector2 v = new Vector2(img.getX(), img.getY());
				newList.add(v);
			}
			newMap.put(obj, newList);
		}
		return newMap;
	}
	
}
