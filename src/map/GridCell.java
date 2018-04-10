package map;

import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Helper cell class for pathfinding map
 */

public class GridCell {

	private int row;
	private int column;
	
	private boolean isObstacle;
	
	private int gVal;
	private int hVal;
	
	private int movementCost = 1;
	
	public void updateGVal(int val)
	{
		gVal = val;
	}
	
	public void updateGVal(GridCell cell)
	{
		gVal = cell.getGVal() + movementCost;
	}
	
	public void setHVal(GridCell cell)
	{
		hVal = (Math.abs(column - cell.getColumn()) + Math.abs(row - cell.getRow())) * this.movementCost;
	}
	
	public int getGVal()
	{
		return gVal;
	}
	
	public int getHVal()
	{
		return hVal;
	}
	
	public GridCell(int r, int c)
	{
		row = r;
		c = column;
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
}
