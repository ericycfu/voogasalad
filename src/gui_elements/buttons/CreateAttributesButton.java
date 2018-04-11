package gui_elements.buttons;

import authoring.view.ComponentAddAttributesScreen;
import game_object.ObjectAttributes;

public class CreateAttributesButton extends MainButton {

	private static final String FILENAME = "create_attributes_button.properties";
	private ObjectAttributes objAttributesInstance;
	
	public CreateAttributesButton(ObjectAttributes objAttributesInstance) {		
		super(FILENAME);
		this.objAttributesInstance = objAttributesInstance;
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			new ComponentAddAttributesScreen(objAttributesInstance);
		});
	}
}