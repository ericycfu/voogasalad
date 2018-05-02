package gui_elements.buttons;

import authoring.edit_object.ComponentAddInteractionsScreen;
import gui_elements.combo_boxes.InteractionNameComboBox;
import gui_elements.combo_boxes.InteractionTargetTeamComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.labels.InteractionImageChoiceTextLabel;
import gui_elements.labels.MainLabel;
import gui_elements.panes.AllSelectedInteractionTagsPane;
import gui_elements.panes.MainPane;
import gui_elements.text_fields.InteractionDescriptionTextField;
import gui_elements.text_fields.InteractionRateTextField;
import gui_elements.text_fields.InteractionVisionRangeTextField;
import gui_elements.text_fields.MainTextField;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class AddInteractionButton extends MainButton {

	private static final String FILENAME = "add_interaction_button.properties";
	private static final String IMAGE_PATH_HEADING = "/images/";
	private static final String ALERT_TITLE = "Interaction Added";
	private static final String ALERT_MESSAGE = "You successfully added an interaction!";
	private InteractionManager interaction_manager;
	private MainComboBox interaction_name_cb, interaction_target_team_cb;
	private MainTextField interaction_vision_range_tf, interaction_description_tf, interaction_rate_tf;
	private MainPane all_selected_interaction_tags_pane;
	private MainLabel interaction_image_choice_text_label;
	private int interaction_id;
	private ComponentAddInteractionsScreen component_add_interactions_screen;
	private static final boolean EXPLICIT_SET_ACTION = false;

	public AddInteractionButton(InteractionManager interaction_manager, MainComboBox interaction_name_cb, 
			MainTextField interaction_vision_range_tf, MainPane all_selected_interaction_tags_pane,
			MainLabel interaction_image_choice_text_label, MainTextField interaction_description_tf, 
			MainComboBox interaction_target_team_cb, MainTextField interaction_rate_tf, 
			ComponentAddInteractionsScreen component_add_interactions_screen, int interaction_id) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.interaction_manager = interaction_manager;
		this.interaction_name_cb = (InteractionNameComboBox) interaction_name_cb;
		this.interaction_vision_range_tf = (InteractionVisionRangeTextField) interaction_vision_range_tf;
		this.all_selected_interaction_tags_pane = (AllSelectedInteractionTagsPane) all_selected_interaction_tags_pane;
		this.interaction_image_choice_text_label = (InteractionImageChoiceTextLabel) interaction_image_choice_text_label;
		this.interaction_description_tf = (InteractionDescriptionTextField) interaction_description_tf;
		this.interaction_target_team_cb = (InteractionTargetTeamComboBox) interaction_target_team_cb;
		this.interaction_rate_tf = (InteractionRateTextField) interaction_rate_tf;
		this.component_add_interactions_screen = component_add_interactions_screen;
		this.interaction_id = interaction_id;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			Interaction interaction = interaction_manager.getInteraction(interaction_id);
			interaction.setName(interaction_name_cb.getComboBox().getEditor().getText());
			interaction_name_cb.getItems().add(interaction.getName());
			interaction.setRange(Double.parseDouble(interaction_vision_range_tf.getTextField().getText()));
			for(Object obj : all_selected_interaction_tags_pane.getPane().getChildren()) {
				interaction.addTag(((Button) obj).getText());
			}
			interaction.setImg(IMAGE_PATH_HEADING + interaction_image_choice_text_label.getText());
			interaction.setDescription(interaction_description_tf.getText());
			interaction.setInteractionTargetTeam(interaction_target_team_cb.getSelectionModel().getSelectedItem());
			interaction.setRate(interaction_rate_tf.getText());
			component_add_interactions_screen.resetElements();
			component_add_interactions_screen.setInteractionID(interaction_id = interaction_manager.createInteraction());
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