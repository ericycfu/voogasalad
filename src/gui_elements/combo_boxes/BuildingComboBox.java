package gui_elements.combo_boxes;

import authoring.backend.TagController;

public class BuildingComboBox extends MainComboBox {
	private static final String FILENAME = "building_cb.properties";
	
	public BuildingComboBox() {
		super(FILENAME);
		getComboBox().setEditable(true);
		addElements();
	}
	
	private void addElements() {
		getComboBox().getItems().add("true");
		getComboBox().getItems().add("false");
	}
}
