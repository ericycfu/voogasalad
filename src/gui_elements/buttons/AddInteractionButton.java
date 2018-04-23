package gui_elements.buttons;

import authoring.view.ComponentAddInteractionsScreen;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.scene.control.Button;

public class AddInteractionButton extends MainButton {

	private static final String FILENAME = "add_interaction_button.properties";
	private InteractionManager interaction_manager;
	private MainComboBox interaction_name_cb;
	private MainTextField interaction_vision_range_tf;
	private MainPane all_selected_interaction_tags_pane;
	private int interaction_id;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public AddInteractionButton(InteractionManager interaction_manager, MainComboBox interaction_name_cb, 
			MainTextField interaction_vision_range_tf, MainPane all_selected_interaction_tags_pane, 
			ComponentAddInteractionsScreen component_add_interactions_screen, int interaction_id) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.interaction_manager = interaction_manager;
		this.interaction_name_cb = (InteractionNameComboBox) interaction_name_cb;
		this.interaction_vision_range_tf = (InteractionVisionRangeTextField) interaction_vision_range_tf;
		this.all_selected_interaction_tags_pane = (AllSelectedInteractionTagsPane) all_selected_interaction_tags_pane;
		this.component_add_interactions_screen = component_add_interactions_screen;
		this.interaction_id = interaction_id;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			Interaction interaction = interaction_manager.getInteraction(interaction_id);
			interaction.setName(interaction_name_cb.getComboBox().getEditor().getText());
			interaction_name_cb.getItems().add(interaction.getName());
			interaction.setRange(Double.parseDouble(interaction_vision_range_tf.getTextField().getText()));
			for(Object obj : all_selected_interaction_tags_pane.getPane().getChildren()) {
				interaction.addTag(((Button) obj).getText());
			}
			component_add_interactions_screen.resetElements();
			component_add_interactions_screen.setInteractionID(interaction_id = interaction_manager.createInteraction());
		});
	}
}