package gui_elements.buttons;

import authoring.view.ComponentAddInteractionsScreen;
import authoring.view.InteractionAddCustomFunctionsScreen;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.InteractionManager;

public class AddCustomFunctionsButton extends MainButton {

	private static final String FILENAME = "add_custom_functions_button.properties";
	private InteractionManager interaction_manager;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private CreatedCustomFunctionsPane created_custom_functions_pane;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public AddCustomFunctionsButton(InteractionManager interaction_manager, MainPane created_custom_functions_pane,
			ComponentAddInteractionsScreen component_add_interactions_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.interaction_manager = interaction_manager;
		this.created_custom_functions_pane = (CreatedCustomFunctionsPane) created_custom_functions_pane;
		this.component_add_interactions_screen = component_add_interactions_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			new InteractionAddCustomFunctionsScreen(interaction_manager, 
													component_add_interactions_screen.getCurrentInteractionID(),
													created_custom_functions_pane);
		});
	}
}