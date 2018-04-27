package gui_elements.buttons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import authoring.backend.AuthoringController;
import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.backend.DraggableImageView;
import authoring.backend.GameEntity;
import game_data.AuthoringToGameObject;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import javafx.stage.Stage;
import resources.Resources;
import transform_library.Vector2;

public class PlayGameButton extends ImageButton {	
	private static final String RESOURCES_STRING = "AUTHOR_LOCATION_OBJECTS";
	private static final String RESOURCES_STRING2 = "AUTHOR_LOCATION_MAP";
	private static final String INITIAL_MAP_STRING = "INITIALIZATION_LOCATION_MAP";
	private static final String INITIAL_LIST_STRING = "INITIALIZATION_LOCATION_LIST";
	private AuthoringController ac;
	private GameEntity gameEntity;
	public PlayGameButton(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.gameEntity = game;
		setupText();
		setAction();
	}
	
	public PlayGameButton(Stage stage) {
		setupText();
	}
	
	private void setupText() {
//		this.getStyleClass().add("make_game_button");
		this.setText("Play Game");
	}
	
	protected void setAction() {
		this.setOnAction(value -> {
			System.out.print("Playing game!");
			Writer myWriter = new Writer();
//			myReader = new Reader();
			
			Map<AuthoringObject, List<DraggableImageView>> map = ac.getMap().getLocations();
			Map<AuthoringObject, List<Vector2>> changedMap = turnImageViewToVector2(map);
			List<Map<AuthoringObject, List<Vector2>>> listFormMap = new ArrayList<>();
			listFormMap.add(changedMap);
			try {
				myWriter.write(Resources.getString(RESOURCES_STRING), gameEntity.getCreatedObjects().getAuthoringObjects());
				myWriter.write(Resources.getString(RESOURCES_STRING2), listFormMap);
				List<GameObjectManager> listFormGOM = new ArrayList<>();
				GameObjectManager myGOM = AuthoringToGameObject.convertMap(map);
				listFormGOM.add(myGOM);
				myWriter.write(Resources.getString(INITIAL_MAP_STRING), listFormGOM);
				List<GameObject> possibleObjects = AuthoringToGameObject.convertList(CreatedObjects.getAuthoringObjects());
				myWriter.write(Resources.getString(INITIAL_LIST_STRING),possibleObjects);
				//GamePlayer gamePlayer = new GamePlayer(myGOM);
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
