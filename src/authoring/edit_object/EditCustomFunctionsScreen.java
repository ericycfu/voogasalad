package authoring.edit_object;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	private static final Paint BACKGROUND = Color.BLACK;
    private static final String PROPERTY_FILENAME = "data/edit_custom_functions_screen.properties";
    private static final String TITLE_PROPERTY = "title";
    private static final String WIDTH_PROPERTY = "width";
    private static final String HEIGHT_PROPERTY = "height";
    private static final String CATCH_IO_EXCEPTION_STRING = "Display file input does not exist!";
	private static final String CATCH_GENERAL_EXCEPTION_STRING = "The properties for the display could not be retrieved completely.";
	private static final String FINALLY_IO_EXCEPTION_STRING = "Display file input cannot close!";
    private String title;
    private int screen_width, screen_height;
    private Stage stage;
    private MainPane edit_custom_functions_pane;
    private CustomComponentParameterFormat format;
    
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private Group root;
    
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
	 * Sets the stage for the edit-custom-functions screen.
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
    
    public Stage getStage() {
    	return stage;
    }
}