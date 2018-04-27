package gui_elements.buttons;

import authoring.backend.TagController;
import authoring.view.EditComponentTagsScreen;
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

	public EditComponentTagsSaveButton(TagController tag_controller, MainPane edit_component_tags_pane,
			EditComponentTagsScreen edit_component_tags_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.tag_controller = tag_controller;
		this.edit_component_tags_pane = (EditComponentTagsPane) edit_component_tags_pane;
		this.edit_component_tags_screen = edit_component_tags_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			ObservableList<Node> valueList = edit_component_tags_pane.getPane().getChildren();
			for(int i = 0; i < tag_controller.getTags().size(); i++) {
				tag_controller.updateTag(i, ((TextField) valueList.get(i)).getText());
			}
			edit_component_tags_screen.getStage().close();
		});
	}
}