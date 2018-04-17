package gui_elements.buttons;

import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.tabs.DesignTab;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateComponentButton extends MainButton {

	private static final String FILENAME = "create_component_button.properties";
	private AuthoringObject authoring_object;
	private static final boolean EXPLICIT_SET_ACTION = false;
	private TextField name_tf, movement_speed_tf;
	private ComboBox tag_cb;
	private Label image_text_label;
		
	public CreateComponentButton(AuthoringObject authoring_object, TextField name_tf,
			ComboBox tag_cb, Label image_text_label, TextField movement_speed_tf) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.authoring_object = authoring_object;
		this.name_tf = name_tf;
		this.tag_cb = tag_cb;
		this.image_text_label = image_text_label;
		this.movement_speed_tf = movement_speed_tf;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			authoring_object.setName(name_tf.getText());
			authoring_object.setTag(tag_cb.getEditor().getText());
			authoring_object.setImage(image_text_label.getText());
			authoring_object.setMovementSpeed(Double.parseDouble(movement_speed_tf.getText()));
			CreatedObjects.addObject(authoring_object);
			DesignTab.setNewAuthoringObject();
			DesignTab.resetComponents();
		});
	}
}