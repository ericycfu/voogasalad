package map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

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
		
		PriorityQueue<GridCell> openList = new PriorityQueue<>(8, cellComparator);
		List<GridCell> closedList = new ArrayList<>();
		
		GridCell current = convert(obj.getTransform().getPosition());
		GridCell target = convert(targetPos);
		
		current.updateGVal(0);
		current.setHVal(target);
		
		openList.add(current);
		
		while(!openList.isEmpty())
		{
			GridCell cell = openList.remove();
			if(cell.matches(target))
			{
				//end path
			}
			
			closedList.add(current);
			for(GridCell nbr : getValidNeighbors(current))
			{
				if(closedList.contains(nbr)) continue;
				
				int gscore = current.getGVal();
				boolean isInOpen = openList.contains(nbr);
				// if(!isInOpen || gScore < nbr.getGVal())
			}
			
			
			
		}
		return null;
		
		
		
		
		
		
		
		
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
		return(cell.getRow() < 0 || cell.getColumn() >= gridMap.getHeight()
				|| cell.getRow() < 0 || cell.getColumn() >= gridMap.getWidth());
	}
	
	private boolean isMoveableInto(GridCell cell)
	{
		return(gridMap.getCell(cell.getRow(), cell.getColumn()).isObstacle());
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
				GridCell temp = new GridCell(cell.getRow() + i, cell.getColumn() + j);
				if(inBounds(temp) && isMoveableInto(temp))
				{
					neighbors.add(temp);
				}
			}
		}
		return neighbors;
	}
	
	private final Comparator<GridCell> cellComparator = new Comparator<GridCell>() {
		public int compare(GridCell a, GridCell b)
		{
			return Integer.compare(a.getFVal(), b.getFVal());
		}
	};
			
}
