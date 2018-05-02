package authoring.edit_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import gui_elements.buttons.EditCustomFunctionsSaveButton;
import gui_elements.panes.EditCustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomComponentParameterFormat;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class EditCustomFunctionsScreen {
	
	private final Paint BACKGROUND = Color.BLACK;
    private final String PROPERTY_FILENAME = "data/edit_custom_functions_screen.properties";
    private final String TITLE_PROPERTY = "title";
    private final String WIDTH_PROPERTY = "width";
    private final String HEIGHT_PROPERTY = "height";
    private String title;
    private int screen_width, screen_height;
    private Stage stage;
    private MainPane edit_custom_functions_pane;
    private CustomComponentParameterFormat format;
    
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private static Group root;
    
    public EditCustomFunctionsScreen(CustomComponentParameterFormat format) {
    	this.format = format;
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
    	setPanes();
    	setButtons();
    }
        
    private void setPanes() {
    	edit_custom_functions_pane = new EditCustomFunctionsPane(format);
    	
    	root.getChildren().add(edit_custom_functions_pane.getPane());
    }
    
    private void setButtons() {
    	root.getChildren().add(new EditCustomFunctionsSaveButton(format,
    													     	 edit_custom_functions_pane,
    													     	 this));
    }

    public void resetElements() {
    }
    
    public Stage getStage() {
    	return stage;
    }
}