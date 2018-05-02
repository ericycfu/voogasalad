package gui_elements.combo_boxes;

import authoring.edit_object.EditCustomConditionsScreen;
import conditions.ConditionManager;
import gui_elements.factories.ComboBoxFactory;
import javafx.scene.control.ComboBox;

public class CustomConditionComboBox extends ComboBox {
	private EditCustomConditionsScreen parameterScreen;
	private ConditionManager cm;
	public CustomConditionComboBox() {
//		ComboBoxFactory.updateComboBox(this, cm.availableCustomConditions());
//		ComboBoxFactory.updateComboBox(this, availableCustomConditions());
		
		ComboBoxFactory.makeComboBox(this, e -> newConditionParameters());
	}
	
	private void newConditionParameters() {
		parameterScreen = new EditCustomConditionsScreen(cm, (String)this.getValue());
	}
	
	private void getAvailableCustomConditions() {
		
	}
	
}
