package map;

import java.util.List;

import game_object.GameObjectManager;
import transform_library.Transform;
import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * This class transforms the map into a grid which can then be used for pathfinding functionality.
 * Each cell is assigned a weightage 
 */

public class GridMap {

	private GridCell[][] mapGrid;
	private int width;
	private int height;
	
	public void GridMap(int width, int height)
	{
		mapGrid = new GridCell[width][height];
		this.width = width;
		this.height = height;
		initializeEmptyMap();
	}
	
	public void updateMapPositions(GameObjectManager gameObjectManager)
	{
		List<Transform> transformList = gameObjectManager.accessGameObjectTransforms();
		//convert javafx coordinates to grid coordinates and update grid
	}
	
	private void initializeEmptyMap()
	{
		for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				mapGrid[i][j].setObstacle(false);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public GridCell getCell(int r, int c)
	{
		return mapGrid[r][c];
	}
	
}
