package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_player.GamePlayer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Any unit that exists on the explored terrain would be shown in the MiniMap.
 * 
 * Clicking on the MiniMap moves the selected units to corresponding location on the real map.
 * 
 * @author Frank Yin
 *
 */
public class MiniMap implements VisualUpdate {
	
	public static final Color DEFAULTMINIMAPBGCOLOR = Color.BEIGE;
	public static final Color DEFAULTMINIMAPSIDECOLOR = Color.BLACK;
	public static final double UNITMINIMAPRATIO = 1.0 / 80;
	public static final double MINIMAPDISPLAYXRATIO = 1.0 / 4 / GamePlayer.MAP_DISPLAY_RATIO;
	public static final double MINIMAPDISPLAYYRATIO = 1.0 / 3 / GamePlayer.MAP_DISPLAY_RATIO;
	private Group myMiniMap;
	private Group myVisibleUnits;
	private List<GameObject> currentVisibleUnits;
	private double myWidth;
	private double myHeight; 
	
	public MiniMap(double width, double height) {
		myWidth = width;
		myHeight = height;
		myMiniMap = new Group();
		myVisibleUnits = new Group();
		currentVisibleUnits = new ArrayList<>();
		initializeMiniMapBackground();
		myMiniMap.getChildren().add(myVisibleUnits);
	}
	
	private void initializeMiniMapBackground() {
		Rectangle miniMapDisplay = new Rectangle(myWidth, myHeight);
		miniMapDisplay.setFill(DEFAULTMINIMAPBGCOLOR);
		miniMapDisplay.setStroke(DEFAULTMINIMAPSIDECOLOR);
		myMiniMap.getChildren().add(miniMapDisplay);
	}
	
	@Override
	public void update(List<GameObject> allGameObjects) {
		myVisibleUnits.getChildren().clear();
		currentVisibleUnits = filter(allGameObjects);
		displayUnits(currentVisibleUnits);
	}
	
	private List<GameObject> filter(List<GameObject> gameObjects) {
		List<GameObject> minimapObjects = new ArrayList<>();
		gameObjects.forEach(go -> minimapObjects.add(go));
		return minimapObjects;
	}
	
	private void displayUnits(List<GameObject> currentVisibleUnits) {
		currentVisibleUnits.forEach(go -> {
			Rectangle unitSquare = new Rectangle(myWidth * UNITMINIMAPRATIO, myWidth * UNITMINIMAPRATIO);
			unitSquare.setX(go.getTransform().getPosition().getX() * MINIMAPDISPLAYXRATIO);
			unitSquare.setY(go.getTransform().getPosition().getY() * MINIMAPDISPLAYYRATIO);
			//unitSquare.setFill(object.getPlayerColor());
			unitSquare.setFill(Color.BLUE);
			myVisibleUnits.getChildren().add(unitSquare);
		});
	}
	
	/**
	 * returns the current mini-map
	 * @return
	 */
	@Override
	public Node getNodes() {
		return myMiniMap;
	}
	
}
