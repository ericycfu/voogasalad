package gui_elements.labels;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.control.Label;

public class LabelProperties {

	private final String X_LOC_STRING = "x_loc";
	private final String Y_LOC_STRING = "y_loc";
	private final String TEXT_STRING = "text";
	private final String WIDTH_STRING = "width";
	private final String HEIGHT_STRING = "height";
	private final String STYLE_STRING = "style";
	private final String DIRECTORY_STRING = "data/label_properties/";
	private Properties properties;
	private InputStream input;
	private Label label;
	
	public LabelProperties(String filename) {
		assignProperties(DIRECTORY_STRING + filename);
	}
	
	private void assignProperties(String full_filename) {
		label = new Label();
		properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(full_filename);
	  		properties.load(input);	  		
	  		label.setLayoutX(Integer.parseInt(properties.getProperty(X_LOC_STRING)));
	  		label.setLayoutY(Integer.parseInt(properties.getProperty(Y_LOC_STRING)));
	  		label.setPrefSize(Integer.parseInt(properties.getProperty(WIDTH_STRING)),
	  						  Integer.parseInt(properties.getProperty(HEIGHT_STRING)));
	  		label.setStyle(properties.getProperty(STYLE_STRING));
	  		System.out.println(properties.getProperty(STYLE_STRING));
	  		label.setText(properties.getProperty(TEXT_STRING));
	   	} catch (IOException ex) {
	   		System.err.println("Cannot create label");
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
	  		   		System.err.println("Cannot close input file for labels");
	  			}
	  		}
	  	}
	}
	
	public Label getLabel() {
		return label;
	}
}