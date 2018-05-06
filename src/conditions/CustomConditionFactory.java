package conditions;

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
			String className = "conditions." + type;
			clazz = Class.forName(className);
			CustomCondition comm = (CustomCondition)clazz.newInstance();
			return comm;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) 
		{

			System.out.println("Custom condition does not exist. Check if properly named on frontend");
			return null;
		}
		
	}
}
