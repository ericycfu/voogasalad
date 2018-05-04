package authoring.edit_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import authoring.view.AuthoringView;
import game_object.ObjectAttributes;
import gui_elements.buttons.AddAttributeButton;
import gui_elements.buttons.AttributeApplyAndOkButton;
import gui_elements.labels.AttributeNameLabel;
import gui_elements.labels.AttributeValueLabel;
import gui_elements.labels.ComponentAttributesTitleLabel;
import gui_elements.panes.AttributeNamesPane;
import gui_elements.panes.AttributeValuesPane;
import gui_elements.panes.MainPane;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ComponentAddAttributesScreen implements AuthoringView {
	private static final Paint BACKGROUND = Color.BLACK;
    private static final String PROPERTY_FILENAME = "data/component_add_attributes_screen.properties";
    private static final String TITLE_PROPERTY = "title";
    private static final String WIDTH_PROPERTY = "width";
    private static final String HEIGHT_PROPERTY = "height";
    private static final String CATCH_IO_EXCEPTION_STRING = "Display file input does not exist!";
	private static final String CATCH_GENERAL_EXCEPTION_STRING = "The properties for the display could not be retrieved completely.";
	private static final String FINALLY_IO_EXCEPTION_STRING = "Display file input cannot close!";
    private String title;
    private int screen_width, screen_height;
    private Stage stage;
    private ObjectAttributes objAttributesInstance;
    private MainPane attribute_names_pane, attribute_values_pane;

	// Additional setup for the add-attributes screen.
    private Scene myScene;
    private Group root;
    
    public ComponentAddAttributesScreen(ObjectAttributes objAttributesInstance) {
    	this.objAttributesInstance = objAttributesInstance;
    	initialize();
    }

	/**
	 * Sets the scene and initializes the screen properties.
	 */
	private void initialize() {
		root = new Group();
		getProperties();
		setScene();
		setStage();
		setGUIComponents();
	}

	private void setScene() {
		myScene = new Scene(root, screen_width, screen_height, BACKGROUND);
	}

	/**
	 * Sets the stage for the add-attributes screen.
	 */
	private void setStage() {
		stage = new Stage();
		stage.setScene(myScene);
		stage.setTitle(title);
		stage.show();
		stage.setResizable(false);
	}

	/**
	 * Reads in properties from a property file and gets the screen properties.
	 */
	private void getProperties() {
		Properties menu_properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(PROPERTY_FILENAME);
			menu_properties.load(input);
			title = menu_properties.getProperty(TITLE_PROPERTY);
			screen_width = Integer.parseInt(menu_properties.getProperty(WIDTH_PROPERTY));
			screen_height = Integer.parseInt(menu_properties.getProperty(HEIGHT_PROPERTY));
		} catch (IOException ex) {
			logError(ex, Level.SEVERE, CATCH_IO_EXCEPTION_STRING);
		} catch (Exception ey) {
			logError(ey, Level.SEVERE, CATCH_GENERAL_EXCEPTION_STRING);
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
    
    private void setGUIComponents() {
    	setLabels();
    	setPanes();
    	setButtons();
    }
    
    private void setLabels() {
    	root.getChildren().addAll(new ComponentAttributesTitleLabel().getLabel(),
    					   	      new AttributeNameLabel().getLabel(),
    						      new AttributeValueLabel().getLabel());
    }
    
    private void setPanes() {
    	attribute_names_pane = new AttributeNamesPane(objAttributesInstance);
    	attribute_values_pane = new AttributeValuesPane(objAttributesInstance);
    	    	
    	root.getChildren().addAll(attribute_names_pane.getPane(),
    							  attribute_values_pane.getPane());
    }
            
    private void setButtons() {
    	root.getChildren().addAll(new AddAttributeButton(attribute_names_pane.getPane(), 
    												     attribute_values_pane.getPane()),
    						   new AttributeApplyAndOkButton(attribute_names_pane.getPane(),
    								   						 attribute_values_pane.getPane(),
    								   						 stage,
    								   						 objAttributesInstance));
    }
}