package gui_elements.combo_boxes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import interactions.CustomFunction;
import interactions.InteractionManager;
import javafx.event.ActionEvent;

public class CustomFunctionTypeComboBox extends MainComboBox {
	
	private static final String FILENAME = "custom_function_type_cb.properties";
	private static final String CUSTOM_FUNCTIONS_FILENAME = "data/CustomFunctions.properties";
	private Properties properties;
	private InputStream input;
	private Map<String, String> custom_function_map;
	private InteractionManager interaction_manager;
	private int interaction_id;
	
	public CustomFunctionTypeComboBox(InteractionManager interaction_manager, int interaction_id) { 
		super(FILENAME);
		custom_function_map = new HashMap<String, String>();
		chooseElements();
	}
		
	private void chooseElements() {
		getCustomFunctions();
    	getComboBox().setOnAction((ActionEvent ev) -> {
    		String type = getComboBox().getSelectionModel().getSelectedItem();
    		CustomFunction custom_function = interaction_manager.getInteraction(interaction_id).addCustomFunction(type);
    		
    	});
	}
	
	private void getCustomFunctions() {
		properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(CUSTOM_FUNCTIONS_FILENAME);
	  		properties.load(input);
	  		for(Object key : properties.keySet()) {
	  			String custom_function_string = (String) key;
	  			getComboBox().getItems().add(custom_function_string);
	  			custom_function_map.put(custom_function_string, properties.getProperty(custom_function_string));
	  		}
	   	} catch (IOException ex) {
	   		System.err.println("Cannot get all custom functions");
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
	  		   		System.err.println("Cannot close custom functions file");
	  			}
	  		}
	  	}
	}
}