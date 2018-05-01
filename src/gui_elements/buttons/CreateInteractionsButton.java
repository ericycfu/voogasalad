package gui_elements.buttons;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import authoring.edit_object.ComponentAddAttributesScreen;
import authoring.edit_object.ComponentAddInteractionsScreen;
import game_object.ObjectAttributes;
import interactions.InteractionManager;

public class CreateInteractionsButton extends MainButton {

	private static final String FILENAME = "create_interactions_button.properties";
	private TagController tag_controller;
	private static final boolean EXPLICIT_SET_ACTION = false;
	private AuthoringObject authoring_object;
	
	public CreateInteractionsButton(AuthoringObject authoring_object, TagController tag_controller) {		
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.tag_controller = tag_controller;
		this.authoring_object = authoring_object;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			new ComponentAddInteractionsScreen(authoring_object, tag_controller);
		});
	}
}