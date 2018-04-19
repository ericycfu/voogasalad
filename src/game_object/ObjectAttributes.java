package game_object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Rayan
 * All the attributes of the game object are managed here. Variable must be created before it can be changed or set.
 */

public class ObjectAttributes {

	private Map<String, Double> attributes;
	private Map<String, Double> buildCosts;
	
	public ObjectAttributes() {
		attributes = new HashMap<String, Double>();
	}
	
	public List<String> getAttributeNames() {
		List<String> list = new ArrayList<>();
		for(String attribute : attributes.keySet()) {
			list.add(attribute);
		}		
		return list;
	}
	
	public List<Double> getAttributeValues() {
		List<Double> list = new ArrayList<>();
		for(String attribute : attributes.keySet()) {
			list.add(attributes.get(attribute));
		}		
		return list;
	}
	
	public Map<String, Double> getAttributeMap() {
		return attributes;
	}
	
	
	/**
	 * 
	 * @param attribute
	 * @param value
	 * Create attribute for the unit. Attributes must be created before changing their values.
	 */
	public void createAttribute(String attribute)
	{
		attributes.put(attribute, null);
	}
	
	/**
	 * 
	 * @param attribute
	 * @param newValue
	 * @throws PropertyNotFoundException
	 * Set the attribute value for a unit. Attribute must have been created beforehand to be able to change it.
	 * This prevents the engine for creating and changing a mistyped attribute that may not be used or seen.
	 */
	public void setAttributeValue(String attribute, double newValue) throws PropertyNotFoundException
	{
		if(attributes.containsKey(attribute))
			attributes.put(attribute, newValue);
		
		throw new PropertyNotFoundException("Cannot change non-existent property for unit");
	}
	
	/**
	 * 
	 * @param attribute
	 * @return
	 * @throws PropertyNotFoundException
	 * 
	 * Change attributes for a game unit. Ensures that non existent attributes are not accessed
	 */
	public double getAttribute(String attribute) throws PropertyNotFoundException
	{
		if(attributes.containsKey(attribute))
			return attributes.get(attribute);
		
		throw new PropertyNotFoundException("Property does not exist for object");
	}	
	
	public void setCosts(Map<String, Double> costMap)
	{
		for(Map.Entry<String, Double> map : costMap.entrySet())
		{
			buildCosts.put(map.getKey(), map.getValue());
		}
	}
}