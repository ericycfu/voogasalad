package game_data;

import java.util.List;
import java.util.Map;

import authoring.backend.AuthoringObject;
import authoring.backend.DraggableImageView;
import game_object.GameObjectManager;
import transform_library.Transform;
import transform_library.Vector2;

public final class AuthoringToGameObject {

	private AuthoringToGameObject() {
		// TODO Auto-generated constructor stub
	}

	public static GameObjectManager convert(Map<AuthoringObject, List<DraggableImageView>> map) {
		GameObjectManager GOM = new GameObjectManager();
		for(AuthoringObject AO: map.keySet()) {
			for(DraggableImageView DIV: map.get(AO)) {
				GOM.createGameObject(new Transform(new Vector2(DIV.getX(), DIV.getY())), AO.getObjectLogic());
			}
		}
		
		return GOM;
	}
	
}
