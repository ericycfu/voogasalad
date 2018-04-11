package conditions;

import interactions.CustomFunction;

/**
 * 
 * @author Rayan
 * Factory for creating and retrieving custom condition objects
 */
public class CustomConditionFactory {

	public CustomConditionFactory()
	{}
	
	public CustomCondition getCustomCondition(String type)
	{
		Class<?> clazz;
		try 
		{
			clazz = Class.forName("conditions." + type);
			CustomCondition comm = (CustomCondition)clazz.newInstance();
			return comm;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Custom condition does not exist. Check if properly named on frontend");
			return null;
		}
		
	}
}
