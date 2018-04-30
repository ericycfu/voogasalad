package gui_elements.combo_boxes;

import authoring.backend.AuthoringObject;

public class ObjectTeamComboBox extends MainComboBox {

	private static final String FILENAME = "object_team_cb.properties";
	private static final String BLANK = "";
	private static final int MAX_TEAMS = 4;
	private AuthoringObject authoring_object;
	
	public ObjectTeamComboBox(AuthoringObject authoring_object) {
		super(FILENAME);
		this.authoring_object = authoring_object;
		addElements();
		setElement();
	}
	
	private void addElements() {
		for(int i = 1; i <= MAX_TEAMS; i++) {
			getComboBox().getItems().add(i + BLANK);
		}
	}
	
	private void setElement() {
		getComboBox().getSelectionModel().select(authoring_object.getTeam() + BLANK);
	}
}