package scenemanager;

/**
 * 
 * @author Rayan
 * Factory that allows authoring to retrieve end conditions to be displayed
 */

public class EndConditionFactory {
	
	public EndConditionFactory()
	{}
	
	public EndCondition getEndCondition(String type)
	{
		Class<?> clazz;
		try 
		{
			clazz = Class.forName("scenemanager." + type);
			EndCondition comm = (EndCondition)clazz.newInstance();
			return comm;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("End condition does not exist. Check if properly named on frontend");
			return null;
		}
		
	}

}
