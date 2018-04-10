package gui_elements.buttons;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AddResourceButton extends MainButton {

	private static final String FILENAME = "add_resource_button.properties";
	private FlowPane resource_name_pane, resource_amount_pane;
	private static final int CB_SIZE = 10;
	private static final Pos CB_POSITION = Pos.CENTER;

	public AddResourceButton() {
//	Pane resource_name_pane, Pane resource_amount_pane ) {
		super(FILENAME);
//		this.resource_name_pane = (FlowPane) resource_name_pane;
//		this.resource_amount_pane = (FlowPane) resource_amount_pane;
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
			createComboBox(resource_name_pane);
			createComboBox(resource_amount_pane);
		});
	}
	
	private void createComboBox(Pane pane){
		ComboBox resource_field = new ComboBox();
		resource_field.setPrefSize(pane.getWidth(), pane.getHeight() / CB_SIZE);
		pane.getChildren().add(resource_field);
	}
}