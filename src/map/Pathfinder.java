package map;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Class that implements pathfinding algorithm
 */

public class Pathfinder {

	private GridMap gridMap;
	
	
	public Pathfinder(GridMap gridMap)
	{
		this.gridMap = gridMap;
		
	}
	
	
	/**
	 * 
	 * @param obj
	 * @param targetPos
	 * @return
	 * Get the cell to which to move next
	 */
	public Vector2 getNextCell(GameObject obj, Vector2 targetPos)
	{
		if(gridMap.getCell(convert(targetPos).getRow(), convert(targetPos).getColumn()).isObstacle())
			return obj.getTransform().getPosition();
		
		List<GridCell> openList = new ArrayList<>();
		List<GridCell> closedList = new ArrayList<>();
		
		GridCell current = convert(obj.getTransform().getPosition());
		GridCell target = convert(targetPos);
		
		closedList.add(current);
		openList.addAll(getValidNeighbors(current));
		
		
		
		
	}
	
	/**
	 * 
	 * @param pos
	 * @return
	 * Converts vector2 position into grid position
	 */
	private GridCell convert(Vector2 pos)
	{
		return new GridCell(0,0);
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 * Checks if cell is inbounds of the grid
	 */
	private boolean inBounds(GridCell cell)
	{
		return(cell.row < 0 || cell.row >= gridMap.getHeight()
				|| cell.column < 0 || cell.column >= gridMap.getWidth());
	}
	
	private boolean isMoveableInto(GridCell cell)
	{
		return(gridMap.getCell(cell.row, cell.column));
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 * Gets the valid neighbors of the cell.
	 */
	private List<GridCell> getValidNeighbors(GridCell cell)
	{
		List<GridCell> neighbors = new ArrayList<>();
		for(int i = -1; i < 2; i++)
		{
			for(int j = -1; j < 2; j++)
			{
				if(i == 0 && j == 0) continue;
				GridCell temp = new GridCell(cell.row + i, cell.column + j);
				if(inBounds(temp) && isMoveableInto(temp))
				{
					neighbors.add(temp);
				}
			}
		}
		return neighbors;
	}
}
