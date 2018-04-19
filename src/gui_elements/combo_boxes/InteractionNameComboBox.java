package gui_elements.combo_boxes;

import authoring.backend.AuthoringObject;
import authoring.backend.InteractionKeysController;

public class InteractionNameComboBox extends MainComboBox {
	
	private static final String FILENAME = "interaction_name_cb.properties";
	private InteractionKeysController interaction_controller;

	public InteractionNameComboBox(InteractionKeysController interaction_controller) {
		super(FILENAME);
		getComboBox().setEditable(true);
		this.interaction_controller = interaction_controller;
		addElements();
	}
	
	private void addElements() {
		for(String interaction_key : interaction_controller.getInteractionKeys()) {
			getComboBox().getItems().add(interaction_key);
		}
	}
}