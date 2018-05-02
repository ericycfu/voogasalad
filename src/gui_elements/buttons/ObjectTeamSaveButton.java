package gui_elements.buttons;

import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.backend.TagController;
import authoring.edit_map.ObjectTeamSelectionScreen;
import authoring.support.DraggableImageView;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.combo_boxes.ObjectTeamComboBox;
import gui_elements.tabs.DesignTab;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ObjectTeamSaveButton extends MainButton {

	private static final String FILENAME = "object_team_save_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private AuthoringObject authoring_object;
	private MainComboBox object_team_cb;
	private DraggableImageView draggable_image_view;
	private ObjectTeamSelectionScreen object_team_selection_screen;
		
	public ObjectTeamSaveButton(AuthoringObject authoring_object, MainComboBox object_team_cb,
			DraggableImageView draggable_image_view, ObjectTeamSelectionScreen object_team_selection_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.authoring_object = authoring_object;
		this.object_team_cb = (ObjectTeamComboBox) object_team_cb;
		this.draggable_image_view = draggable_image_view;
		this.object_team_selection_screen = object_team_selection_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			String team_selected = object_team_cb.getSelectionModel().getSelectedItem();
			authoring_object.setTeam(Integer.parseInt(team_selected));
			draggable_image_view.updateImageProperties(authoring_object);
			object_team_selection_screen.getStage().close();
		});
	}	
}