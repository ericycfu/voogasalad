package authoring.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import gui_elements.buttons.AddCustomFunctionsButton;
import gui_elements.buttons.AddInteractionButton;
import gui_elements.buttons.InteractionOkButton;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.ComponentInteractionsTitleLabel;
import gui_elements.labels.CreatedCustomFunctionsLabel;
import gui_elements.labels.InteractionComponentTagLabel;
import gui_elements.labels.InteractionNameLabel;
import gui_elements.labels.AllSelectedInteractionTagsLabel;
import gui_elements.labels.CurrentSelectedInteractionComponentsLabel;
import gui_elements.labels.InteractionVisionRangeLabel;
import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.CurrentSelectedInteractionComponentsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.InteractionManager;
import javafx.scene.Group;
import javafx.scene.Scene;
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
    private int screen_width, screen_height, interaction_id;
    private Stage stage;
    private InteractionManager interaction_manager;
    private TagController tag_controller;
    private MainPane all_selected_interaction_tags_pane, current_selected_interaction_components_pane, created_custom_functions_pane;
    private MainComboBox interaction_component_tag_cb, interaction_name_cb;
    private MainTextField interaction_vision_range_tf;
	
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private static Group root;
    
    public ComponentAddInteractionsScreen(AuthoringObject authoring_object, TagController tag_controller) {
    	this.tag_controller = tag_controller;
    	interaction_manager = authoring_object.getInteractionsManagerInstance();
    	interaction_id = interaction_manager.createInteraction();
    	System.out.println(interaction_id);
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
		stage.setOnCloseRequest(e -> {
			e.consume();
		});
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
    	setRadioButtons();
    	setTextFields();
    	setPanes();
    	setComboBoxes();
    	setButtons();
    }
    
    private void setLabels() {
    	root.getChildren().addAll(new ComponentInteractionsTitleLabel().getLabel(),
    							  new InteractionNameLabel().getLabel(),
    							  new InteractionComponentTagLabel().getLabel(),
    							  new InteractionVisionRangeLabel().getLabel(),
  								  new CurrentSelectedInteractionComponentsLabel().getLabel(),
  								  new AllSelectedInteractionTagsLabel().getLabel(),
    							  new CreatedCustomFunctionsLabel().getLabel());    							  
    }
    
    private void setRadioButtons() {
    }
            
    private void setTextFields() {
		interaction_vision_range_tf = new InteractionVisionRangeTextField();
		root.getChildren().addAll(interaction_vision_range_tf.getTextField());
    }
    
    private void setPanes() {
    	all_selected_interaction_tags_pane = new AllSelectedInteractionTagsPane(interaction_manager);
    	current_selected_interaction_components_pane = new CurrentSelectedInteractionComponentsPane(tag_controller);
    	created_custom_functions_pane = new CreatedCustomFunctionsPane();
    	
    	root.getChildren().addAll(all_selected_interaction_tags_pane.getPane(),
    							  current_selected_interaction_components_pane.getPane(),
    							  created_custom_functions_pane.getPane());
    }
    
    private void setComboBoxes() {
		interaction_name_cb = new InteractionNameComboBox(all_selected_interaction_tags_pane,
														  created_custom_functions_pane,
														  interaction_manager,
														  interaction_vision_range_tf);
    	interaction_component_tag_cb = new InteractionComponentTagComboBox(tag_controller, 
    																	   all_selected_interaction_tags_pane,
    																	   current_selected_interaction_components_pane);
		
		root.getChildren().addAll(interaction_component_tag_cb.getComboBox(),
								  interaction_name_cb.getComboBox());
    }

    private void setButtons() {
    	root.getChildren().addAll(new AddInteractionButton(interaction_manager,
    													   interaction_name_cb,
    													   interaction_vision_range_tf,
    													   all_selected_interaction_tags_pane,
    													   created_custom_functions_pane,
    													   this,
    													   interaction_id).getButton(),
    							  new AddCustomFunctionsButton(interaction_manager,
    									  					   created_custom_functions_pane,
    									  					   this),
    							  new InteractionOkButton(interaction_manager, 
    									  				  this));
    }

    public void resetElements() {
    	all_selected_interaction_tags_pane.getPane().getChildren().clear();
    	current_selected_interaction_components_pane.getPane().getChildren().clear();
    	created_custom_functions_pane.getPane().getChildren().clear();
    	interaction_name_cb.getEditor().clear();
    	interaction_component_tag_cb.getEditor().clear();
    	interaction_vision_range_tf.clear();
    }
    
    public int getCurrentInteractionID() {
    	return interaction_id;
    }
    
    public void setInteractionID(int interaction_id) {
    	this.interaction_id = interaction_id;
    }
    
    public Stage getStage() {
    	return stage;
    }
}