package authoring.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.backend.InteractionKeysController;
import authoring.backend.TagController;
import game_object.ObjectAttributes;
import gui_elements.buttons.AddInteractionButton;
import gui_elements.combo_boxes.ComponentTagComboBox;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.AttributeNameLabel;
import gui_elements.labels.AttributeValueLabel;
import gui_elements.labels.ComponentAttributesTitleLabel;
import gui_elements.labels.ComponentInteractionsTitleLabel;
import gui_elements.labels.InteractionComponentTagLabel;
import gui_elements.labels.InteractionNameLabel;
import gui_elements.labels.InteractionSelectedLabel;
import gui_elements.labels.InteractionSelectionsLabel;
import gui_elements.labels.InteractionVisionRangeLabel;
import gui_elements.panes.AttributeNamesPane;
import gui_elements.panes.AttributeValuesPane;
import gui_elements.panes.InteractionSelectedPane;
import gui_elements.panes.InteractionSelectionsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.CustomFunction;
import interactions.Interaction;
import interactions.InteractionManager;
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

public class ComponentAddInteractionsScreen {
	private final Paint BACKGROUND = Color.BLACK;
    private final String PROPERTY_FILENAME = "data/component_add_interactions_screen.properties";
    private final String TITLE_PROPERTY = "title";
    private final String WIDTH_PROPERTY = "width";
    private final String HEIGHT_PROPERTY = "height";
    private String title;
    private int screen_width, screen_height;
    private Stage stage;
    private InteractionManager interaction_manager;
    private InteractionKeysController interaction_keys_controller;
    private TagController tag_controller;
    private MainPane interaction_selected_pane, interaction_selections_pane;
    private MainComboBox interaction_component_tag_cb, interaction_name_cb;
    private MainTextField interaction_vision_range_tf;
	private List<String> custom_function_strings;
    private AuthoringObject authoring_object;
	
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private static Group root;
    
    public ComponentAddInteractionsScreen(AuthoringObject authoring_object, TagController tag_controller) {
    	this.authoring_object = authoring_object;
    	this.tag_controller = tag_controller;
    	interaction_manager = authoring_object.getInteractionsManagerInstance();
    	interaction_keys_controller = new InteractionKeysController();
    	custom_function_strings = new ArrayList<String>();
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
	 * Sets the stage for the add-interactions screen.
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
    	setComboBoxes();
    	setRadioButtons();
    	setTextFields();
    	setPanes();
    	setButtons();
    }
    
    private void setLabels() {
    	root.getChildren().addAll(new ComponentInteractionsTitleLabel().getLabel(),
    							  new InteractionNameLabel().getLabel(),
    							  new InteractionComponentTagLabel().getLabel(),
    							  new InteractionVisionRangeLabel().getLabel(),
  								  new InteractionSelectionsLabel().getLabel(),
  								  new InteractionSelectedLabel().getLabel());    							  
    }
        
    private void setComboBoxes() {
		interaction_name_cb = new InteractionNameComboBox(interaction_keys_controller);
    	interaction_component_tag_cb = new InteractionComponentTagComboBox(tag_controller);
		
		root.getChildren().addAll(interaction_component_tag_cb.getComboBox(),
								  interaction_name_cb.getComboBox());
    }
    
    private void setRadioButtons() {
    }
            
    private void setTextFields() {
		interaction_vision_range_tf = new InteractionVisionRangeTextField();
		
		root.getChildren().addAll(interaction_vision_range_tf.getTextField());
    }
    
    private void setPanes() {
    	interaction_selected_pane = new InteractionSelectedPane(interaction_name_cb, interaction_manager);
    	interaction_selections_pane = new InteractionSelectionsPane(interaction_component_tag_cb, tag_controller, interaction_selected_pane);
    	    	
    	root.getChildren().addAll(interaction_selected_pane.getPane(),
    							  interaction_selections_pane.getPane());
    }

    private void setButtons() {
    	root.getChildren().addAll(new AddInteractionButton(authoring_object,
    													   custom_function_strings,
    													   interaction_name_cb,
    													   interaction_vision_range_tf,
    													   interaction_selected_pane,
    													   interaction_keys_controller,
    													   this).getButton());
    }

    public void resetElements() {
    	interaction_selected_pane.getPane().getChildren().clear();
    	interaction_selections_pane.getPane().getChildren().clear();
    	interaction_name_cb.getEditor().clear();
    	interaction_component_tag_cb.getEditor().clear();
    	interaction_vision_range_tf.clear();
    	custom_function_strings.clear();
    }
}