package gui_elements.tabs;

import authoring.backend.AuthoringObject;
import gui_elements.buttons.CreateAttributesButton;
import gui_elements.buttons.CreateConditionsButton;
import gui_elements.buttons.CreateInteractionsButton;
import gui_elements.buttons.ComponentImageChooserButton;
import gui_elements.buttons.CreateComponentButton;
import gui_elements.combo_boxes.ComponentTagComboBox;
import gui_elements.combo_boxes.InteractionComponentPropertyComboBox;
import gui_elements.combo_boxes.InteractionComponentTagComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.ComponentAttributesTitleLabel;
import gui_elements.labels.ComponentHealthLabel;
import gui_elements.labels.ComponentImageChoiceTextLabel;
import gui_elements.labels.ComponentImageChooserLabel;
import gui_elements.labels.ComponentNameLabel;
import gui_elements.labels.ComponentProductionCostLabel;
import gui_elements.labels.ComponentTagLabel;
import gui_elements.labels.ComponentVisionRangeLabel;
import gui_elements.labels.CreateComponentTitleLabel;
import gui_elements.labels.InteractionAutomaticKeyRequestLabel;
import gui_elements.labels.InteractionAutomaticLabel;
import gui_elements.labels.InteractionAutomaticNoLabel;
import gui_elements.labels.InteractionAutomaticYesLabel;
import gui_elements.labels.InteractionComponentPropertyLabel;
import gui_elements.labels.InteractionComponentTagLabel;
import gui_elements.labels.InteractionQuantityLabel;
import gui_elements.labels.InteractionSelectedLabel;
import gui_elements.labels.InteractionSelectionsLabel;
import gui_elements.labels.InteractionTitleLabel;
import gui_elements.labels.InteractionTypeLabel;
import gui_elements.labels.MainLabel;
import gui_elements.text_fields.ComponentHealthTextField;
import gui_elements.text_fields.ComponentNameTextField;
import gui_elements.text_fields.ComponentVisionRangeTextField;
import gui_elements.text_fields.InteractionAutomaticKeyTextField;
import gui_elements.text_fields.InteractionQuantityTextField;
import gui_elements.text_fields.InteractionTypeTextField;
import gui_elements.text_fields.MainTextField;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class DesignTab extends Tab {

	private final String TAB_TEXT = "Design";
	private VBox interaction_selections_pane;
	private FlowPane interaction_selected_pane;
	private Group design_root;
	private MainTextField component_health_tf, component_name_tf, component_vision_range_tf, 
		interaction_automatic_key_tf, interaction_quantity_tf, interaction_type_tf;
	private MainComboBox component_tag_cb, interaction_component_property_cb, interaction_component_tag_cb;
	private MainLabel component_image_choice_text_label;
	private AuthoringObject authoring_object = new AuthoringObject();
	
	public DesignTab() {
		initialize();
	}
	
	private void initialize() {
		setGroup();
		setLabels();
		setButtons();
		setTextFields();
		setComboBoxes();
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
//										 new ComponentVisionRangeLabel().getLabel(),
										 new CreateComponentTitleLabel(),
										 new ComponentImageChooserLabel().getLabel(),
										 (component_image_choice_text_label = new ComponentImageChoiceTextLabel()).getLabel(),
//										 new ComponentProductionCostLabel().getLabel(),
										 new ComponentTagLabel().getLabel());
//										 new InteractionTitleLabel().getLabel(),
//										 new InteractionTypeLabel().getLabel(),
//										 new InteractionComponentPropertyLabel().getLabel(),
//										 new InteractionAutomaticLabel().getLabel(),
//										 new InteractionAutomaticYesLabel().getLabel(),
//										 new InteractionAutomaticNoLabel().getLabel(),
//										 new InteractionAutomaticKeyRequestLabel().getLabel(),
//										 new InteractionComponentTagLabel().getLabel(),
//										 new InteractionQuantityLabel().getLabel(),
//										 new InteractionSelectionsLabel().getLabel(),
//										 new InteractionSelectedLabel().getLabel());
	}
	
	private void setButtons() {
		design_root.getChildren().addAll(
//										 new AddInteractionButton().getButton(),
										 new ComponentImageChooserButton(component_image_choice_text_label).getButton(),
										 new CreateComponentButton().getButton(),
										 new CreateAttributesButton(authoring_object.getObjectAttributesInstance()).getButton(),
										 new CreateInteractionsButton().getButton(),
										 new CreateConditionsButton().getButton());
	}
	
	private void setTextFields() {
//		component_health_tf = new ComponentHealthTextField();
		component_name_tf = new ComponentNameTextField();
//		component_vision_range_tf = new ComponentVisionRangeTextField();
//		interaction_automatic_key_tf = new InteractionAutomaticKeyTextField();
//		interaction_quantity_tf = new InteractionQuantityTextField();
//		interaction_type_tf = new InteractionTypeTextField();
		
		design_root.getChildren().addAll(
//										 component_health_tf.getTextField(),
										 component_name_tf.getTextField());
//										 component_vision_range_tf.getTextField(),
//										 interaction_automatic_key_tf.getTextField(),
//										 interaction_quantity_tf.getTextField(),
//										 interaction_type_tf.getTextField());
	}
	
	private void setComboBoxes() {
		component_tag_cb = new ComponentTagComboBox();
//		interaction_component_property_cb = new InteractionComponentPropertyComboBox();
//		interaction_component_tag_cb = new InteractionComponentTagComboBox();
		
		design_root.getChildren().addAll(component_tag_cb.getComboBox());
//										 interaction_component_property_cb.getComboBox(),
//										 interaction_component_tag_cb.getComboBox());
	}
}