package gui_elements.buttons;

import java.util.Map;

import game_object.ObjectAttributes;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ResourceApplyButton extends MainButton {

	private static final String FILENAME = "resource_apply_button.properties";
	private FlowPane resource_names_pane, resource_values_pane;
	private Stage stage;
	private ObjectAttributes objAttributesInstance;
	private static final boolean NO_EXPLICIT_SET_ACTION = false;
	private static final double DEFAULT_RESOURCE_VALUE = 0.0;
	
	public ResourceApplyButton(Pane resource_names_pane, Pane resource_values_pane,
			Stage stage, ObjectAttributes objAttributesInstance) {
		super(FILENAME, NO_EXPLICIT_SET_ACTION);
		this.resource_names_pane = (FlowPane) resource_names_pane;
		this.resource_values_pane = (FlowPane) resource_values_pane;
		this.stage = stage;
		this.objAttributesInstance = objAttributesInstance;
		setAction();
	}

	@Override
	protected void setAction() {
		Map<String, Double> attributes = objAttributesInstance.getAttributeMap();
		getButton().setOnAction(e -> {
			attributes.clear();
			for(int i = 0; i < resource_names_pane.getChildren().size(); i++) {
				String name = "";
				double value = DEFAULT_RESOURCE_VALUE;
				try {
					name = (String) ((TextField) resource_names_pane.getChildren().get(i)).getText();
					value = Double.parseDouble(((TextField) resource_values_pane.getChildren().get(i)).getText());
					if(!name.equals(""))
						attributes.put(name, value);
				} catch(NullPointerException ex) {
					((TextField) resource_names_pane.getChildren().get(i)).clear();
					((TextField) resource_values_pane.getChildren().get(i)).clear();					
				} catch(NumberFormatException ey) {
					attributes.put(name, DEFAULT_RESOURCE_VALUE);
				}
			}
			stage.close();
		});
	}	
}