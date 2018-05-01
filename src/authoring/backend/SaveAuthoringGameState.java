package authoring.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.support.DraggableImageView;
import game_data.AuthoringToGameObject;
import game_data.Writer;
import game_object.GameObject;
import game_object.GameObjectManager;
import resources.Resources;
import transform_library.Vector2;

public class SaveAuthoringGameState {

	private Writer myWriter = new Writer();
	
	public SaveAuthoringGameState(AuthoringController authoring_controller, GameEntity game_entity) {
		Map<AuthoringObject, List<DraggableImageView>> map = authoring_controller.getCurrentMap().getLocations();
		Map<AuthoringObject, List<Vector2>> changedMap = turnImageViewToVector2(map);
		List<Object> listForAuthor = new ArrayList<>();
		List<Object> listForGame = new ArrayList<>();
		try {
			listForAuthor.add(game_entity.getCreatedObjects().getAuthoringObjects());
			listForAuthor.add(changedMap);
			listForAuthor.add(authoring_controller.getCurrentMap().getMapSettings());
			listForAuthor.add(game_entity.getResourceManager());
			myWriter.write(Resources.getString("AUTHOR_LOCATION"), listForAuthor);
			GameObjectManager myGOM = AuthoringToGameObject.convertMap(map,game_entity.getResourceManager());
			List<GameObject> possibleObjectsList = AuthoringToGameObject.convertList(game_entity.getCreatedObjects().getAuthoringObjects());
			Set<GameObject> possibleObjects = new HashSet<>();
			possibleObjects.addAll(possibleObjectsList);
			listForGame.add(myGOM);
			listForGame.add(possibleObjects);
			listForGame.add(authoring_controller.getCurrentMap().getMapSettings());
			myWriter.write(Resources.getString("INITIALIZATION_LOCATION"),listForGame);
			System.out.println("Object saved");
		} catch (IOException e) {
			System.err.println("Could not save created authoring objects");
		}
	}
	
	private Map<AuthoringObject, List<Vector2>> turnImageViewToVector2(Map<AuthoringObject, List<DraggableImageView>> originalMap) {
		Map<AuthoringObject, List<Vector2>> newMap = new HashMap<>();
		for (AuthoringObject obj: originalMap.keySet()) {
			List<DraggableImageView> list = originalMap.get(obj);
			List<Vector2> newList = new ArrayList<Vector2>();
			for (DraggableImageView img: list) {
				Vector2 v = new Vector2(img.getX(), img.getY());
				newList.add(v);
			}
			newMap.put(obj, newList);
		}
		return newMap;
	}
}