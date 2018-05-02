package gui_elements.buttons;

import authoring.backend.TagController;
import authoring.edit_object.EditComponentTagsScreen;
import gui_elements.combo_boxes.ComponentTagComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.EditComponentTagsPane;
import gui_elements.panes.MainPane;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class EditComponentTagsSaveButton extends MainButton {

	private static final String FILENAME = "edit_component_tags_save_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private TagController tag_controller;
	private EditComponentTagsPane edit_component_tags_pane;
	private EditComponentTagsScreen edit_component_tags_screen;
	private ComponentTagComboBox component_tag_cb;

	public EditComponentTagsSaveButton(TagController tag_controller, MainPane edit_component_tags_pane,
			MainComboBox component_tag_cb, EditComponentTagsScreen edit_component_tags_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.tag_controller = tag_controller;
		this.edit_component_tags_pane = (EditComponentTagsPane) edit_component_tags_pane;
		this.component_tag_cb = (ComponentTagComboBox) component_tag_cb;
		this.edit_component_tags_screen = edit_component_tags_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			ObservableList<Node> valueList = edit_component_tags_pane.getPane().getChildren();
			for(int i = 0; i < tag_controller.getTags().size(); i++) {
				String text = ((TextField) valueList.get(i)).getText();
				tag_controller.updateTag(i, text);
				component_tag_cb.getItems().set(i, text);
			}
			edit_component_tags_screen.getStage().close();
		});
	}
}