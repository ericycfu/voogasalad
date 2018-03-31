package game_object;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectAttributes {

	private Map<String, Double> attributes;
	
	
	public List<String> GetAttributeNames()
	{
		List<String> list = new ArrayList<>();
		for(Map.Entry<String, Double> entry : attributes.entrySet())
		{
			list.add(entry.getKey());
		}
		
		return list;
	}
	
	/**
	 * 
	 * @param attribute
	 * @param value
	 * Create attribute for the unit. Attributes must be created before changing their values.
	 */
	public void CreateAttribute(String attribute)
	{
		attributes.put(attribute, null);
	}
	
	/**
	 * 
	 * @param attribute
	 * @param newValue
	 * @throws NullPropertyException
	 * Set the attribute value for a unit. Attribute must have been created beforehand to be able to change it.
	 * This prevents the engine for creating and changing a mistyped attribute that may not be used or seen.
	 */
	public void SetAttributeValue(String attribute, double newValue) throws NullPropertyException
	{
		if(attributes.containsKey(attribute))
			attributes.put(attribute, newValue);
		
		throw new NullPropertyException("Cannot change non-existent property for unit");
	}
	
	/**
	 * 
	 * @param attribute
	 * @return
	 * @throws NullPropertyException
	 * 
	 * Change attributes for a game unit. Ensures that non existent attributes are not accessed
	 */
	public double getAttribute(String attribute) throws NullPropertyException
	{
		if(attributes.containsKey(attribute))
			return attributes.get(attribute);
		
		throw new NullPropertyException("Property does not exist for object");
	}
}
