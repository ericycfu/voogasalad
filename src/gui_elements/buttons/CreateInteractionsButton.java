package gui_elements.buttons;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import authoring.edit_object.ComponentAddInteractionsScreen;
import gui_elements.combo_boxes.MainComboBox;

public class CreateInteractionsButton extends MainButton {

	private static final String FILENAME = "create_interactions_button.properties";
	private AuthoringObject authoring_object;
	private TagController tag_controller;
	private MainComboBox component_tag_cb;
	private static final boolean EXPLICIT_SET_ACTION = false;
	
	public CreateInteractionsButton(AuthoringObject authoring_object, TagController tag_controller,
			MainComboBox component_tag_cb) {		
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.tag_controller = tag_controller;
		this.authoring_object = authoring_object;
		this.component_tag_cb = component_tag_cb;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			new ComponentAddInteractionsScreen(authoring_object, tag_controller, component_tag_cb);
		});
	}
}