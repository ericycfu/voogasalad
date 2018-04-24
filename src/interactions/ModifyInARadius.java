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
	
	public final String NAME = "ModifyInARadius";
	public final String VARIABLE = "Variable";
	public final String DELTA = "Delta";
	public final String RADIUS = "Radius";
	
	private CustomComponentParameterFormat format;
	
	private String variable;
	private double delta;
	private double radius;
	
	public ModifyInARadius()
	{
		
		format = new CustomComponentParameterFormat();
		setParameterFormatFields();
		
	}
	
	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager) {
		
		Transform curTrans = current.getTransform();
		for(GameObject g : manager.getElements())
		{
			Transform otherTrans = g.getTransform();
			double distance = curTrans.getDisplacement(otherTrans);
			if(distance >= radius) return;
			
			double prevVal;
			
			try 
			{
				prevVal = g.accessLogic().accessAttributes().getAttribute(variable);
				g.accessLogic().accessAttributes().setAttributeValue(variable, prevVal + delta);
				current.dequeueInteraction();
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
		
		format.addHelpText("This function allows you to change a variable in another object when the "
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
			this.delta = Double.parseDouble(format.getParameterValue(DELTA));
			this.radius = Double.parseDouble(format.getParameterValue(RADIUS));
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

}
