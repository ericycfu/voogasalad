package gui_elements.buttons;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BuildCostAddButton extends MainButton {

	
	private static final String FILENAME = "add_build_cost_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	
	
	public BuildCostAddButton(EventHandler<ActionEvent> event) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.setOnAction(event);
	}

	@Override
	protected void setAction() {
		// TODO Auto-generated method stub
		
	}

}
