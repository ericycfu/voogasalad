package gui_elements.buttons;

import gui_elements.tabs.DesignTab;

public class NewComponentButton extends MainButton {

	private static final String FILENAME = "new_component_button.properties";
	private DesignTab design_tab;
	private static final boolean EXPLICIT_SET_ACTION = false;
		
	public NewComponentButton(DesignTab design_tab) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.design_tab = design_tab;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			design_tab.setNewAuthoringObject();
			design_tab.resetComponents();
		});
	}
}