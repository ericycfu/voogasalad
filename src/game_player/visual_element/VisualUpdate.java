package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import javafx.scene.Node;

public interface VisualUpdate {
	
	public void update(List<GameObject> gameObjects);
	
	public Node getNodes();
	
}
