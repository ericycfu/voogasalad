package gui_elements.combo_boxes;

import authoring.backend.TagController;
import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.InteractionSelectionsPane;
import gui_elements.panes.MainPane;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InteractionComponentTagComboBox extends MainComboBox {

	private static final String FILENAME = "interaction_component_tag_cb.properties";
	private TagController tag_controller;
	private InteractionSelectedPane interaction_selected_pane;
	private InteractionSelectionsPane interaction_selections_pane;
	
	public InteractionComponentTagComboBox(TagController tag_controller, MainPane interaction_selected_pane, 
			MainPane interaction_selections_pane) {
		super(FILENAME);
		this.tag_controller = tag_controller;
		this.interaction_selected_pane = (InteractionSelectedPane) interaction_selected_pane;
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
    		try {
				String tag_entered = getComboBox().getEditor().getText();
				if(getComboBox().getItems().contains(tag_entered)) {
					String tag_selected = getComboBox().getSelectionModel().getSelectedItem();
	    			interaction_selected_pane.updatePane(tag_selected);
	    			interaction_selections_pane.updatePane(tag_selected);
				}
				else {
					interaction_selections_pane.getPane().getChildren().clear();
				}
    		} catch (Exception e) {
				System.err.println("Interaction tag does not exist");
			}
    	});
	}	
}