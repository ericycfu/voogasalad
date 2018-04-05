package interactions;

import game_object.ObjectLogic;

/**
 * 
 * @author Rayan
 * Functions that we implement which the game designer will be allowed to call when programming game
 * logic for units.
 * Designed this way to allow for flexibility. We can then make any type of function without changing the execution logic
 */

public interface CustomFunction {

	public void Execute(ObjectLogic obj);

}
