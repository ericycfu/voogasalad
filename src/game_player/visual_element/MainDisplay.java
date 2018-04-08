package game_player.visual_element;

import java.util.Collections;
import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;

public class MainDisplay implements VisualUpdate {
	
	private Group myTerrains;
	private Group myUnits;
	private List<GameObject> mySelectedUnits;

	@Override
	public void update(List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getNodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<GameObject> getSelectedUnits(){
		return Collections.unmodifiableList(mySelectedUnits);
	}
}
