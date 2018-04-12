package gui_elements.buttons;

import authoring.view.ComponentAddAttributesScreen;
import authoring.view.ComponentAddInteractionsScreen;
import game_object.ObjectAttributes;

public class CreateInteractionsButton extends MainButton {

	private static final String FILENAME = "create_interactions_button.properties";
	private ObjectAttributes objAttributesInstance;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public CreateInteractionsButton(ObjectAttributes objAttributesInstance) {		
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.objAttributesInstance = objAttributesInstance;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			new ComponentAddInteractionsScreen(objAttributesInstance);
		});
	}
}