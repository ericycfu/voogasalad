package interactions;

/**
 * 
 * @author Rayan
 *Factory to allow retrieval of CustomFunction objects. They can then be added to interactions
 *
 *
 *PROBLEM: Setting arguments for constructor.
 */

public class CustomFunctionFactory {

	
	public CustomFunctionFactory()
	{}
	
	public CustomFunction getCustomFunction(String type)
	{
		Class<?> clazz;
		try 
		{
			clazz = Class.forName("interactions." + type);
			CustomFunction comm = (CustomFunction)clazz.newInstance();
			return comm;
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			System.out.println("Custom function does not exist. Check if properly named on frontend");
			return null;
		}
		
	}
}
