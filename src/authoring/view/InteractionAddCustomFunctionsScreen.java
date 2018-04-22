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
import gui_elements.combo_boxes.CustomFunctionTypeComboBox;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.ComponentInteractionsTitleLabel;
import gui_elements.labels.InteractionComponentTagLabel;
import gui_elements.labels.InteractionCustomFunctionsTitleLabel;
import gui_elements.labels.InteractionNameLabel;
import gui_elements.labels.AllSelectedInteractionTagsLabel;
import gui_elements.labels.CurrentSelectedInteractionComponentsLabel;
import gui_elements.labels.CustomFunctionTypeLabel;
import gui_elements.labels.InteractionVisionRangeLabel;
import gui_elements.panes.AllSelectedInteractionTagsPane;
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

public class InteractionAddCustomFunctionsScreen {
	
	private final Paint BACKGROUND = Color.BLACK;
    private final String PROPERTY_FILENAME = "data/interaction_add_custom_functions_screen.properties";
    private final String TITLE_PROPERTY = "title";
    private final String WIDTH_PROPERTY = "width";
    private final String HEIGHT_PROPERTY = "height";
    private String title;
    private int screen_width, screen_height, current_interaction_id;
    private Stage stage;
    private InteractionManager interaction_manager;
    
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private static Group root;
    
    public InteractionAddCustomFunctionsScreen(InteractionManager interaction_manager, int current_interaction_id) {
    	this.interaction_manager = interaction_manager;
    	this.current_interaction_id = current_interaction_id;
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
//		stage.setOnCloseRequest(e -> {
//			e.consume();
//		});
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
    	setTextFields();
    	setPanes();
    	setComboBoxes();
    	setButtons();
    }
    
    private void setLabels() {
    	root.getChildren().addAll(new InteractionCustomFunctionsTitleLabel().getLabel(),
    							  new CustomFunctionTypeLabel().getLabel());
    }
    
    private void setRadioButtons() {
    }
            
    private void setTextFields() {
    }
    
    private void setPanes() {
    }
    
    private void setComboBoxes() {
		root.getChildren().addAll(new CustomFunctionTypeComboBox(interaction_manager, interaction_id).getComboBox());
    }

    private void setButtons() {
    }

    public void resetElements() {
    }
    
    public Stage getStage() {
    	return stage;
    }
}