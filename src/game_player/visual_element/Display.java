package game_player.visual_element;

import java.util.List;

import game_object.GameObject;

// implemented by Display and Minimap
public interface Display {
	
	public void display(List<GameObject> list);
	
}
