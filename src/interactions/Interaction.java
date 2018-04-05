package interactions;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Codes for an action that occurs in the game. Different actions might simulate a GameObject acting on itself or acting on another object
 * @author andrew, Rayan
 *
 */
public class Interaction {
	
	private List<String> targetTags;
	
	//store functions by id
	private Map<Integer, CustomFunction> customFunctions;
	
	public Interaction() {
		customFunctions = new TreeMap<>();
	}
	
	public void addCustomFunction(String type)
	{
		CustomFunctionFactory factory = new CustomFunctionFactory();
			
		//this is where i need to make it better
		CustomFunction function = factory.getCustomFunction(type);
		
	}
	
 	
}
