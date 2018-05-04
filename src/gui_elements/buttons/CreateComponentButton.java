package gui_elements.buttons;

import java.util.Map;
import java.util.Map.Entry;

import authoring.backend.AuthoringObject;
import authoring.backend.GameEntity;
import authoring.backend.TagController;
import gui_elements.tabs.DesignTab;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CreateComponentButton extends MainButton {

	private static final String FILENAME = "create_component_button.properties";
	private static final String SPACE = " ";
	private static final String IMAGE_PATH_HEADING = "/images/";
	private static final String ALERT_TITLE = "Component Created";
	private static final String ALERT_MESSAGE = "You successfully created a new component!";
	private static final int DEFAULT_TEAM = 1;
	private AuthoringObject authoring_object;
	private static final boolean EXPLICIT_SET_ACTION = false;
	private TextField name_tf, movement_speed_tf;
	private ComboBox<String> tag_cb;
	private Label image_text_label;
	private TagController tag_controller;
	private DesignTab design_tab;
	private ComboBox<String> building_cb;
	private TextField build_time_tf;
	private Map<String, Double> resource_cost_map;
	private GameEntity game_entity;
		
	public CreateComponentButton(AuthoringObject authoring_object, TextField name_tf, ComboBox<String> tag_cb, 
			TagController tag_controller, Label image_text_label, TextField movement_speed_tf, ComboBox<String> building_cb, 
			TextField build_time_tf, Map<String, Double> resource_cost_map, DesignTab design_tab,
			GameEntity game_entity) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.authoring_object = authoring_object;
		this.name_tf = name_tf;
		this.tag_cb = tag_cb;
		this.tag_controller = tag_controller;
		this.image_text_label = image_text_label;
		this.movement_speed_tf = movement_speed_tf;
		this.design_tab = design_tab;
		this.building_cb = building_cb;
		this.build_time_tf = build_time_tf;
		this.resource_cost_map = resource_cost_map;
		this.game_entity = game_entity;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			String tag_text = tag_cb.getEditor().getText();
			authoring_object.setName(name_tf.getText());
			for(String tag : tag_text.split(SPACE)) {
				authoring_object.addTag(tag);
				tag_controller.addTag(tag, authoring_object);
				if(!tag_cb.getItems().contains(tag))
					tag_cb.getItems().add(tag);
			}
			authoring_object.setImage(IMAGE_PATH_HEADING + image_text_label.getText());
			authoring_object.setMovementSpeed(Double.parseDouble(movement_speed_tf.getText()));
			authoring_object.setBuilding(Boolean.parseBoolean(building_cb.getValue()));
			authoring_object.setBuildTime(Double.parseDouble(build_time_tf.getText()));
			authoring_object.setTeam(DEFAULT_TEAM);
			for (Entry<String, Double> entry: resource_cost_map.entrySet()) {
				authoring_object.setBuildCost(entry.getKey(), entry.getValue());
			}
			game_entity.getCreatedObjects().addObject(authoring_object);
			design_tab.setNewAuthoringObject();
			design_tab.resetComponents();
			createAlert();
		});
	}
	
	private void createAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ALERT_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(ALERT_MESSAGE);
		alert.showAndWait();
	}
}