package map;

import java.util.List;

import game_object.GameObjectManager;
import transform_library.Transform;

/**
 * 
 * @author Rayan
 * This class transforms the map into a grid which can then be used for pathfinding functionality.
 * Each cell is assigned a weightage 
 */

public class GridMap {

	private boolean[][] mapGrid;
	private Map<Integer[]>
	
	public void GridMap(int width, int height)
	{
		mapGrid = new boolean[width][height];
	}
	
	public void updateMapPositions(GameObjectManager gameObjectManager)
	{
		List<Transform> transformList = gameObjectManager.accessGameObjectTransforms();
		
	}
}
