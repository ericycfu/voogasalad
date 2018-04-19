package gui_elements.combo_boxes;

import authoring.backend.TagController;

public class ComponentTagComboBox extends MainComboBox {

	private static final String FILENAME = "component_tag_cb.properties";
	private TagController tag_controller;
	
	public ComponentTagComboBox(TagController tag_controller) {
		super(FILENAME);
		this.tag_controller = tag_controller;
		getComboBox().setEditable(true);
//		addElements();
	}
	
	private void addElements() {
		for(String tag : tag_controller.getTags()) {
			getComboBox().getItems().add(tag);
		}
	}
}