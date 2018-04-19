package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.backend.InteractionKeysController;
import authoring.backend.TagController;
import game_object.ObjectAttributes;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.MainComboBox;
import interactions.InteractionManager;
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

public class InteractionSelectedPane extends MainPane {

	private GridPane grid_pane;
	private String full_directory_name = DIRECTORY_STRING + "interaction_selected_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #ffffff";
	private int x, y, width, height;
	private static final int TEXTFIELD_SIZE = 10;
	private static final Pos TEXTFIELD_POSITION = Pos.CENTER;
	private InteractionKeysController interaction_keys_controller;
	private MainComboBox interaction_name_cb;
		
	public InteractionSelectedPane(MainComboBox interaction_name_cb, InteractionKeysController interaction_keys_controller) {
		this.interaction_keys_controller = interaction_keys_controller;
		this.interaction_name_cb = (InteractionNameComboBox) interaction_name_cb;
		System.out.println("HI");
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
	
	public void updateSelectedComponentsPane() {
		grid_pane.getChildren().clear();
		String name_selected = interaction_name_cb.getSelectionModel().getSelectedItem();
		List<AuthoringObject> authoring_objects = interaction_keys_controller.getInteractionComponents(name_selected);
		for(AuthoringObject ao : authoring_objects) {
			grid_pane.getChildren().add(new Button(ao.getName()));
//			ImageView imageView = new ImageView(ao.getImage());
//			grid_pane.getChildren().add(imageView);
		}
	}
}