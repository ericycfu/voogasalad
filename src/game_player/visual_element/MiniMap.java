package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import game_player.Element;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Any unit that exists on the explored terrain would be shown in the MiniMap.
 * 
 * Clicking on the MiniMap moves the selected units to corresponding location on the real map.
 * 
 * @author FY
 *
 */
public class MiniMap implements Element, VisualUpdate {
	private ImageView myMiniMapDisplay;
	
	public MiniMap(double xcoor, double ycoor, double length, double width, ImageView thisMiniMapDisplay) {
		
	}
	
	@Override
	public void update(List<GameObject> allGameObjects) {
		// TODO Auto-generated method stub
		
	}
	
	private 
	/**
	 * returns the current mini-map to 
	 * @return
	 */
	public ImageView getCurrentMiniMapDisplay() {
		return new ImageView();
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
