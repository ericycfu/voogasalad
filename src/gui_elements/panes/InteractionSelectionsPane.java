package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.backend.TagController;
import game_object.ObjectAttributes;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.MainComboBox;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InteractionSelectionsPane extends MainPane {

	private GridPane grid_pane;
	private String full_directory_name = DIRECTORY_STRING + "interaction_selections_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #ffffff";
	private int x, y, width, height;
	private static final int TEXTFIELD_SIZE = 10;
	private static final double DEFAULT_ATTRIBUTE_VALUE = 0.0;
	private static final Pos TEXTFIELD_POSITION = Pos.CENTER;	
	private MainComboBox interaction_component_tag_cb;
	private TagController tag_controller;
	private MainPane interaction_selected_pane;
	
	public InteractionSelectionsPane(MainComboBox interaction_component_tag_cb, TagController tag_controller,
			MainPane interaction_selected_pane) {
		this.interaction_component_tag_cb = (InteractionComponentTagComboBox) interaction_component_tag_cb;
		this.tag_controller = tag_controller;
		this.interaction_selected_pane = (InteractionSelectedPane) interaction_selected_pane;
		initialize();
	}
	
	public void initialize() {
		getProperties();
		createPane();
	}
	
	@Override
	protected void getProperties() {
		properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(full_directory_name);
	  		properties.load(input);

	  		x = Integer.parseInt(properties.getProperty(X_LOC_STRING));
	  		y = Integer.parseInt(properties.getProperty(Y_LOC_STRING));
	  		width = Integer.parseInt(properties.getProperty(WIDTH));
	  		height = Integer.parseInt(properties.getProperty(HEIGHT));
	   	} catch (IOException ex) {
//	   		E
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
//	  				E
	  			}
	  		}
	  	}
	}

	@Override
	protected void createPane() {
		grid_pane = new GridPane();
		grid_pane.setLayoutX(x);
		grid_pane.setLayoutY(y);
		grid_pane.setPrefSize(width, height);
		grid_pane.setStyle(PANE_STYLE);
	}
	
	@Override
	protected void setX(int x) {
		grid_pane.setLayoutX(x);
	}

	@Override
	protected void setY(int y) {
		grid_pane.setLayoutY(y);
	}

	@Override
	protected int getX() {
		return (int) grid_pane.getLayoutX();
	}

	@Override
	protected int getY() {
		return (int) grid_pane.getLayoutY();
	}
	
	@Override
	public Pane getPane() {
		return grid_pane;
	}
	
	public void updateSelectionComponentsPane() {
		grid_pane.getChildren().clear();
		Map<String, List<AuthoringObject>> tagMap = tag_controller.getTagMap();
		String tag_selected = interaction_component_tag_cb.getSelectionModel().getSelectedItem();
		List<AuthoringObject> authoring_objects = tagMap.get(tag_selected);
		for(AuthoringObject ao : authoring_objects) {
//			grid_pane.getChildren().add(new Button(ao.getName()));
			ImageView imageView = new ImageView(ao.getImage());
			addImageViewProperties(imageView);
			grid_pane.getChildren().add(imageView);
		}
	}
	
	private void addImageViewProperties(ImageView imageView) {
		imageView.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
    		if(e.isPrimaryButtonDown()) {
    			List<Node> imageViews = interaction_selected_pane.getPane().getChildren();
    			if(e.getClickCount() == 2 && !imageViews.contains(imageView)) {
    				imageViews.add(imageView);
            	}
            }
        });
	}
}