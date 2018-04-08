package interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.ObjectLogic;
import transform_library.Transform;

/**
 * Codes for an action that occurs in the game. Different actions might simulate a GameObject acting on itself or acting on another object
 * @author andrew, Rayan
 *
 */
public class Interaction {
	
	private int id;
	private List<String> targetTags;
	
	
	//store functions by id
	private List<CustomFunction> customFunctions;
	private double range;
	
	public Interaction(InteractionManager manager) {
		
		customFunctions = new ArrayList<>();
		targetTags = new ArrayList<>();
		addToInteractionManager(manager);
	}
	
	public void addCustomFunction(String type)
	{
		CustomFunctionFactory factory = new CustomFunctionFactory();
			
		//this is where i need to make it better
		CustomFunction function = factory.getCustomFunction(type);
		customFunctions.add(function);
	}
	
	
	/**
	 * Runs all the custom functions in the interactions
	 * Each custom function can affect the other game object
	 */
	public void executeCustomFunctions(GameObject current, GameObject other)
	{
		for(CustomFunction cFunc : customFunctions)
		{
			cFunc.Execute(current, other);
		}
	}
	
	public void addToInteractionManager(InteractionManager manager)
	{
		setID(manager.addElementToManager(this));
	}
	
	public void setRange(double range)
	{
		this.range = range;
	}
	
	public double getRange()
	{
		return range;
	}
	
 	public void setID(int id)
 	{
 		this.id = id;
 	}
 	
 	public int getID()
 	{
 		return id;
 	}
}
