package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;

public class StaggeredModifyVariable implements CustomFunction {

	public final String VARIABLE1 = "Variable 1";
	public final String INCREASE_DECREASE = "Increase or Decrease (1 or 0)";
	public final String LIMIT = "Limit";
	
	public final String VARIABLE2 = "Variable 2";
	public final String DELTA = "Delta";
	
	
	private CustomComponentParameterFormat format;
	
	private String variable1;
	private String delta1;
	private String variable2;
	private String delta2;
	
	
	public StaggeredModifyVariable()
	{
		format = new CustomComponentParameterFormat();
	}
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) {
		
		try 
		{
			ParameterParser p = new ParameterParser();
			double deltaVal1 = p.assignValidatedValue(delta1, current);
			double deltaVal2 = p.assignValidatedValue(delta2, current);
			
			double prevVal = other.accessLogic().accessAttributes().getAttribute(variable1);
			
			other.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + deltaVal);
			current.dequeueInteraction();
		} 
		catch (PropertyNotFoundException | UnmodifiableGameObjectException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("Variable 1 reaches 0 before variable 2 starts to degrade. This function allows you to stagger your variable changes i.e. make sure that"
				+ "one property reaches zero before the other property starts decreasing. Can be useful if"
				+ "you want shields to degrade before health.");
		format.addStringField(VARIABLE1);
		format.addStringField(DELTA1);
		
		format.addStringField(VARIABLE2);
		format.addStringField(DELTA2);
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) { 
		
		try 
		{
			this.variable1 = format.getParameterValue(VARIABLE1);
			this.delta1 = format.getParameterValue(DELTA1);
		
			this.variable2 = format.getParameterValue(VARIABLE2);
			this.delta2 = format.getParameterValue(DELTA2);
		} 
		catch (PropertyNotFoundException e) 
		{
			System.out.println("Improper custom function variable assignment");
		}
		catch (NumberFormatException e)
		{
			System.out.println("Improper format for variable");
		}
	}

}
