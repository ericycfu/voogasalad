package gui_elements.combo_boxes;

import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.InteractionManager;
import javafx.event.ActionEvent;

public class InteractionNameComboBox extends MainComboBox {
	
	private static final String FILENAME = "interaction_name_cb.properties";
	private static final String BLANK_TEXT = "";
	private AllSelectedInteractionTagsPane all_selected_interaction_tags_pane;
	private InteractionManager interaction_manager;
	private InteractionVisionRangeTextField interaction_vision_range_tf;
	
	public InteractionNameComboBox(MainPane all_selected_interaction_tags_pane, InteractionManager interaction_manager, 
			MainTextField interaction_vision_range_tf) {
		super(FILENAME);
		this.all_selected_interaction_tags_pane = (AllSelectedInteractionTagsPane) all_selected_interaction_tags_pane;
		this.interaction_manager = interaction_manager;
		this.interaction_vision_range_tf = (InteractionVisionRangeTextField) interaction_vision_range_tf;
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
	    		int id_selected = getComboBox().getSelectionModel().getSelectedIndex() + 1;
	    		all_selected_interaction_tags_pane.changePaneWithNewName(id_selected);
	    		all_selected_interaction_tags_pane.setToOldInteractionMode();
	    		interaction_vision_range_tf.setText(interaction_manager.getInteraction(id_selected).getRange() + BLANK_TEXT);
			}
			else {
				all_selected_interaction_tags_pane.setToNewInteractionMode();
				interaction_vision_range_tf.setText(BLANK_TEXT);
			}			
    	});
	}
}