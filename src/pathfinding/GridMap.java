package pathfinding;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import game_object.GameObject;
import game_object.GameObjectManager;
import transform_library.Transform;
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
	private int gridLength;
	
	public GridMap(double width, double height)
	{
		int gridHeight = (int) (height / CELL_LENGTH);
		int gridWidth = (int) (width / CELL_LENGTH);
		
		mapGrid = new GridCell[gridHeight][gridWidth];
		initializeEmptyMap();
	}
	
	//Call this whenever a building object is built or destroyed
	
	public void updateMapPositions(List<GameObject> objList)
	{
		for(GameObject obj : objList)
		{
			if(!obj.isBuilding()) break;
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
		
		return getCell(Integer.parseInt(yPos), Integer.parseInt(xPos));
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
		for(int i = 0; i < gridLength; i++)
		{
			for(int j = 0; j < gridLength; j++)
			{
				mapGrid[i][j] = new GridCell(i, j);
				mapGrid[i][j].setObstacle(false);
			}
		}
	}
	
	
	public double getGridLength()
	{
		return gridLength;
	}
	
	public GridCell getCell(int r, int c)
	{
		return mapGrid[r][c];
	}
	
}
