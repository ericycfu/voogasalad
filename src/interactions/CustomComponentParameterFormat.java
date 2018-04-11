package interactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.PropertyNotFoundException;

/**
 * 
 * @author Rayan
 * Format for how parameters should be displayed for each custom function. Frontend can access this 
 * to create the fields it needs. Alsos stores the parameter values and how they need to be passed to the backend
 */

public class CustomComponentParameterFormat {

	private String helpText;
	private Map<String, String> parameterFields;
	
	//will also include any text help we need to add
	
	public CustomComponentParameterFormat()
	{
		parameterFields = new HashMap<>();
	}
	
	/**
	 * 
	 * @param field
	 * Called in CustomFunction to set names of parameters.
	 */
	public void addStringField(String field)
	{
		parameterFields.put(field, "");
	}
	
	
	public void setFieldValue(String field, String value)
	{
		if(parameterFields.containsKey(field))
		{
			parameterFields.put(field, value);
		}
		else
		{
			System.out.println("field not declared in custom function");
		}
	}
	
	public String getParameterValue(String parameter) throws PropertyNotFoundException
	{
		if(parameterFields.containsKey(parameter))
		{
			return parameterFields.get(parameter);
		}
		
		throw new PropertyNotFoundException("Parameter does not exist for that custom function");
	}
	
	public void addHelpText(String text)
	{
		helpText = text;
	}
	
	public String getHelpText()
	{
		return helpText;
	}
	
	public List<String> getParameterList()
	{
		List<String> paraList = new ArrayList<>();
		for(Map.Entry<String, String> entry : parameterFields.entrySet())
		{
			paraList.add(entry.getKey());
		}
		return paraList;
	}
	
}
