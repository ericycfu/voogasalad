package interactions;

import java.io.Serializable;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
/**
 * 
 * @author Rayan
 * Functions that we implement which the game designer will be allowed to call when programming game
 * logic for units.
 * Designed this way to allow for flexibility. We can then make any type of function without changing the execution logic
 */

public interface CustomFunction extends Serializable {

	public void Execute(GameObject current, GameObject other, GameObjectManager manager) throws PropertyNotFoundException;
	public CustomComponentParameterFormat getParameterFormat();
	public void setParameterFormatFields();
	public void setParameters(CustomComponentParameterFormat toFormat);
	public String getName();
}
