package interactions;

import game_object.GameObject;

public class DeathFunction implements CustomFunction{
	private CustomFunctionParameterFormat format;
	@Override
	public void Execute(GameObject current, GameObject other) {
		
		other.setIsDead(true);
		
	}

	@Override
	public CustomFunctionParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		format = new CustomFunctionParameterFormat();
		format.addHelpText("This function allows you to instantly destroy a unit");
		
	}
	

}
