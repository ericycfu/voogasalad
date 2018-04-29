package gui_elements.combo_boxes;

public class ComponentTeamComboBox extends MainComboBox {

	private static final String FILENAME = "component_team_cb.properties";
	private static final String BLANK = "";
	private static final int MAX_TEAMS = 4;
	
	public ComponentTeamComboBox() {
		super(FILENAME);
		addElements();
	}
	
	private void addElements() {
		for(int i = 1; i <= MAX_TEAMS; i++) {
			getComboBox().getItems().add(i + BLANK);
		}
	}
}