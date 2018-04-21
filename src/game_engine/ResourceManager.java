package game_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import game_object.PropertyNotFoundException;

/**
 * 
 * @author Rayan
 * Object that manages resources for each team.
 * Has certain limitations to ensure that improperly named resources are ignored.
 */
public class ResourceManager {

	private Map<String, Double> resourceMap;
	
	public ResourceManager()
	{
		resourceMap = new HashMap<>();
	}
	
	/**
	 * 
	 * @param resourceMap
	 * Constructor defined for copying purposes. 
	 */
	public ResourceManager(Map<String, Double> resourceMap)
	{
		this.resourceMap = resourceMap;
	}
	
	/**
	 * 
	 * @param copy
	 * Allows for copying of the resource manager
	 */
	public ResourceManager copyResourceManager(ResourceManager copy)
	{
		Map<String, Double> map = new HashMap<>();
		for(Entry<String, Double> entry : copy.getResourceEntries())
		{
			map.put(entry.getKey(), entry.getValue());
		}
		return new ResourceManager(map);
	}
	
	/**
	 * 
	 * @param resource
	 * @param startingValue
	 * adds a resource type to the stockpile. 
	 */
	public void addResource(String resource, double startingValue)
	{
		resourceMap.put(resource, startingValue);
	}
	
	/**
	 * 
	 * @param resource
	 * @param newValue
	 * @throws InvalidResourceValueException
	 * updates the value of the resource in the stockpile
	 * Throws an exception if the user attempts to change a resource value that does not exist
	 */
	public void updateResource(String resource, double newValue) throws InvalidResourceValueException
	{
		if(resourceMap.containsKey(resource))
		{
			resourceMap.put(resource, newValue);
		}
		else
		{
			throw new InvalidResourceValueException("Resource does not exist in the game");
		}
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 * @throws PropertyNotFoundException
	 * 
	 * Get the amount of the resource in the stockpile
	 * throws an exception if a resource is accessed that does not exist in the game
	 */
	public Double getResource(String resource) throws InvalidResourceValueException
	{
		if(resourceMap.containsKey(resource))
		{
			return resourceMap.get(resource);
		}
		throw new InvalidResourceValueException("Resource doesn't exist in the game");
	}
	
	/**
	 * 
	 * @return
	 * Returns a list of entries<resource, value> which can be used for display
	 */
	public List<Entry<String, Double>> getResourceEntries()
	{
		List<Entry<String, Double>> varList = new ArrayList<>();
		
		for(Map.Entry<String, Double> var : resourceMap.entrySet())
		{
			varList.add(new SimpleEntry<>(var.getKey(), var.getValue()));
		}
		
		return varList;
	}
}
