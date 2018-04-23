package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Any unit that exists on the explored terrain would be shown in the MiniMap.
 * 
 * Clicking on the MiniMap moves the selected units to corresponding location on the real map.
 * 
 * @author FY
 *
 */
public class MiniMap implements VisualUpdate {
	public static final double UNITMINIMAPRATIO = 1.0/80;
	public static final double MINIMAPDISPLAYXRATIO = 1.0/4/MainDisplay.MAP_DISPLAY_RATIO;
	public static final double MINIMAPDISPLAYYRATIO = 1.0/3/MainDisplay.MAP_DISPLAY_RATIO;
	private Group myMiniMap;
	private Rectangle myMiniMapDisplay;
	private Group myVisibleUnits;
	private List<GameObject> currentVisibleUnits;
	private double myWidth;
	private double myHeight; 
	
	public MiniMap(double width, double height) {
		myWidth = width;
		myHeight = height;
		myMiniMap = new Group();
		myVisibleUnits = new Group();
		currentVisibleUnits = new ArrayList<GameObject>();
		initializeMiniMapBackground();
		myMiniMap.getChildren().add(myVisibleUnits);
	}
	
	private void initializeMiniMapBackground() {
		myMiniMapDisplay = new Rectangle(myWidth, myHeight);
		myMiniMapDisplay.setFill(Color.BEIGE);
		myMiniMapDisplay.setStroke(Color.BLACK);
		myMiniMap.getChildren().add(myMiniMapDisplay);
	}
	
	@Override
	public void update(List<GameObject> allGameObjects) {
		myVisibleUnits.getChildren().clear();
		currentVisibleUnits = filter(allGameObjects);
		displayUnits(currentVisibleUnits);
	}
	
	private List<GameObject> filter(List<GameObject> gameObjects) {
		List<GameObject> minimapObjects = new ArrayList<GameObject>();
		for (GameObject object : gameObjects) {
			minimapObjects.add(object);
		}
		return minimapObjects;
	}
	
	private void displayUnits(List<GameObject> currentVisibleUnits) {
		for (GameObject object: currentVisibleUnits) {
			Rectangle unitSquare = new Rectangle(myWidth * UNITMINIMAPRATIO, myWidth * UNITMINIMAPRATIO);
			unitSquare.setX(object.getTransform().getPosition().getX() * MINIMAPDISPLAYXRATIO);
			unitSquare.setY(object.getTransform().getPosition().getY() * MINIMAPDISPLAYYRATIO);
			//unitSquare.setFill(object.getPlayerColor());
			unitSquare.setFill(Color.BLUE);
			myVisibleUnits.getChildren().add(unitSquare);
		}
	}
	
	/**
	 * returns the current mini-map to 
	 * @return
	 */
	@Override
	public Node getNodes() {
		return myMiniMap;
	}
	
}
