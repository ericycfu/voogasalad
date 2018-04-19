package gui_elements.combo_boxes;

import authoring.backend.TagController;
import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.InteractionSelectionsPane;
import gui_elements.panes.MainPane;
import javafx.event.ActionEvent;

public class InteractionComponentTagComboBox extends MainComboBox {

	private static final String FILENAME = "interaction_component_tag_cb.properties";
	private TagController tag_controller;
	private InteractionSelectionsPane interaction_selections_pane;
	
	public InteractionComponentTagComboBox(TagController tag_controller, 
			MainPane interaction_selections_pane) {
		super(FILENAME);
		this.tag_controller = tag_controller;
		this.interaction_selections_pane = (InteractionSelectionsPane) interaction_selections_pane;
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
    		if(!interaction_selections_pane.equals(null))
    			interaction_selections_pane.updateSelectionComponentsPane();
    	});
	}
}