package conditions;

import java.io.Serializable;

import game_object.GameObject;
import interactions.CustomComponentParameterFormat;

/**
 * 
 * @author Rayan
 * Interface for CustomCondition methods. Slightly different from CustomFunctions. It will only act upon the object
 * it is assigned to.
 */

//may combine with custom functions and allow conditions to act upon other objects

public interface CustomCondition extends Serializable{
	
	public void Execute(GameObject current);
	public CustomComponentParameterFormat getParameterFormat();
	public void setParameterFormatFields();

}
