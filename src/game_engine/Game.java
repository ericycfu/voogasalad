package game_engine;

import java.util.List;

/**
 * Represents the Game itself, which includes available maps, available units, and available 
 * @author andrew
 *
 */
public class Game {
	public List<String> myGameMaps;
	public GameInfo possibleUnits;
	
	/**
	 * Sets up the Game from the specified game file
	 */
	public void setUp(String filepath) {
		possibleUnits = new GameInfo();
	}
	/**
	 * Creates and runs a new GameInstance and GamePlayer for the map
	 * @param index
	 */
	public void runMap(String s) {
		if(!myGameMaps.contains(s))
			throw new IllegalArgumentException("Bad map name");
		new GameInstance(possibleUnits,s);
	}
}
