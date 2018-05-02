package authoring.edit_map;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.support.DraggableImageView;
import gui_elements.buttons.ObjectTeamSaveButton;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.combo_boxes.ObjectTeamComboBox;
import gui_elements.labels.ObjectTeamLabel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ObjectTeamSelectionScreen {
	
	private final Paint BACKGROUND = Color.BLACK;
    private final String PROPERTY_FILENAME = "data/object_team_selection_screen.properties";
    private final String TITLE_PROPERTY = "title";
    private final String WIDTH_PROPERTY = "width";
    private final String HEIGHT_PROPERTY = "height";
    private String title;
    private int screen_width, screen_height;
    private Stage stage;
    private AuthoringObject authoring_object;
    private MainComboBox object_team_cb;
    private DraggableImageView draggable_image_view;
    
	// Additional setup for the add-interactions screen.
    private Scene myScene;
    private static Group root;
    
    public ObjectTeamSelectionScreen(DraggableImageView draggable_image_view, AuthoringObject authoring_object) {
    	this.draggable_image_view = draggable_image_view;
    	this.authoring_object= authoring_object;
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
    	setComboBoxes();
    	setButtons();
    }
        
    private void setLabels() {
    	root.getChildren().add(new ObjectTeamLabel().getLabel());
    }

    private void setComboBoxes() {
    	
    	object_team_cb = new ObjectTeamComboBox(authoring_object);
    	
    	root.getChildren().add(object_team_cb.getComboBox());
    }
    
    private void setButtons() {
    	root.getChildren().add(new ObjectTeamSaveButton(authoring_object,
    													object_team_cb,
    													draggable_image_view,
    													this).getButton());
    }

    public Stage getStage() {
    	return stage;
    }
}