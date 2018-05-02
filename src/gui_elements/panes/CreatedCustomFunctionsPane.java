package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import authoring.edit_object.EditCustomFunctionsScreen;
import interactions.CustomComponentParameterFormat;
import interactions.CustomFunction;
import interactions.InteractionManager;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class CreatedCustomFunctionsPane extends MainPane {

	private FlowPane flow_pane;
	private String full_directory_name = DIRECTORY_STRING + "created_custom_functions_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #ffffff";
	private int x, y, width, height;
		
	public CreatedCustomFunctionsPane() {
		initialize();
	}
	
	public void initialize() {
		getProperties();
		createPane();
	}
	
	@Override
	protected void getProperties() {
		properties = new Properties();
		input = null;
		try {
	  		input = new FileInputStream(full_directory_name);
	  		properties.load(input);

	  		x = Integer.parseInt(properties.getProperty(X_LOC_STRING));
	  		y = Integer.parseInt(properties.getProperty(Y_LOC_STRING));
	  		width = Integer.parseInt(properties.getProperty(WIDTH));
	  		height = Integer.parseInt(properties.getProperty(HEIGHT));
	   	} catch (IOException ex) {
//	   		E
	  	} finally {
	  		if (input != null) {
	  			try {
	  				input.close();
	  			} catch (IOException e) {
//	  				E
	  			}
	  		}
	  	}
	}

	@Override
	protected void createPane() {
		flow_pane = new FlowPane();
		flow_pane.setLayoutX(x);
		flow_pane.setLayoutY(y);
		flow_pane.setPrefSize(width, height);
		flow_pane.setStyle(PANE_STYLE);
	}
	
	@Override
	protected void setX(int x) {
		flow_pane.setLayoutX(x);
	}

	@Override
	protected void setY(int y) {
		flow_pane.setLayoutY(y);
	}

	@Override
	protected int getX() {
		return (int) flow_pane.getLayoutX();
	}

	@Override
	protected int getY() {
		return (int) flow_pane.getLayoutY();
	}
	
	@Override
	public Pane getPane() {
		return flow_pane;
	}
	
	public void addButton(String text, CustomComponentParameterFormat format) {
		Button custom_function_button = new Button(text);
		custom_function_button.setOnAction(e -> {
			new EditCustomFunctionsScreen(format);
		});
		flow_pane.getChildren().add(custom_function_button);
	}
}