package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import game_player.Element;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class UnitActionDisplay implements Element, VisualUpdate{
	
	public static final int ACTION_GRID_LENGTH = 4;
	public static final int ACTION_GRID_WIDTH = 3;
	ImageView[][] myActionsGrid;
	
	public UnitActionDisplay(GameObject unit) {
		
	}
	
	private void initialize() {
		myActionsGrid = new ImageView[ACTION_GRID_LENGTH][ACTION_GRID_WIDTH];
		for (int i = 0; i < myActionsGrid.length; i++) {
			for (int j = 0; j < myActionsGrid[0].length; j++) {
				myActionsGrid[i][j] = new ImageView();
			}
		}
	}

	@Override
	public void update(List<GameObject> gameObjects) {
		// TODO Auto-generated method stub
		
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
