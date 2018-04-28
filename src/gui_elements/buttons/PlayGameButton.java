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
import game_data.AuthoringToGameObject;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import resources.Resources;
import transform_library.Vector2;

public class PlayGameButton extends Button {	
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
	//	this.setOnAction(e -> );
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
			
			Map<AuthoringObject, List<DraggableImageView>> map = ac.getCurrentMap().getLocations();
			Map<AuthoringObject, List<Vector2>> changedMap = turnImageViewToVector2(map);
			List<Map<AuthoringObject, List<Vector2>>> listFormMap = new ArrayList<>();
			listFormMap.add(changedMap);
			List<Object> listForm = new ArrayList<>();
			try {
				myWriter.write(Resources.getString(RESOURCES_STRING), gameEntity.getCreatedObjects().getAuthoringObjects());
				myWriter.write(Resources.getString(RESOURCES_STRING2), listFormMap);
				GameObjectManager myGOM = AuthoringToGameObject.convertMap(map);
				listForm.add(myGOM);
				List<GameObject> possibleObjectsList = AuthoringToGameObject.convertList(CreatedObjects.getAuthoringObjects());
				Set<GameObject> possibleObjects = new HashSet<>();
				possibleObjects.addAll(possibleObjectsList);
				listForm.add(possibleObjects);
				myWriter.write(Resources.getString(INITIAL_MAP_STRING),listForm);
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
