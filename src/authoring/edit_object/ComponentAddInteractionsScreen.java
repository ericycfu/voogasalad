package authoring.edit_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import gui_elements.buttons.AddCustomFunctionsButton;
import gui_elements.buttons.AddInteractionButton;
import gui_elements.buttons.InteractionImageChooserButton;
import gui_elements.buttons.InteractionOkButton;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.InteractionTargetTeamComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.AllSelectedInteractionTagsLabel;
import gui_elements.labels.ComponentInteractionsTitleLabel;
import gui_elements.labels.CreatedCustomFunctionsLabel;
import gui_elements.labels.CurrentSelectedInteractionComponentsLabel;
import gui_elements.labels.InteractionComponentTagLabel;
import gui_elements.labels.InteractionDescriptionLabel;
import gui_elements.labels.InteractionImageChoiceTextLabel;
import gui_elements.labels.InteractionImageChooserLabel;
import gui_elements.labels.InteractionNameLabel;
import gui_elements.labels.InteractionRateLabel;
import gui_elements.labels.InteractionTargetTeamLabel;
import gui_elements.labels.InteractionVisionRangeLabel;
import gui_elements.labels.MainLabel;
import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.CurrentSelectedInteractionComponentsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionDescriptionTextField;
import gui_elements.text_fields.InteractionRateTextField;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.InteractionManager;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ComponentAddInteractionsScreen {

	private static final Paint BACKGROUND = Color.BLACK;
    private static final String PROPERTY_FILENAME = "data/component_add_interactions_screen.properties";
    private static final String TITLE_PROPERTY = "title";
    private static final String WIDTH_PROPERTY = "width";
    private static final String HEIGHT_PROPERTY = "height";
    private static final String CATCH_IO_EXCEPTION_STRING = "Display file input does not exist!";
	private static final String CATCH_GENERAL_EXCEPTION_STRING = "The properties for the display could not be retrieved completely.";
	private static final String FINALLY_IO_EXCEPTION_STRING = "Display file input cannot close!";
    private String title;
    private int screen_width, screen_height, interaction_id;
    private Stage stage;
    private InteractionManager interaction_manager;
    private TagController tag_controller;
    private MainPane all_selected_interaction_tags_pane, current_selected_interaction_components_pane, created_custom_functions_pane;
    private MainComboBox interaction_component_tag_cb, interaction_name_cb, interaction_target_team_cb, component_tag_cb;
    private MainTextField interaction_vision_range_tf, interaction_description_tf, interaction_rate_tf;
    private MainLabel interaction_image_choice_text_label;
	
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private Group root;
    
    public ComponentAddInteractionsScreen(AuthoringObject authoring_object, TagController tag_controller,
    		MainComboBox component_tag_cb) {
    	this.tag_controller = tag_controller;
    	this.component_tag_cb = component_tag_cb;
    	interaction_manager = authoring_object.getInteractionsManagerInstance();
    	interaction_id = interaction_manager.createInteraction();
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
    	setTextFields();
    	setPanes();
    	setComboBoxes();
    	setButtons();
    }
    
    private void setLabels() {
    	
    	interaction_image_choice_text_label = new InteractionImageChoiceTextLabel();
    	
    	root.getChildren().addAll(new ComponentInteractionsTitleLabel().getLabel(),
    							  new InteractionNameLabel().getLabel(),
    							  new InteractionComponentTagLabel().getLabel(),
    							  new InteractionVisionRangeLabel().getLabel(),
  								  new CurrentSelectedInteractionComponentsLabel().getLabel(),
  								  new AllSelectedInteractionTagsLabel().getLabel(),
  								  new InteractionImageChooserLabel().getLabel(),
  								  interaction_image_choice_text_label.getLabel(),
    							  new CreatedCustomFunctionsLabel().getLabel(),
    							  new InteractionDescriptionLabel().getLabel(),
    							  new InteractionTargetTeamLabel().getLabel(),
    							  new InteractionRateLabel().getLabel());
    }
    
    private void setTextFields() {
		interaction_vision_range_tf = new InteractionVisionRangeTextField();
		interaction_description_tf = new InteractionDescriptionTextField();
		interaction_rate_tf = new InteractionRateTextField();
		
		root.getChildren().addAll(interaction_vision_range_tf.getTextField(),
								  interaction_description_tf.getTextField(),
								  interaction_rate_tf.getTextField());
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
    	interaction_target_team_cb = new InteractionTargetTeamComboBox();
    	interaction_name_cb = new InteractionNameComboBox(all_selected_interaction_tags_pane,
														  created_custom_functions_pane,
														  interaction_manager,
														  interaction_vision_range_tf,
														  interaction_rate_tf,
														  interaction_target_team_cb,
														  interaction_description_tf,
														  interaction_image_choice_text_label);
    	interaction_component_tag_cb = new InteractionComponentTagComboBox(tag_controller, 
    																	   all_selected_interaction_tags_pane,
    																	   current_selected_interaction_components_pane);
    			
		root.getChildren().addAll(interaction_target_team_cb.getComboBox(),
								  interaction_component_tag_cb.getComboBox(),
								  interaction_name_cb.getComboBox());
    }

    private void setButtons() {
    	root.getChildren().addAll(new AddInteractionButton(interaction_manager,
    													   interaction_name_cb,
    													   interaction_vision_range_tf,
    													   all_selected_interaction_tags_pane,
    													   interaction_image_choice_text_label,
    													   interaction_description_tf,
    													   interaction_target_team_cb,
    													   interaction_rate_tf,
    													   this,
    													   interaction_id,
    													   interaction_component_tag_cb,
    													   component_tag_cb,
    													   tag_controller).getButton(),
    							  new AddCustomFunctionsButton(interaction_manager,
    									  					   created_custom_functions_pane,
    									  					   this),
    							  new InteractionOkButton(interaction_manager, 
    									  				  this),
    							  new InteractionImageChooserButton(interaction_image_choice_text_label));
    }

    public void resetElements() {
    	all_selected_interaction_tags_pane.getPane().getChildren().clear();
    	current_selected_interaction_components_pane.getPane().getChildren().clear();
    	created_custom_functions_pane.getPane().getChildren().clear();
    	component_tag_cb.getEditor().clear();
    	interaction_name_cb.getEditor().clear();
    	interaction_component_tag_cb.getEditor().clear();
    	interaction_vision_range_tf.clear();
    	interaction_image_choice_text_label.setText(null);
    	interaction_description_tf.clear();
    	interaction_target_team_cb.getSelectionModel().clearSelection();
    	interaction_rate_tf.clear();
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