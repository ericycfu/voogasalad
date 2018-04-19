package interactions;

import game_object.GameObject;

public class DeathFunction implements CustomFunction{
	private CustomComponentParameterFormat format;
	public DeathFunction() {
		setParameterFormatFields();
	}
	@Override
	public void Execute(GameObject current, GameObject other) {
		other.setIsDead(true);
	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return format;
	}

	@Override
	public void setParameterFormatFields() {
		format = new CustomComponentParameterFormat();
		format.addHelpText("This function allows you to instantly destroy a unit");
		
	}
	public void setParameters(CustomComponentParameterFormat toFormat) {
		
	}
	

}
