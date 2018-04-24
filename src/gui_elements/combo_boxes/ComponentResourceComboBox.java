package gui_elements.combo_boxes;

public class ComponentResourceComboBox extends MainComboBox {
	private static final String FILENAME = "component_choose_resources_cb.properties";
	
	public ComponentResourceComboBox() {
		super(FILENAME);
		getComboBox().setEditable(true);
		addElements();

	}
	
	private void addElements() {
		
	}
}
