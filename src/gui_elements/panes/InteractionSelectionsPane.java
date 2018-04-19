package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.MainComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class InteractionSelectionsPane extends MainPane {

	private GridPane grid_pane;
	private String full_directory_name = DIRECTORY_STRING + "interaction_selections_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #ffffff";
	private int x, y, width, height;
	private MainComboBox interaction_component_tag_cb;
	private TagController tag_controller;
	
	public InteractionSelectionsPane(MainComboBox interaction_component_tag_cb, TagController tag_controller) {
		this.interaction_component_tag_cb = (InteractionComponentTagComboBox) interaction_component_tag_cb;
		this.tag_controller = tag_controller;
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
			grid_pane.getChildren().add(imageView);
		}
	}
}