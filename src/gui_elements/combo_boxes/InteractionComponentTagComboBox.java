package gui_elements.combo_boxes;

import authoring.backend.TagController;
import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.CurrentSelectedInteractionComponentsPane;
import gui_elements.panes.MainPane;
import javafx.event.ActionEvent;

public class InteractionComponentTagComboBox extends MainComboBox {

	private static final String FILENAME = "interaction_component_tag_cb.properties";
	private TagController tag_controller;
	private AllSelectedInteractionTagsPane all_selected_interaction_tags_pane;
	private CurrentSelectedInteractionComponentsPane current_selected_interaction_components_pane;
	
	public InteractionComponentTagComboBox(TagController tag_controller, MainPane all_selected_interaction_tags_pane, 
			MainPane current_selected_interaction_components_pane) {
		super(FILENAME);
		this.tag_controller = tag_controller;
		this.all_selected_interaction_tags_pane = (AllSelectedInteractionTagsPane) all_selected_interaction_tags_pane;
		this.current_selected_interaction_components_pane = (CurrentSelectedInteractionComponentsPane) current_selected_interaction_components_pane;
		getComboBox().setEditable(true);
		addElements();
		chooseElements();
	}
	
	private void addElements() {
		for(String tag : tag_controller.getTags()) {
			getComboBox().getItems().add(tag);
		}
	}
	
	private void chooseElements() {
    	getComboBox().setOnAction((ActionEvent ev) -> {
    		try {
				String tag_entered = getComboBox().getEditor().getText();
				if(getComboBox().getItems().contains(tag_entered)) {
					String tag_selected = getComboBox().getSelectionModel().getSelectedItem();
	    			all_selected_interaction_tags_pane.updatePane(tag_selected);
	    			current_selected_interaction_components_pane.updatePane(tag_selected);
				}
				else {
					current_selected_interaction_components_pane.getPane().getChildren().clear();
				}
    		} catch (Exception e) {
				System.err.println("Interaction tag does not exist");
			}
    	});
	}	
}