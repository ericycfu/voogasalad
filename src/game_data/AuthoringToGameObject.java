package game_data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import authoring.backend.AuthoringObject;
import authoring.backend.DraggableImageView;
import game_object.GameObject;
import transform_library.Transform;
import transform_library.Vector2;

public final class AuthoringToGameObject {

	private AuthoringToGameObject() {
		// TODO Auto-generated constructor stub
	}

	public static List<GameObject> convert(Map<AuthoringObject, List<DraggableImageView>> map) {
		List<GameObject> GOs = new ArrayList<GameObject>();
		for(AuthoringObject AO: map.keySet()) {
			for(DraggableImageView DIV: map.get(AO)) {
				GOs.add(new GameObject(new Transform(new Vector2(DIV.getX(), DIV.getY())), AO.getObjectLogic()));
			}
		}
		
		return GOs;
	}
	
}
