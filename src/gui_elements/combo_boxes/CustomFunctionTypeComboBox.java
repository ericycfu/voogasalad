package gui_elements.combo_boxes;

import javafx.event.ActionEvent;

public class CustomFunctionTypeComboBox extends MainComboBox {
	
	private static final String FILENAME = "custom_function_type_cb.properties";
	
	public CustomFunctionTypeComboBox() { 
		super(FILENAME);
//		addElements();
		chooseElements();
	}
		
	private void chooseElements() {
    	getComboBox().setOnAction((ActionEvent ev) -> {
		
    	});
	}
}