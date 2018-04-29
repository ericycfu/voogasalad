package gui_elements.combo_boxes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import authoring.view.InteractionAddCustomFunctionsScreen;
import game_object.PropertyNotFoundException;
import gui_elements.panes.CustomFunctionNamesPane;
import gui_elements.panes.CustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomComponentParameterFormat;
import interactions.CustomFunction;
import interactions.InteractionManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CustomFunctionTypeComboBox extends MainComboBox {
	
	private static final String FILENAME = "custom_function_type_cb.properties";
	private static final String CUSTOM_FUNCTIONS_FILENAME = "data/CustomFunctions.properties";
	private static final String BLANK = "";
	private static final String COLON = ":";
	private Properties properties;
	private InputStream input;
	private Map<String, String> custom_function_map;
	private InteractionManager interaction_manager;
	private CustomFunctionNamesPane custom_function_names_pane;
	private CustomFunctionsPane custom_functions_pane;
	private CustomFunction custom_function;
	private int interaction_id;
	
	public CustomFunctionTypeComboBox(InteractionManager interaction_manager, int interaction_id,
			MainPane custom_function_names_pane, MainPane custom_functions_pane,
			CustomFunction custom_function) { 
		super(FILENAME);
		this.interaction_manager = interaction_manager;
		this.interaction_id = interaction_id;
		this.custom_function_names_pane = (CustomFunctionNamesPane) custom_function_names_pane;
		this.custom_functions_pane = (CustomFunctionsPane) custom_functions_pane;
		this.custom_function = custom_function;
		custom_function_map = new HashMap<String, String>();
		chooseElements();
	}
		
	private void chooseElements() {
		getCustomFunctions();
    	getComboBox().setOnAction((ActionEvent ev) -> {
    		String type = getComboBox().getSelectionModel().getSelectedItem();
    		custom_function = interaction_manager.getInteraction(interaction_id).generateCustomFunction(type);
    		CustomComponentParameterFormat format = custom_function.getParameterFormat();
    		createInformationAlert(custom_function, format);
    		List<String> parameterList = format.getParameterList();
    		custom_function_names_pane.getPane().getChildren().clear();
    		custom_functions_pane.getPane().getChildren().clear();
    		for(int i = 0; i < parameterList.size(); i++) {
    			String parameter = parameterList.get(i);
    			custom_function_names_pane.addCustomFunctionNameLabel(parameter + COLON);
				try {
	    			String value = format.getParameterValue(parameter);
	    			custom_functions_pane.addCustomFunctionTextField(value);
				} catch (PropertyNotFoundException e) {
					custom_functions_pane.addCustomFunctionTextField(BLANK);
				}
    		}
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
	
	public CustomFunction getCurrentSelectedCustomFunction() {
		return custom_function;
	}
	
	private void createInformationAlert(CustomFunction custom_function, CustomComponentParameterFormat format) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(custom_function.getName());
		alert.setHeaderText(null);
		alert.setContentText(format.getHelpText());
		alert.showAndWait();
	}
}