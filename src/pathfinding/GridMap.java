package pathfinding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_object.GameObject;
import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * This class transforms the map into a grid which can then be used for pathfinding functionality.
 * It also changes the states of the cells if they become obstacles. 
 */

//each object will have its own repeatedly created gridmap
public class GridMap {

	public final static int CELL_LENGTH = 10;
	private GridCell[][] mapGrid;
	private int gridHeight;
	private int gridWidth;
	
	public GridMap(double width, double height)
	{
		gridHeight = (int) (height / CELL_LENGTH);
		gridWidth = (int) (width / CELL_LENGTH);
		
		mapGrid = new GridCell[gridHeight][gridWidth];
		
		initializeEmptyMap();
	}
	
	//Call this whenever a building object is built or destroyed
	
	public void updateMapPositions(List<GameObject> objList)
	{
		for(GameObject obj : objList)
		{
			if(!obj.isBuilding()) 
				continue;
			if(obj.isDead()) 
				continue;
			for(GridCell cell : getOccupiedCells(obj))
			{
				cell.setObstacle(true);
			}
		}
		
		
	}
	
	private Set<GridCell> getOccupiedCells(GameObject object)
	{
		Set<GridCell> occupiedCells = new HashSet<>();
		Vector2 originCoordinates = object.getTransform().getPosition();
		int limitX = (int) (originCoordinates.getX() + object.getRenderer().getDisp().getImage().getWidth());
		int limitY = (int) (originCoordinates.getY() + object.getRenderer().getDisp().getImage().getHeight());
		for(int i = (int)(originCoordinates.getX()); i <= limitX; i++)
		{
			for(int j = (int)(originCoordinates.getY()); j < limitY; j++)
			{
				occupiedCells.add(convertToGrid(new Vector2(i,j)));
			}
		}
		
		return occupiedCells;
		
	}
	

	/**
	 * 
	 * @param pos
	 * @return
	 * Converts vector2 position into grid position
	 */
	public GridCell convertToGrid(Vector2 pos)
	{
		String xPos = Integer.toString((int)pos.getX());
		xPos = xPos.substring(0, xPos.length()-1);
		String yPos = Integer.toString((int)pos.getY());
		yPos = yPos.substring(0, yPos.length()-1);
		
		GridCell c = getCell(Integer.parseInt(yPos), Integer.parseInt(xPos));
		return c;
		
	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 * Grid position into vector2 
	 */
	public Vector2 convertToWorld(GridCell cell)
	{
		int cellLength = CELL_LENGTH;
		double xCord = (cell.getColumn() * cellLength) + (cellLength / 2);
		double yCord = (cell.getRow() * cellLength) + (cellLength / 2);
		return new Vector2(xCord, yCord);
	}
	
	private void initializeEmptyMap()
	{
		for(int i = 0; i < gridHeight; i++)
		{
			for(int j = 0; j < gridWidth; j++)
			{
				mapGrid[i][j] = new GridCell(i, j);
				mapGrid[i][j].setObstacle(false);
			}
		}
	}
	
	
	public int getGridHeight()
	{
		return gridHeight;
	}
	
	public int getGridWidth()
	{
		return gridWidth;
	}
	
	public GridCell getCell(int r, int c)
	{
		return mapGrid[r][c];
	}
	
}
