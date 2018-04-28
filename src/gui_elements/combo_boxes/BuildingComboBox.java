package gui_elements.combo_boxes;

import authoring.backend.TagController;

public class BuildingComboBox extends MainComboBox {
	private static final String FILENAME = "building_cb.properties";
	
	public BuildingComboBox() {
		super(FILENAME);
		addElements();
	}
	
	private void addElements() {
		getComboBox().getItems().add("true");
		getComboBox().getItems().add("false");
	}
}
