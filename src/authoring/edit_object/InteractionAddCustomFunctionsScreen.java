package authoring.edit_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import gui_elements.buttons.CustomFunctionsSaveButton;
import gui_elements.combo_boxes.CustomFunctionTypeComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.InteractionCustomFunctionsTitleLabel;
import gui_elements.labels.CreatedCustomFunctionsLabel;
import gui_elements.labels.CustomFunctionTypeLabel;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.CustomFunctionNamesPane;
import gui_elements.panes.CustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomFunction;
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
    private MainPane custom_functions_pane, custom_function_names_pane, created_custom_functions_pane;
    private MainComboBox custom_function_type_cb;
    private CustomFunction custom_function;
    
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private static Group root;
    
    public InteractionAddCustomFunctionsScreen(InteractionManager interaction_manager, int current_interaction_id,
    		MainPane created_custom_functions_pane) {
    	this.interaction_manager = interaction_manager;
    	this.current_interaction_id = current_interaction_id;
    	this.created_custom_functions_pane = (CreatedCustomFunctionsPane) created_custom_functions_pane;
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
    	setPanes();
    	setComboBoxes();
    	setButtons();
    }
    
    private void setLabels() {
    	root.getChildren().addAll(new InteractionCustomFunctionsTitleLabel().getLabel(),
    							  new CustomFunctionTypeLabel().getLabel());
    }
    
    private void setPanes() {
    	custom_functions_pane = new CustomFunctionsPane();
    	custom_function_names_pane = new CustomFunctionNamesPane();
    	
    	root.getChildren().addAll(custom_functions_pane.getPane(),
    							  custom_function_names_pane.getPane());
    }
    
    private void setComboBoxes() {
    	custom_function_type_cb = new CustomFunctionTypeComboBox(interaction_manager, 
																 current_interaction_id,
																 custom_function_names_pane,
																 custom_functions_pane,
																 custom_function);
    	
		root.getChildren().add(custom_function_type_cb.getComboBox());
    }

    private void setButtons() {
    	root.getChildren().add(new CustomFunctionsSaveButton(interaction_manager,
    														 current_interaction_id,
    														 custom_functions_pane,
    													     created_custom_functions_pane,
    														 custom_function_type_cb,
    														 this));
    }
    
    public Stage getStage() {
    	return stage;
    }
    
    public Scene getScene() {
    	return myScene;
    }
}