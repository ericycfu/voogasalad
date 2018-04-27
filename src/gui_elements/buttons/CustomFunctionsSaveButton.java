package gui_elements.buttons;

import java.util.List;

import authoring.view.InteractionAddCustomFunctionsScreen;
import gui_elements.combo_boxes.CustomFunctionTypeComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.panes.CreatedCustomFunctionsPane;
import gui_elements.panes.CustomFunctionsPane;
import gui_elements.panes.MainPane;
import interactions.CustomComponentParameterFormat;
import interactions.CustomFunction;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CustomFunctionsSaveButton extends MainButton {

	private static final String FILENAME = "custom_functions_save_button.properties";
	private static final String ALERT_TITLE = "Custom Function Saved";
	private static final String ALERT_MESSAGE = "Your custom function has been saved!";
	private static final String INVALID_FIELDS_ERROR_TITLE = "Invalid Field";
	private static final String INVALID_FIELDS_ERROR_MESSAGE = "Please make sure all fields are filled!";
	private static final String CANNOT_BUILD_ERROR_TITLE = "Cannot Create \"Build\" Custom Function";
	private static final String CANNOT_BUILD_ERROR_MESSAGE = "Sorry, the \"Build\" custom function cannot be created because custom functions already exist!";
	private static final String BUILD_EXISTS_ERROR_TITLE = "\"Build\" Custom Function Already Exists";
	private static final String BUILD_EXISTS_ERROR_MESSAGE = "Sorry, another custom function cannot be created because the \"Build\" custom function already exists!";
	private static final String BLANK = "";
	private static final String SPACE = " ";
	private static final String BUILD_FUNCTION = "BuildFunction";
	private static final boolean EXPLICIT_SET_ACTION = false;
	private CustomFunctionsPane custom_functions_pane;
	private CreatedCustomFunctionsPane created_custom_functions_pane;
	private CustomFunctionTypeComboBox custom_function_type_cb;
	private InteractionAddCustomFunctionsScreen interaction_add_custom_functions_screen;
	private InteractionManager interaction_manager;
	private int current_interaction_id;

	public CustomFunctionsSaveButton(InteractionManager interaction_manager, int current_interaction_id,
			MainPane custom_functions_pane, MainPane created_custom_functions_pane, MainComboBox custom_function_type_cb, 
			InteractionAddCustomFunctionsScreen interaction_add_custom_functions_screen) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.interaction_manager = interaction_manager;
		this.current_interaction_id = current_interaction_id;
		this.custom_functions_pane = (CustomFunctionsPane) custom_functions_pane;
		this.created_custom_functions_pane = (CreatedCustomFunctionsPane) created_custom_functions_pane;
		this.custom_function_type_cb = (CustomFunctionTypeComboBox) custom_function_type_cb;
		this.interaction_add_custom_functions_screen = interaction_add_custom_functions_screen;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			Interaction interaction = interaction_manager.getInteraction(current_interaction_id);
			if(buildCustomFunctionAlreadyCreated(interaction)) {
				createBuildCustomFunctionAlreadyCreatedError();
				return;
			}
			CustomFunction custom_function = custom_function_type_cb.getCurrentSelectedCustomFunction();			
			if(custom_function.getName().equals(BUILD_FUNCTION) && !interaction.getCustomFunctions().isEmpty()) {
				createCannotCreateBuildCustomFunctionError();
				return;
			}
			CustomComponentParameterFormat format = custom_function.getParameterFormat();
			List<String> parameterList = format.getParameterList();
			ObservableList<Node> valueList = custom_functions_pane.getPane().getChildren();
			if(!fieldsAreValid(valueList)) {
				createInvalidFieldsError();
				return;
			}
			for(int i = 0; i < parameterList.size(); i++)
				format.setFieldValue(parameterList.get(i), ((TextField) valueList.get(i)).getText());
			created_custom_functions_pane.addButton(custom_function_type_cb.getComboBox().getSelectionModel().getSelectedItem(), format);
			interaction_add_custom_functions_screen.getStage().close();
			interaction.addCustomFunction(custom_function);
			createSuccessAlert();
		});
	}
	
	private boolean buildCustomFunctionAlreadyCreated(Interaction interaction) {
		for(CustomFunction custom_function : interaction.getCustomFunctions()) {
			String name = custom_function.getName();
			if(name.equals(BUILD_FUNCTION))
				return true;
		}
		return false;
	}
	
	private boolean fieldsAreValid(ObservableList<Node> valueList) {
		for(int i = 0; i < valueList.size(); i++) {
			String text = ((TextField) valueList.get(i)).getText();
			if(text.equals(SPACE) || text.equals(BLANK)) {
				return false;
			}
		}
		return true;
	}
	
	private void createInvalidFieldsError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(INVALID_FIELDS_ERROR_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(INVALID_FIELDS_ERROR_MESSAGE);
		alert.showAndWait();
	}

	private void createCannotCreateBuildCustomFunctionError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(CANNOT_BUILD_ERROR_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(CANNOT_BUILD_ERROR_MESSAGE);
		alert.showAndWait();
	}

	private void createBuildCustomFunctionAlreadyCreatedError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(BUILD_EXISTS_ERROR_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(BUILD_EXISTS_ERROR_MESSAGE);
		alert.showAndWait();
	}
	
	private void createSuccessAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(ALERT_TITLE);
		alert.setHeaderText(null);
		alert.setContentText(ALERT_MESSAGE);
		alert.showAndWait();
	}
}