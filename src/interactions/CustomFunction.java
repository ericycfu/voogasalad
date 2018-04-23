package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
/**
 * 
 * @author Rayan
 * Functions that we implement which the game designer will be allowed to call when programming game
 * logic for units.
 * Designed this way to allow for flexibility. We can then make any type of function without changing the execution logic
 */

public interface CustomFunction {

	public void Execute(GameObject current, GameObject other, GameObjectManager manager);
	public CustomComponentParameterFormat getParameterFormat();
	public void setParameterFormatFields();
	public void setParameters(CustomComponentParameterFormat toFormat);
}
