package gui_elements.buttons;

import java.util.List;

import authoring.backend.AuthoringObject;
import authoring.backend.InteractionKeysController;
import authoring.backend.TagController;
import authoring.view.ComponentAddAttributesScreen;
import authoring.view.ComponentAddInteractionsScreen;
import game_object.ObjectAttributes;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.CustomFunction;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.scene.Node;

public class AddInteractionButton extends MainButton {

	private static final String FILENAME = "add_interaction_button.properties";
	private InteractionManager interaction_manager;
	private MainComboBox interaction_name_cb;
	private List<String> custom_function_strings;
	private MainTextField interaction_vision_range_tf;
	private MainPane interaction_selected_pane;
	private Interaction interaction;
	private AuthoringObject authoring_object;
	private InteractionKeysController interaction_keys_controller;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public AddInteractionButton(AuthoringObject authoring_object, List<String> custom_function_strings, 
			MainComboBox interaction_name_cb, MainTextField interaction_vision_range_tf, MainPane interaction_selected_pane, 
			InteractionKeysController interaction_keys_controller, ComponentAddInteractionsScreen component_add_interactions_screen) {		
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.authoring_object = authoring_object;
		this.custom_function_strings = custom_function_strings;
		this.interaction_name_cb = (InteractionNameComboBox) interaction_name_cb;
		this.interaction_vision_range_tf = (InteractionVisionRangeTextField) interaction_vision_range_tf;
		this.interaction_selected_pane = (InteractionSelectedPane) interaction_selected_pane;
		this.interaction_keys_controller = interaction_keys_controller;
		this.component_add_interactions_screen = component_add_interactions_screen;
		interaction_manager = authoring_object.getInteractionsManagerInstance();
		interaction = new Interaction();
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			interaction.setName(interaction_name_cb.getComboBox().getEditor().getText());
			System.out.println(interaction_name_cb.getComboBox().getEditor().getText());
			System.out.println(interaction_vision_range_tf.getTextField().getText());
			interaction.setRange(Double.parseDouble(interaction_vision_range_tf.getTextField().getText()));
			interaction.addToManager(interaction_manager);
			for(Object obj : interaction_selected_pane.getPane().getChildren()) {
				AuthoringObject ao = (AuthoringObject) obj;
				interaction.addAllTags(ao.getTags());
				interaction.addTargetAuthoringObject(ao);
			}
			interaction.addAllCustomFunctions(custom_function_strings);
			interaction_keys_controller.addInteractionKey(interaction_name_cb.getEditor().getText(), authoring_object);
			component_add_interactions_screen.resetElements();
		});
	}
}