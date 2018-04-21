package gui_elements.tabs;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import gui_elements.buttons.CreateAttributesButton;
import gui_elements.buttons.CreateConditionsButton;
import gui_elements.buttons.CreateInteractionsButton;
import gui_elements.buttons.MainButton;
import gui_elements.buttons.ComponentImageChooserButton;
import gui_elements.buttons.CreateComponentButton;
import gui_elements.combo_boxes.ComponentTagComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.ComponentImageChoiceTextLabel;
import gui_elements.labels.ComponentImageChooserLabel;
import gui_elements.labels.ComponentMovementSpeedLabel;
import gui_elements.labels.ComponentNameLabel;
import gui_elements.labels.ComponentTagLabel;
import gui_elements.labels.CreateComponentTitleLabel;
import gui_elements.labels.MainLabel;
import gui_elements.text_fields.ComponentMovementSpeedTextField;
import gui_elements.text_fields.ComponentNameTextField;
import gui_elements.text_fields.MainTextField;
import javafx.scene.Group;
import javafx.scene.control.Tab;

public class DesignTab extends Tab {

	private final String TAB_TEXT = "Design";
	private Group design_root;
	private MainTextField component_name_tf, component_movement_speed_tf;
	private MainComboBox component_tag_cb;
	private MainLabel component_image_choice_text_label;
	private MainButton component_image_chooser_button;
	private AuthoringObject authoring_object;
	private TagController tag_controller;
	
	public DesignTab() {
		authoring_object = new AuthoringObject();
		tag_controller = new TagController();
		initialize();
	}
	
	private void initialize() {
		setGroup();
		setLabels();
		setTextFields();
		setComboBoxes();
		setButtons();
		setText();
	}
	
	private void setGroup() {
		design_root = new Group();
		this.setContent(design_root);
	}
		
	private void setText() {
		this.setText(TAB_TEXT);
	}
	
	private void setLabels() {
		design_root.getChildren().addAll(new ComponentNameLabel().getLabel(),
//										 new ComponentHealthLabel().getLabel(),
										 new CreateComponentTitleLabel(),
										 new ComponentImageChooserLabel().getLabel(),
										 (component_image_choice_text_label = new ComponentImageChoiceTextLabel()).getLabel(),
//										 new ComponentProductionCostLabel().getLabel(),
										 new ComponentTagLabel().getLabel(),
										 new ComponentMovementSpeedLabel().getLabel());
//										 new InteractionComponentPropertyLabel().getLabel(),
//										 new InteractionAutomaticLabel().getLabel(),
//										 new InteractionAutomaticYesLabel().getLabel(),
//										 new InteractionAutomaticNoLabel().getLabel(),
//										 new InteractionAutomaticKeyRequestLabel().getLabel(),
//										 new InteractionComponentTagLabel().getLabel(),
//										 new InteractionQuantityLabel().getLabel(),
	}
		
	private void setTextFields() {
//		component_health_tf = new ComponentHealthTextField();
		component_name_tf = new ComponentNameTextField();
		component_movement_speed_tf = new ComponentMovementSpeedTextField();
//		interaction_automatic_key_tf = new InteractionAutomaticKeyTextField();
//		interaction_quantity_tf = new InteractionQuantityTextField();
		
		design_root.getChildren().addAll(
//										 component_health_tf.getTextField(),
										 component_name_tf.getTextField(),
										 component_movement_speed_tf.getTextField());
//										 interaction_automatic_key_tf.getTextField(),
//										 interaction_quantity_tf.getTextField(),
//										 interaction_type_tf.getTextField());
	}
	
	private void setComboBoxes() {
		component_tag_cb = new ComponentTagComboBox(tag_controller);
//		interaction_component_property_cb = new InteractionComponentPropertyComboBox();
		
		design_root.getChildren().addAll(component_tag_cb.getComboBox());
//										 interaction_component_property_cb.getComboBox(),
	}
	
	private void setButtons() {
		component_image_chooser_button = new ComponentImageChooserButton(component_image_choice_text_label);
		
		design_root.getChildren().addAll(
										 component_image_chooser_button.getButton(),
										 new CreateComponentButton(authoring_object,
												 component_name_tf.getTextField(),
												 component_tag_cb.getComboBox(),
												 tag_controller,
												 component_image_choice_text_label.getLabel(),
												 component_movement_speed_tf.getTextField(),
												 this).getButton(),
										 new CreateAttributesButton(authoring_object.getObjectAttributesInstance()).getButton(),
										 new CreateInteractionsButton(authoring_object,
												 					  tag_controller).getButton(),
										 new CreateConditionsButton(authoring_object.getConditionManager()).getButton());
	}
	
	public void setNewAuthoringObject() {
		authoring_object = new AuthoringObject();
		initialize();
	}
	
	public void setAuthoringObject(AuthoringObject object) {
		authoring_object = object;
		initialize();
	}
	
	public void resetComponents() {
		component_name_tf.clear();
		component_tag_cb.getEditor().clear();
		component_image_choice_text_label.setText(null);
		component_movement_speed_tf.clear();
	}
	
	public void assignComponents() {
		component_name_tf.setText(authoring_object.getName() + " Loaded");
		component_tag_cb.getEditor().setText(authoring_object.getTags() + " Loaded");
		component_movement_speed_tf.setText(authoring_object.getMovementSpeed() + 2.0 + "");
	}
}