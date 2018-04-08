package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;

public class MainDisplay implements VisualUpdate {
	private Group myDisplayWindow;
	private Group myTerrains;
	private Group myUnits;
	
	public MainDisplay() {
		myDisplayWindow = new Group();
		myTerrains = new Group();
		myUnits = new Group();
		myDisplayWindow.getChildren().addAll(myTerrains, myUnits);
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		myUnits.getChildren().clear();
		myTerrains.getChildren().clear();
		for (GameObject unit : gameObjects) {
			myUnits.getChildren().add(unit.getRenderer());
		}
	}

	@Override
	public Node getNodes() {
		// TODO Auto-generated method stub
		return null;
	}
}
