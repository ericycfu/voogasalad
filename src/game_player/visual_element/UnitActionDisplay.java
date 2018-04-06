package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_player.Element;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UnitActionDisplay implements Element, VisualUpdate{
	
	public static final int ACTION_GRID_LENGTH = 4;
	public static final int ACTION_GRID_WIDTH = 3;
	public static final int CELL_SIZE = 50;
	private double myLeftCornerXCoor;
	private double myLeftCornerYCoor;
	ImageView[][] myActionsGrid;
	Map<String, List<String>> myUnitSkills;
	Map<String, List<Image>> mySkillImages;
	
	public UnitActionDisplay(double xcoor, double ycoor, Map<String, List<String>> unitSkills, 
			Map<String, List<Image>> skillImages) {
		mySkillImages = skillImages;
		myUnitSkills = unitSkills;
		myLeftCornerXCoor = xcoor;
		myLeftCornerYCoor = ycoor;
		initialize();
	}
	
	private void initialize() {
		myActionsGrid = new ImageView[ACTION_GRID_LENGTH][ACTION_GRID_WIDTH];
		for (int i = 0; i < myActionsGrid.length; i++) {
			for (int j = 0; j < myActionsGrid[0].length; j++) {
				Image img = new Image("images/default_icon.png");
				ImageView cell = new ImageView(img);
				cell.setLayoutX(myLeftCornerXCoor + 50*i);
				cell.setLayoutY(myLeftCornerYCoor + 50*j);
				myActionsGrid[i][j] = cell;
			}
		}
	}

	@Override
	public void update(List<GameObject> gameObjects) {
		GameObject gameObject = gameObjects.get(0);
		
		
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
