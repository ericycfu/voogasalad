package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;

/**
 * The GUI component that displays the dynamic information in the top panel (current resources and time) to 
 * the player.
 * @author
 *
 */
public class DynamicDisplay implements VisualUpdate {
	private Group myTerrains;
	private Group myUnits;
	
	@Override
	public void update(List<GameObject> gameObjects) {
		
	}

	@Override
	public Node getNodes() {
		return null;
	}
	
}
