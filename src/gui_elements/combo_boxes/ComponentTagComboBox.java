package gui_elements.combo_boxes;

import authoring.backend.TagController;
import authoring.view.EditComponentTagsScreen;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class ComponentTagComboBox extends MainComboBox {

	private static final String FILENAME = "component_tag_cb.properties";
	private static final String SPACE = " ";
	private String current_tag_string = "";
	private TagController tag_controller;
	
	public ComponentTagComboBox(TagController tag_controller) {
		super(FILENAME);
		this.tag_controller = tag_controller;
		getComboBox().setEditable(true);
		initialize();
	}
	
	private void initialize() {
		addElements();
		chooseElements();
		editElements();
		enterElements();
	}
	
	private void addElements() {
		for(String tag : tag_controller.getTags()) {
			getComboBox().getItems().add(tag);
		}
	}
	
	private void chooseElements() {
		this.setOnAction((ActionEvent ev) -> {
			this.getEditor().focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
		        if(!isNowFocused) {
		            this.setValue(this.getSelectionModel().getSelectedItem());
		        }
		    });			
		});
//		this.setOnAction((ActionEvent ev) -> {
//			System.out.println(current_tag_string);
//			System.out.println(this.getEditor().getText());
//			current_tag_string += getComboBox().getSelectionModel().getSelectedItem() + SPACE;
//			this.getEditor().setText(current_tag_string);
//			System.out.println(this.getEditor().getText());
//		});
	}
	
	private void editElements() {
		getComboBox().addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			if(e.isPrimaryButtonDown()) {
				if(e.getClickCount() == 2) {
					new EditComponentTagsScreen(tag_controller, this);
				}
			}
		});
	}
	
	private void enterElements() {
		getComboBox().getEditor().setOnKeyPressed(key -> {
			if(key.getCode() == KeyCode.TAB) {
				String tag_text = getComboBox().getEditor().getText();
				for(String tag : tag_text.split(SPACE)) {
					if(!tag_controller.getTags().contains(tag)) {
						tag_controller.addTag(tag);
						getComboBox().getItems().add(tag);
					}
				}
				getComboBox().getEditor().clear();
            }
		});		
	}
}