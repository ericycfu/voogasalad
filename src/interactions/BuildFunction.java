package interactions;

import game_object.GameObject;

public class BuildFunction implements CustomFunction {
	private double buildTime;
	private CustomFunctionParameterFormat format;
	@Override
	public void Execute(GameObject current, GameObject other) {
		try {
			Thread.sleep((long) (buildTime * 1000));
		} catch (InterruptedException e) {}
		current.dequeueInteraction();
		
	}

	@Override
	public CustomFunctionParameterFormat getParameterFormat() {
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		format.addHelpText("This function allows you to build a unit");
		format.addStringField("buildTime");	
		
	}

}
