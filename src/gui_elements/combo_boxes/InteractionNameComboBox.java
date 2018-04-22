package gui_elements.combo_boxes;

import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.MainPane;
import interactions.InteractionManager;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InteractionNameComboBox extends MainComboBox {
	
	private static final String FILENAME = "interaction_name_cb.properties";
	private InteractionSelectedPane interaction_selected_pane;
	private InteractionManager interaction_manager;
	
	public InteractionNameComboBox(MainPane interaction_selected_pane, InteractionManager interaction_manager) {
		super(FILENAME);
		this.interaction_selected_pane = (InteractionSelectedPane) interaction_selected_pane;
		this.interaction_manager = interaction_manager;
		getComboBox().setEditable(true);
		addElements();
		chooseElements();
	}
	
	private void addElements() {
		System.out.println("Number of interactions just as interaction screen pops up: " + interaction_manager.getElements().size());
		for(int i = 1; i < interaction_manager.getElements().size(); i++) {
			getComboBox().getItems().add(interaction_manager.getInteraction(i).getName());
		}
	}
	
	private void chooseElements() {
    	getComboBox().setOnAction((ActionEvent ev) -> {
			String name_entered = getComboBox().getEditor().getText();
			if(getComboBox().getItems().contains(name_entered)) {
	    		int id_selected = getComboBox().getSelectionModel().getSelectedIndex() + 1;
	    		interaction_selected_pane.changePaneWithNewName(id_selected);
	    		interaction_selected_pane.setToOldInteractionMode();
			}
			else {
				interaction_selected_pane.setToNewInteractionMode();
			}			
    	});
	}
}