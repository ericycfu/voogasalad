package gui_elements.buttons;

import authoring.view.ComponentAddAttributesScreen;
import game_object.ObjectAttributes;

public class CreateAttributesButton extends MainButton {

	private static final String FILENAME = "create_attributes_button.properties";
	private ObjectAttributes objAttributesInstance;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public CreateAttributesButton(ObjectAttributes objAttributesInstance) {		
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.objAttributesInstance = objAttributesInstance;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			new ComponentAddAttributesScreen(objAttributesInstance);
		});
	}
}