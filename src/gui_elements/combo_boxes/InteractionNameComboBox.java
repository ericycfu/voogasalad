package gui_elements.combo_boxes;

import authoring.backend.AuthoringObject;
import authoring.backend.InteractionKeysController;
import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.MainPane;
import javafx.event.ActionEvent;

public class InteractionNameComboBox extends MainComboBox {
	
	private static final String FILENAME = "interaction_name_cb.properties";
	private InteractionKeysController interaction_controller;
	private InteractionSelectedPane interaction_selected_pane;
	
	public InteractionNameComboBox(InteractionKeysController interaction_controller, 
			MainPane interaction_selected_pane) {
		super(FILENAME);
		this.interaction_controller = interaction_controller;
		this.interaction_selected_pane = (InteractionSelectedPane) interaction_selected_pane;
		getComboBox().setEditable(true);
		addElements();
		chooseElements();
	}
	
	private void addElements() {
		for(String interaction_key : interaction_controller.getInteractionKeys()) {
			getComboBox().getItems().add(interaction_key);
		}
	}
	
	private void chooseElements() {
    	getComboBox().setOnAction((ActionEvent ev) -> {
    		if(!interaction_selected_pane.equals(null))
    			interaction_selected_pane.updateSelectedComponentsPane();
    	});
	}
}