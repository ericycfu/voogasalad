package gui_elements.buttons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;

/**
 * @author Aditya Sridhar
 *
 */

public abstract class MainButton extends Button {

	private static final String X_LOC_STRING = "x_loc";
	private static final String Y_LOC_STRING = "y_loc";
	private static final String TEXT_STRING = "text";
	private static final String WIDTH_STRING = "width";
	private static final String HEIGHT_STRING = "height";
	private static final String STYLE_STRING = "style";
	private static final String DIRECTORY_STRING = "data/button_properties/";
    private static final String CATCH_IO_EXCEPTION_STRING = "Cannot create button!";
	private static final String FINALLY_IO_EXCEPTION_STRING = "Cannot open input file for button!!";
	
	public MainButton(String filename, boolean explicit_set_action_allowed) {
		assignProperties(DIRECTORY_STRING + filename);
		if(explicit_set_action_allowed) {
			setAction();
		}
	}
		
	private void assignProperties(String full_filename) {
		Properties properties = new Properties();
		InputStream input = null;
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
			logError(ex, Level.SEVERE, CATCH_IO_EXCEPTION_STRING);
    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				logError(e, Level.SEVERE, FINALLY_IO_EXCEPTION_STRING);
    			}
    		}
    	}
    }
	
	private void logError(Exception e, Level level, String error) {
		Logger logger = Logger.getAnonymousLogger();
		Exception ex = new Exception(e);
		logger.log(level, error, ex);
	}
	
	protected abstract void setAction();
	
	public Button getButton() {
		return this;
	}	
}