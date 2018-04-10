package authoring.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ComponentAddAttributesScreen {
	private final Paint BACKGROUND = Color.BLACK;
    private final String PROPERTY_FILENAME = "data/component_add_attributes_screen.properties";
    private final String TITLE_PROPERTY = "title";
    private final String WIDTH_PROPERTY = "width";
    private final String HEIGHT_PROPERTY = "height";
    private String title;
    private int screen_width, screen_height;
    private Stage stage;
    private ObjectAttributes objAttributesInstance;
    private MainPane attribute_names_pane, attribute_values_pane;

	// Additional setup for the turtle property screen.
    private Scene myScene;
    private static Group root;
    
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
	 * Sets the stage for the turtle property screen.
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
			System.err.println("Display file input does not exist!");
		} catch (Exception ey) {
			System.err.println("The properties for the display could not be retrieved completely.");

    	} finally {
    		if (input != null) {
    			try {
    				input.close();
    			} catch (IOException e) {
    				System.err.println("Display file input cannot close!");
    			}
    		}
    	}
    }
    
    private void setGUIComponents() {
    	setLabels();
    	setPanes();
    	setComboBoxes();
    	setRadioButtons();
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
    
    private void setComboBoxes() {
    }
    
    private void setRadioButtons() {
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