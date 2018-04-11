package gui_elements.buttons;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AddAttributeButton extends MainButton {

	private static final String FILENAME = "add_attribute_button.properties";
	private FlowPane attribute_names_pane, attribute_values_pane;
	private static final int TEXTFIELD_SIZE = 10;
	private static final Pos TEXTFIELD_POSITION = Pos.CENTER;
	private static final boolean EXPLICIT_SET_ACTION = true;
	
	public AddAttributeButton(Pane attribute_names_pane, Pane attribute_values_pane) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.attribute_names_pane = (FlowPane) attribute_names_pane;
		this.attribute_values_pane = (FlowPane) attribute_values_pane;
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			createTextField(attribute_names_pane);
			createTextField(attribute_values_pane);
		});
	}
	
	private void createTextField(Pane pane) {
		TextField attribute_field = new TextField();
		attribute_field.setAlignment(TEXTFIELD_POSITION);
		attribute_field.setPrefSize(pane.getWidth(), pane.getHeight() / TEXTFIELD_SIZE);
		pane.getChildren().add(attribute_field);
	}
}