package gui_elements.buttons;

import authoring.backend.AuthoringObject;
import javafx.stage.Stage;

public class CreateComponentButton extends MainButton {

	private static final String FILENAME = "create_component_button.properties";
	private AuthoringObject authoring_object;
	private static final boolean EXPLICIT_SET_ACTION = false;
	private Stage stage;
	
	public CreateComponentButton(Stage stage, AuthoringObject authoring_object) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.stage = stage;
		this.authoring_object = authoring_object;
		setAction();
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			
			stage.close();
		});
	}
}