package gui_elements.buttons;

import java.util.List;

import authoring.backend.AuthoringObject;
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

public class AddCustomFunctionsButton extends MainButton {

	private static final String FILENAME = "add_custom_functions_button.properties";
	private InteractionManager interaction_manager;
	private MainComboBox interaction_name_cb;
	private List<String> custom_function_strings;
	private MainTextField interaction_vision_range_tf;
	private MainPane interaction_selected_pane;
	private int interaction_id;
	private AuthoringObject authoring_object;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public AddCustomFunctionsButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			
		});
	}
}