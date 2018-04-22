package gui_elements.buttons;

import java.util.List;

import authoring.view.ComponentAddInteractionsScreen;
import gui_elements.combo_boxes.CustomFunctionTypeComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.CustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomComponentParameterFormat;
import interactions.CustomFunction;
import interactions.InteractionManager;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class CustomFunctionsSaveButton extends MainButton {

	private static final String FILENAME = "custom_functions_save_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private CustomFunctionsPane custom_functions_pane;
	private CustomFunctionTypeComboBox custom_function_type_cb;

	public CustomFunctionsSaveButton(MainPane custom_functions_pane, MainComboBox custom_function_type_cb) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.custom_functions_pane = (CustomFunctionsPane) custom_functions_pane;
		this.custom_function_type_cb = (CustomFunctionTypeComboBox) custom_function_type_cb;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			CustomComponentParameterFormat format = custom_function_type_cb.getCurrentSelectedCustomFunction().getParameterFormat();
			List<String> parameterList = format.getParameterList();
			ObservableList<Node> valueList = custom_functions_pane.getPane().getChildren();
			for(int i = 0; i < parameterList.size(); i++) {
				format.setFieldValue(parameterList.get(i), ((TextField) valueList.get(i)).getText());
			}
		});
	}
}