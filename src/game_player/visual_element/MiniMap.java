package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_player.Element;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Any unit that exists on the explored terrain would be shown in the MiniMap.
 * 
 * Clicking on the MiniMap moves the selected units to corresponding location on the real map.
 * 
 * @author FY
 *
 */
public class MiniMap implements Element, VisualUpdate {
	private static double unitMapRatio = 1/100;
	private Group myMiniMap;
	private Rectangle myMiniMapDisplay;
	private Group myVisibleUnits;
	private List<GameObject> currentVisibleUnits;
	private double myLength;
	private double myWidth; 
	
	public MiniMap(double xcoor, double ycoor, double length, double width, Paint stroke, Paint background) {
		myMiniMap = new Group();
		myVisibleUnits = new Group();
		currentVisibleUnits = new ArrayList<GameObject>();
		initializeMiniMapBackground(xcoor, ycoor, length, width, stroke, background);
		myLength = length;
		myWidth = width;
	}
	
	private void initializeMiniMapBackground(double xcoor, double ycoor, double length, double width, Paint stroke, Paint background) {
		myMiniMapDisplay = new Rectangle(length, width);
		myMiniMapDisplay.setX(xcoor);
		myMiniMapDisplay.setY(ycoor);
		myMiniMapDisplay.setFill(background);
		myMiniMapDisplay.setStroke(stroke);
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
			/*if (object.isOnExploredTerrian()) { // if the unit is on the terrain THIS player has explored (THIS player's units have been on this terrain 
				minimapObjects.add(object);
			}*/
		}
		return minimapObjects;
	}
	
	private void displayUnits(List<GameObject> currentVisibleUnits) {
		for (GameObject object: currentVisibleUnits) {
			Rectangle unitSquare = new Rectangle(myWidth*unitMapRatio, myWidth*unitMapRatio);
			//unitSquare.setFill(object.getPlayerColor());
			myVisibleUnits.getChildren().add(unitSquare);
		}
	}
	
	/**
	 * returns the current mini-map to 
	 * @return
	 */
	
	public Group getCurrentMiniMapDisplay() {
		return myMiniMap;
	}
	
	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWidth(double w) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight(double h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Pane getPane() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNode(Node n) {
		// TODO Auto-generated method stub
		
	}
	
}
