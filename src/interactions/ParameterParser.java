package interactions;

import java.util.regex.Pattern;

import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;

/**
 * 
 * @author Rayan
 * Parses input in CustomFunction/CustomCondition parameters
 */

public class ParameterParser {
	
	public ParameterParser(){}
	
	private boolean isDouble(String text)
	{
		String decimalPattern = "([0-9]*)\\.([0-9]*)";  
		return (Pattern.matches(decimalPattern, text));
	}
	
	public double assignValidatedValue(String val, GameObject obj) throws PropertyNotFoundException, UnmodifiableGameObjectException
	{
		if(isDouble(val))
		{
			return Double.parseDouble(val);
		}
		else
		{
			return obj.accessLogic().accessAttributes().getAttribute(val);
		}
	}
}