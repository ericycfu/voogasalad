package game_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.backend.AuthoringObject;
import authoring.backend.DraggableImageView;
import game_object.GameObject;
import game_object.GameObjectManager;
import transform_library.Transform;
import transform_library.Vector2;

public final class AuthoringToGameObject {

	private AuthoringToGameObject() {

	}

	public static GameObjectManager convertMap(Map<AuthoringObject, List<DraggableImageView>> map) {
		GameObjectManager GOM = new GameObjectManager();
		for(AuthoringObject AO: map.keySet()) {
			for(DraggableImageView DIV: map.get(AO)) {
				GOM.createGameObject(new Transform(new Vector2(DIV.getX(), DIV.getY())), AO.getObjectLogic());
			}
		}
		
		return GOM;
	}
	/**
	 * currently doesn't work, need gameObjectConstructor
	 * @param list
	 * @return
	 */
	public static List<GameObject> convertList(List<AuthoringObject> list){
		List<GameObject> gameObjectList = new ArrayList<>();
		for(AuthoringObject AO: list) {
			//gameObjectList.add(new GameObject())
		}
		
		
		return gameObjectList;
	}
	
}
