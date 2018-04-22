package gui_elements.buttons;

import authoring.view.ComponentAddInteractionsScreen;
import interactions.InteractionManager;

public class InteractionOkButton extends MainButton {

	private static final String FILENAME = "interaction_ok_button.properties";
	private InteractionManager interaction_manager;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public InteractionOkButton(InteractionManager interaction_manager, ComponentAddInteractionsScreen component_add_interactions_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.interaction_manager = interaction_manager;
		this.component_add_interactions_screen = component_add_interactions_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			System.out.println("Number of interactions just before ok button is pressed: " + interaction_manager.getElements().size());
			interaction_manager.removeElement(interaction_manager.get(interaction_manager.getElements().size()));
			System.out.println("Number of interactions just after ok button is pressed: " + interaction_manager.getElements().size());
			component_add_interactions_screen.getStage().close();
		});
	}
}