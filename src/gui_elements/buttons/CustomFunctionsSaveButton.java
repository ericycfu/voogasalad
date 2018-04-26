package gui_elements.buttons;

import java.util.List;

import authoring.view.ComponentAddInteractionsScreen;
import authoring.view.InteractionAddCustomFunctionsScreen;
import gui_elements.combo_boxes.CustomFunctionTypeComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.CustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomComponentParameterFormat;
import interactions.CustomFunction;
import interactions.InteractionManager;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CustomFunctionsSaveButton extends MainButton {

	private static final String FILENAME = "custom_functions_save_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private static final String ALERT_TITLE = "Custom Function Saved";
	private static final String ALERT_MESSAGE = "Your custom function has been saved!";
	private CustomFunctionsPane custom_functions_pane;
	private CreatedCustomFunctionsPane created_custom_functions_pane;
	private CustomFunctionTypeComboBox custom_function_type_cb;
	private InteractionAddCustomFunctionsScreen interaction_add_custom_functions_screen;

	public CustomFunctionsSaveButton(MainPane custom_functions_pane, MainPane created_custom_functions_pane,
			MainComboBox custom_function_type_cb, InteractionAddCustomFunctionsScreen interaction_add_custom_functions_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.custom_functions_pane = (CustomFunctionsPane) custom_functions_pane;
		this.created_custom_functions_pane = (CreatedCustomFunctionsPane) created_custom_functions_pane;
		this.custom_function_type_cb = (CustomFunctionTypeComboBox) custom_function_type_cb;
		this.interaction_add_custom_functions_screen = interaction_add_custom_functions_screen;
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
			created_custom_functions_pane.addButton(custom_function_type_cb.getComboBox().getSelectionModel().getSelectedItem(),
													format);
			interaction_add_custom_functions_screen.getStage().close();
			createAlert();
		});
	}
	
	private void createAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ALERT_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(ALERT_MESSAGE);
		alert.showAndWait();
	}
}