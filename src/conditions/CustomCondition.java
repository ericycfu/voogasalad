package conditions;

import game_object.GameObject;
import interactions.CustomFunctionParameterFormat;

public interface CustomCondition {
	
	public void Execute(GameObject current);
	public CustomFunctionParameterFormat getParameterFormat();
	public void setParameterFormatFields();

}
