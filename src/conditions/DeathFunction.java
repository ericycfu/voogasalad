package conditions;

import game_object.GameObject;
import game_object.GameObjectManager;
import interactions.CustomComponentParameterFormat;

public class DeathFunction implements CustomCondition {
	
	private CustomComponentParameterFormat format;
	public DeathFunction() {
		setParameterFormatFields();
	}
	@Override
	public void Execute(GameObject current) {
		
		current.setIsDead(true);
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
