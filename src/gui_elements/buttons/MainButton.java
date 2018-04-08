package gui_elements.buttons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.Button;

public abstract class MainButton extends Button {

	private final String X_LOC_STRING = "x_loc";
	private final String Y_LOC_STRING = "y_loc";
	private final String TEXT_STRING = "text";
	private final String WIDTH_STRING = "width";
	private final String HEIGHT_STRING = "height";
	private final String STYLE_STRING = "style";
	private final String DIRECTORY_STRING = "data/button_properties/";
	private Properties properties;
	private InputStream input;
	
	public MainButton(String filename) {
		assignProperties(DIRECTORY_STRING + filename);
		setAction();
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
	  		this.setStyle(properties.getProperty(STYLE_STRING));
	  		this.setText(properties.getProperty(TEXT_STRING));
	   	} catch (IOException ex) {
	   		System.err.println("Cannot create button");
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
	  		   		System.err.println("Cannot close input file for button");
	  			}
	  		}
	  	}
	}
	
	protected abstract void setAction();
	
	public Button getButton() {
		return this;
	}	
}