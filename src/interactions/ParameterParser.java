package interactions;

import java.util.regex.Pattern;

/**
 * 
 * @author Rayan
 * Parses input in CustomFunction/CustomCondition parameters
 */

public class ParameterParser {
	
	public ParameterParser(){}
	
	public boolean isDouble(String text)
	{
		String decimalPattern = "([0-9]*)\\.([0-9]*)";  
		return (Pattern.matches(decimalPattern, text));
	}
}
