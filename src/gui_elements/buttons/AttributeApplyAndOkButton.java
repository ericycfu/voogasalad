package gui_elements.buttons;

import java.util.Map;

import game_object.ObjectAttributes;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AttributeApplyAndOkButton extends MainButton {

	private static final String FILENAME = "attribute_apply_and_ok_button.properties";
	private FlowPane attribute_names_pane, attribute_values_pane;
	private Stage stage;
	private ObjectAttributes objAttributesInstance;
	private static final boolean EXPLICIT_SET_ACTION = false;
	private static final double DEFAULT_ATTRIBUTE_VALUE = 0.0;
	
	public AttributeApplyAndOkButton(Pane attribute_names_pane, Pane attribute_values_pane,
			Stage stage, ObjectAttributes objAttributesInstance) {
		super(FILENAME, EXPLICIT_SET_ACTION);
		this.attribute_names_pane = (FlowPane) attribute_names_pane;
		this.attribute_values_pane = (FlowPane) attribute_values_pane;
		this.stage = stage;
		this.objAttributesInstance = objAttributesInstance;
		setAction();
	}

	@Override
	protected void setAction() {
		//Map<String, Double> attributes = objAttributesInstance.getAttributeMap();
		getButton().setOnAction(e -> {
			//attributes.clear();
			objAttributesInstance.clearAttributes();
			for(int i = 0; i < attribute_names_pane.getChildren().size(); i++) {
				String name = "";
				double value = DEFAULT_ATTRIBUTE_VALUE;
				try {
					name = (String) ((TextField) attribute_names_pane.getChildren().get(i)).getText();
					value = Double.parseDouble(((TextField) attribute_values_pane.getChildren().get(i)).getText());
					if(!name.equals(""))
						//attributes.put(name, value);
						objAttributesInstance.createAttribute(name, value);
				} catch(NullPointerException ex) {
					((TextField) attribute_names_pane.getChildren().get(i)).clear();
					((TextField) attribute_values_pane.getChildren().get(i)).clear();					
				} catch(NumberFormatException ey) {
					objAttributesInstance.createAttribute(name, DEFAULT_ATTRIBUTE_VALUE);
				}
			}
			stage.close();
		});
	}
}