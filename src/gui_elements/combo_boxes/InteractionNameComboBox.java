package gui_elements.combo_boxes;

import java.util.List;

import gui_elements.labels.InteractionDescriptionLabel;
import gui_elements.labels.MainLabel;
import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionRateTextField;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.CustomComponentParameterFormat;
import interactions.CustomFunction;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.event.ActionEvent;

public class InteractionNameComboBox extends MainComboBox {
	
	private static final String FILENAME = "interaction_name_cb.properties";
	private static final String BLANK_TEXT = "";
	private AllSelectedInteractionTagsPane all_selected_interaction_tags_pane;
	private CreatedCustomFunctionsPane created_custom_functions_pane;
	private InteractionManager interaction_manager;
	private InteractionVisionRangeTextField interaction_vision_range_tf;
	private InteractionRateTextField interaction_rate_tf;
	private InteractionTargetTeamComboBox interaction_target_team_cb;
	private InteractionDescriptionLabel interaction_description_label;
	
	public InteractionNameComboBox(MainPane all_selected_interaction_tags_pane, MainPane created_custom_functions_pane,
			InteractionManager interaction_manager, MainTextField interaction_vision_range_tf, MainTextField interaction_rate_tf,
			MainComboBox interaction_target_team_cb, MainLabel interaction_description_label) {
		super(FILENAME);
		this.all_selected_interaction_tags_pane = (AllSelectedInteractionTagsPane) all_selected_interaction_tags_pane;
		this.created_custom_functions_pane = (CreatedCustomFunctionsPane) created_custom_functions_pane;
		this.interaction_manager = interaction_manager;
		this.interaction_vision_range_tf = (InteractionVisionRangeTextField) interaction_vision_range_tf;
		this.interaction_rate_tf = (InteractionRateTextField) interaction_rate_tf;
		this.interaction_target_team_cb = (InteractionTargetTeamComboBox) interaction_target_team_cb;
		this.interaction_description_label = (InteractionDescriptionLabel) interaction_description_label;
		getComboBox().setEditable(true);
		addElements();
		chooseElements();
	}
	
	private void addElements() {
//		System.out.println("Number of interactions just as interaction screen pops up: " + interaction_manager.getElements().size());
		for(int i = 1; i < interaction_manager.getElements().size(); i++) {
			getComboBox().getItems().add(interaction_manager.getInteraction(i).getName());
		}
	}
	
	private void chooseElements() {
    	getComboBox().setOnAction((ActionEvent ev) -> {
			String name_entered = getComboBox().getEditor().getText();
			if(getComboBox().getItems().contains(name_entered)) {
	    		int selected_index = getComboBox().getSelectionModel().getSelectedIndex();
	    		Interaction interaction = interaction_manager.getElements().get(selected_index);
	    		int id_selected = interaction.getID();
	    		all_selected_interaction_tags_pane.changePaneWithNewName(id_selected);
	    		all_selected_interaction_tags_pane.setToOldInteractionMode();
	    		interaction_vision_range_tf.setText(interaction.getRange() + BLANK_TEXT);
	    		interaction_rate_tf.setText(Double.toString(interaction.getRate()));
	    		interaction_target_team_cb.getSelectionModel().select(interaction.getInteractionTargetTeam().toString());
	    		interaction_description_label.setText(interaction.getDescription());
	    		List<CustomFunction> custom_functions = interaction.getCustomFunctions();
	    		for(CustomFunction custom_function : custom_functions) {
	    			created_custom_functions_pane.addButton(custom_function.getName(), custom_function.getParameterFormat());
	    		}
			}
			else {
				all_selected_interaction_tags_pane.setToNewInteractionMode();
				interaction_vision_range_tf.setText(BLANK_TEXT);
			}			
    	});
	}
}