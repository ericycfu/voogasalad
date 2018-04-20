package map;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import game_object.GameObject;
import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Class that implements pathfinding algorithm.
 */

public class Pathfinder {
	
	private final static int MOVEMENT_DIRECTIONS = 8;
	
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
	 * Finds a path for the unit
	 */
	private Stack<GridCell> calculatePath(Vector2 originPos, Vector2 targetPos)
	{			
		//Created open and closed lists
		PriorityQueue<GridCell> openSet = new PriorityQueue<>(MOVEMENT_DIRECTIONS, cellComparator);
		Set<GridCell> closedSet = new HashSet<>();
		
		//converted coordinates from JavaFX to MapGrid
		GridCell current = gridMap.convertToGrid(originPos);
		GridCell target = gridMap.convertToGrid(targetPos);
		
		//Setting the scores and adding the current node to the open list
		current.setGVal(0);
		current.setHVal(target);
		openSet.add(current);
		
		while(!openSet.isEmpty())
		{
			current = openSet.peek();
			if(current.matches(target))
			{
				return getPath(target);
			}
			
			current = openSet.remove();
			closedSet.add(current);
			
			for(GridCell nbr : getValidNeighbors(current))
			{
				if(closedSet.contains(nbr))
					continue;
				
				if(!openSet.contains(nbr))
				{
					nbr.setGVal(current);
					nbr.setHVal(target);
					nbr.setParent(current);
					openSet.add(nbr);
				}
				else
				{
					int tempG = nbr.calculateGVal(current);
					if(tempG >= nbr.getGVal()) continue;
					openSet.remove(nbr);
					nbr.setParent(current);
					nbr.setGVal(current);
					nbr.setHVal(target);
					openSet.add(nbr);
				}
					
			}
		}
		return getPath(null);	
	}
	
	public Queue<Vector2> findPath(GameObject obj, Vector2 target, List<GameObject> objList)
	{
		gridMap.updateMapPositions(objList);
		Stack<GridCell> gridPathPoints = calculatePath(obj.getTransform().getPosition(), target);
		Queue<Vector2> mapWayPoints = new LinkedList<>();
		for(GridCell cell : gridPathPoints)
		{
			mapWayPoints.add(gridMap.convertToWorld(cell));
		}
		return mapWayPoints;		
	}
	
	private Stack<GridCell> getPath(GridCell finalCell)
	{
		Stack<GridCell> path = new Stack<>();
		if(finalCell == null) 
			return path;
		while(finalCell.getParent() != null)
		{
			path.add(finalCell.getParent());
		}
		return path;
	}
	
	
	/**
	 * 
	 * @param cell
	 * @return
	 * Checks if cell is inbounds of the grid
	 */
	private boolean inBounds(GridCell cell)
	{
		return(cell.getRow() < 0 || cell.getColumn() >= gridMap.getGridLength()
				|| cell.getRow() < 0 || cell.getColumn() >= gridMap.getGridLength());
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
