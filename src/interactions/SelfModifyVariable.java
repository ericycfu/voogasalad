package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;

public class SelfModifyVariable implements CustomFunction {

	public static final String NAME = "SelfModifyVariable";
	
	public final String VARIABLE = "Variable";
	public final String DELTA = "Delta";
	public final String RATE = "Rate";
	
	private CustomComponentParameterFormat format;
	
	private String variable;
	private String delta;
	
	public SelfModifyVariable()
	{
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
	}
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager)
			throws PropertyNotFoundException {
		
		try 
		{
			
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = format.getParameterValue(DELTA);
			System.out.println("current delta value (self): " + delta);
			ParameterParser p = new ParameterParser();
			double deltaVal = p.assignValidatedValue(delta, current);
			double prevVal = current.accessLogic().accessAttributes().getAttribute(variable);
			double maxVal = current.accessLogic().accessAttributes().getMaxAttributeVal(variable);
			if(prevVal + deltaVal <= maxVal)
			{
				current.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + deltaVal);
				System.out.println(prevVal+deltaVal);
			}
			else
			{
				double finalDelta = (prevVal + deltaVal) - maxVal;
				current.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + finalDelta);

			}
			current.getRenderer().flashUnit();

		} 
		catch (PropertyNotFoundException | UnmodifiableGameObjectException e) 
		{
			e.printStackTrace();
		}

	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() 
	{

		format.addHelpText("This function allows you to change a variable in object calling the interation when the "
				+ "interaction occurs. Variable = Variable you can change. Delta = The change that must take place. The Delta "
				+ "can either be a number or an attribute in the player");
		format.addStringField(VARIABLE);
		format.addStringField(DELTA);
	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {

		try 
		{
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = format.getParameterValue(DELTA);
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

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return NAME;
	}

	@Override
	public boolean isRepetitive() {
		// TODO Auto-generated method stub
		return true;
	}

}
