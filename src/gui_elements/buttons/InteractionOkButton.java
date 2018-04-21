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

public class InteractionOkButton extends MainButton {

	private static final String FILENAME = "interaction_ok_button.properties";
	private InteractionManager interaction_manager;
	private int interaction_id;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public InteractionOkButton(InteractionManager interaction_manager, int interaction_id, 
			ComponentAddInteractionsScreen component_add_interactions_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.interaction_manager = interaction_manager;
		this.interaction_id = interaction_id;
		this.component_add_interactions_screen = component_add_interactions_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			interaction_manager.removeElement(interaction_manager.get(interaction_id));
			component_add_interactions_screen.getStage().close();
		});
	}
}