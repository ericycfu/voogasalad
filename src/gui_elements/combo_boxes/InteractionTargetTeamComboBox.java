package gui_elements.combo_boxes;

import interactions.Interaction;
import interactions.Interaction.InteractionTargetTeam;

public class InteractionTargetTeamComboBox extends MainComboBox {

	private static final String FILENAME = "interaction_target_team_cb.properties";
	
	public InteractionTargetTeamComboBox() {
		super(FILENAME);
		addElements();
	}
	
	private void addElements() {
		for(InteractionTargetTeam value : Interaction.InteractionTargetTeam.values()) {
			getComboBox().getItems().add(value.toString());
		}
	}	
}