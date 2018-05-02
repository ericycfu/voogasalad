package interactions;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;

public class Teleport implements CustomFunction {

	@Override
	public void Execute(GameObject current, GameObject other, GameObjectManager manager)
			throws PropertyNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomComponentParameterFormat getParameterFormat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameterFormatFields() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setParameters(CustomComponentParameterFormat toFormat) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
