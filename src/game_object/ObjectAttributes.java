package game_object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Rayan
 * All the attributes of the game object are managed here. Variable must be created before it can be changed or set.
 */

public class ObjectAttributes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Double> attributes;
	private Map<String, Double> maxAttributes;
	private Map<String, Double> buildCosts;
	private double buildTime;
	
	
	public ObjectAttributes()
	{
		attributes = new HashMap<String, Double>();
		maxAttributes = new HashMap<String, Double>();
		buildCosts = new HashMap<String, Double>();
	}
	
	public List<String> getAttributeNames() 
	{
		List<String> list = new ArrayList<>();
		for(String attribute : attributes.keySet()) {
			list.add(attribute);
		}		
		return list;
	}
	
	public List<Double> getAttributeValues() 
	{
		List<Double> list = new ArrayList<>();
		for(String attribute : attributes.keySet()) {
			list.add(attributes.get(attribute));
		}		
		return list;
	}
	
	
	/**
	 * 
	 * @param attribute
	 * @param value
	 * Create attribute for the unit. Attributes must be created before changing their values.
	 */
	public void createAttribute(String attribute, double maxVal)
	{
		maxAttributes.put(attribute, maxVal);
		attributes.put(attribute, maxVal);
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
		else
		{
			throw new PropertyNotFoundException("Cannot change non-existent property for unit");
		}
	}
	
	public void setMaximumAttributeValue(String attribute, double newMaxValue) throws PropertyNotFoundException
	{
		if(attributes.containsKey(attribute) && maxAttributes.containsKey(attribute))
		{
			attributes.put(attribute, newMaxValue);
			maxAttributes.put(attribute, newMaxValue);
		}
		else
		{
			throw new PropertyNotFoundException("Cannot change non-existent property for unit");
		}
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
		else throw new PropertyNotFoundException("Property does not exist for object: " + attribute);
	}	
	
	public double getMaxAttributeVal(String attribute) throws PropertyNotFoundException
	{
		if(maxAttributes.containsKey(attribute))
			return maxAttributes.get(attribute);
		else throw new PropertyNotFoundException("Property does not exist for object: " + attribute);
	}
	
	public Map<String, Double> getCosts() {
		return buildCosts;
	}
	
	public void setCosts(Map<String, Double> costMap)
	{
		for(Map.Entry<String, Double> map : costMap.entrySet())
		{
			buildCosts.put(map.getKey(), map.getValue());
		}
	}

	public double getBuildTime() 
	{
		return buildTime;
	}

	public void setBuildTime(double buildTime) 
	{
		this.buildTime = buildTime;
	}
	
	public void clearAttributes()
	{
		attributes.clear();
		maxAttributes.clear();
	}
}