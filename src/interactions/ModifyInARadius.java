package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import transform_library.Transform;

/**
 * 
 * @author Rayan
 * Allows users to modify variables within the radius of a unit e.g. explosion interactions
 */

public class ModifyInARadius implements CustomFunction {
	
	public static final String NAME = "ModifyInARadius";
	public static final String VARIABLE = "Variable";
	public static final String DELTA = "Delta";
	public static final String RADIUS = "Radius";
	
	private CustomComponentParameterFormat format;
	
	private String variable;
	private String delta;
	private String radius;
	
	public ModifyInARadius()
	{
		
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
		
	}
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) {
		
		Transform curTrans = current.getTransform();
		ParameterParser p = new ParameterParser();
		for(GameObject g : manager.getElements())
		{
			Transform otherTrans = g.getTransform();
			double distance = curTrans.getDisplacement(otherTrans);			
			try 
			{
				this.variable = format.getParameterValue(VARIABLE);
				this.delta = format.getParameterValue(DELTA);
				this.radius = format.getParameterValue(RADIUS);
				if(distance >= p.assignValidatedValue(radius, current)) continue;
				double deltaVal = p.assignValidatedValue(delta, current);
				double prevVal = other.accessLogic().accessAttributes().getAttribute(variable);
				double maxVal = other.accessLogic().accessAttributes().getMaxAttributeVal(variable);
				if(prevVal + deltaVal <= maxVal)
				{
					other.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + deltaVal);
				}
				else
				{
					double finalDelta = (prevVal + deltaVal) - maxVal;
					other.accessLogic().accessAttributes().setAttributeValue(variable, finalDelta);
				}
				other.getRenderer().flashUnit();

			}
			catch (PropertyNotFoundException | UnmodifiableGameObjectException e) 
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		
		format.addHelpText("This function allows you to change a variable in another object inside a radius when the "
				+ "interaction occurs. Variable = Variable you can change. Delta = The change that must take place.");
		format.addStringField(VARIABLE);
		format.addStringField(DELTA);
		format.addStringField(RADIUS);	

	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		
		try 
		{
			this.variable = format.getParameterValue(VARIABLE);
			this.delta = format.getParameterValue(DELTA);
			this.radius = format.getParameterValue(RADIUS);
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
		return false;
	}

}
