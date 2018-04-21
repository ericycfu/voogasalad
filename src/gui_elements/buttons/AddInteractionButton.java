package gui_elements.buttons;

import authoring.backend.AuthoringObject;
import authoring.backend.InteractionKeysController;
import authoring.view.ComponentAddInteractionsScreen;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.Interaction;
import interactions.InteractionManager;

public class AddInteractionButton extends MainButton {

	private static final String FILENAME = "add_interaction_button.properties";
	private InteractionManager interaction_manager;
	private MainComboBox interaction_name_cb;
	private MainTextField interaction_vision_range_tf;
	private MainPane interaction_selected_pane;
	private int interaction_id;
	private AuthoringObject authoring_object;
	private InteractionKeysController interaction_keys_controller;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public AddInteractionButton(AuthoringObject authoring_object, MainComboBox interaction_name_cb, 
			MainTextField interaction_vision_range_tf, MainPane interaction_selected_pane, 
			InteractionKeysController interaction_keys_controller, ComponentAddInteractionsScreen component_add_interactions_screen,
			int interaction_id) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.authoring_object = authoring_object;
		this.interaction_name_cb = (InteractionNameComboBox) interaction_name_cb;
		this.interaction_vision_range_tf = (InteractionVisionRangeTextField) interaction_vision_range_tf;
		this.interaction_selected_pane = (InteractionSelectedPane) interaction_selected_pane;
		this.interaction_keys_controller = interaction_keys_controller;
		this.component_add_interactions_screen = component_add_interactions_screen;
		interaction_manager = authoring_object.getInteractionsManagerInstance();
		this.interaction_id = interaction_id;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			Interaction interaction = interaction_manager.getInteraction(interaction_id);
			interaction.setName(interaction_name_cb.getComboBox().getEditor().getText());
			interaction.setRange(Double.parseDouble(interaction_vision_range_tf.getTextField().getText()));
			for(Object obj : interaction_selected_pane.getPane().getChildren()) {
				AuthoringObject ao = (AuthoringObject) obj;
				interaction.addAllTags(ao.getTags());
			}
			interaction_keys_controller.addInteractionKey(interaction_name_cb.getEditor().getText(), authoring_object);
			component_add_interactions_screen.resetElements();
			component_add_interactions_screen.setInteractionID(interaction_id = interaction_manager.createInteraction());
		});
	}
}