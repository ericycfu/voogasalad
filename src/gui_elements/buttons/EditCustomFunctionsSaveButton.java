package gui_elements.buttons;

import java.util.List;

import authoring.view.EditCustomFunctionsScreen;
import gui_elements.panes.EditCustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomComponentParameterFormat;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class EditCustomFunctionsSaveButton extends MainButton {

	private static final String FILENAME = "edit_custom_functions_save_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private CustomComponentParameterFormat format;
	private EditCustomFunctionsPane edit_custom_functions_pane;
	private EditCustomFunctionsScreen edit_custom_functions_screen;

	public EditCustomFunctionsSaveButton(CustomComponentParameterFormat format,
			MainPane edit_custom_functions_pane, EditCustomFunctionsScreen edit_custom_functions_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.format = format;
		this.edit_custom_functions_pane = (EditCustomFunctionsPane) edit_custom_functions_pane;
		this.edit_custom_functions_screen = edit_custom_functions_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			List<String> parameterList = format.getParameterList();
			ObservableList<Node> valueList = edit_custom_functions_pane.getPane().getChildren();
			for(int i = 0; i < parameterList.size(); i++) {
				format.setFieldValue(parameterList.get(i), ((TextField) valueList.get(i)).getText());
			}
			edit_custom_functions_screen.getStage().close();
		});
	}
}