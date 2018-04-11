package gui_elements.text_fields;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.TextField;

public class MainTextField extends TextField {

	private final String X_LOC_STRING = "x_loc";
	private final String Y_LOC_STRING = "y_loc";
	private final String WIDTH_STRING = "width";
	private final String HEIGHT_STRING = "height";
	private final String DIRECTORY_STRING = "data/text_field_properties/";
	private Properties properties;
	private InputStream input;
	
	public MainTextField(String filename) {
		assignProperties(DIRECTORY_STRING + filename);
	}
	
	private void assignProperties(String full_filename) {
		properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(full_filename);
	  		properties.load(input);	  		
	  		this.setLayoutX(Double.parseDouble(properties.getProperty(X_LOC_STRING)));
	  		this.setLayoutY(Double.parseDouble(properties.getProperty(Y_LOC_STRING)));
	  		this.setPrefSize(Double.parseDouble(properties.getProperty(WIDTH_STRING)),
	  						  Double.parseDouble(properties.getProperty(HEIGHT_STRING)));
	   	} catch (IOException ex) {
	   		System.err.println("Cannot create text field");
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
	  		   		System.err.println("Cannot close input file for text field");
	  			}
	  		}
	  	}
	}
	
	public TextField getTextField() {
		return this;
	}
}