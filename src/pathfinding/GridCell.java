package pathfinding;

import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Helper cell class for pathfinding map. Contains data like position in the grid and its H and G scores for the 
 * A* algorithm.
 */

public class GridCell {

	private final static int MOVEMENT_COST = 10;
	private final static double DIAGONAL_MODIFIER = 1.4;
	
	private int row;
	private int column;
	
	private boolean isObstacle;
	
	private int gVal;
	private int hVal;
	
	private GridCell parent;
	

	public GridCell(int r, int c)
	{
		row = r;
		column = c;
		isObstacle = false;
	}
	
	public void setGVal(int val)
	{
		gVal = val;
	}
	
	public void setGVal(GridCell cell)
	{
		gVal = calculateGVal(cell);
	}
	
	private int getMovementCost(GridCell origin, GridCell target)
	{
		if(origin.getRow() != target.getRow() && origin.getColumn() != target.getColumn())
		{
			return (int)(MOVEMENT_COST * DIAGONAL_MODIFIER);
		}
		return MOVEMENT_COST;
	}
	
	public void setHVal(GridCell cell)
	{
		hVal = (Math.abs(column - cell.getColumn()) + Math.abs(row - cell.getRow())) * MOVEMENT_COST;
	}
	
	public int getFVal()
	{
		return gVal + hVal;
	}
	
	public int getGVal()
	{
		return gVal;
	}
	
	public int calculateGVal(GridCell cell)
	{
		return cell.getGVal() + getMovementCost(cell, this);
	}
	
	public int getHVal()
	{
		return hVal;
	}
	
	
	public void setObstacle(boolean val)
	{
		isObstacle = val;
	}
	
	public boolean isObstacle()
	{
		return isObstacle;
	}

	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	public boolean matches(GridCell other)
	{
		return(this.row == other.row && this.column == other.column);
	}
	

	public GridCell getParent() 
	{
		return parent;
	}

	public void setParent(GridCell parent) 
	{
		this.parent = parent;
	}
}
