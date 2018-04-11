package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import game_object.ObjectAttributes;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AttributeNamesPane extends MainPane {

	private FlowPane flow_pane;
	private String full_directory_name = DIRECTORY_STRING + "attribute_names_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #000000";
	private int x, y, width, height;
	private static final int TEXTFIELD_SIZE = 10;
	private static final Pos TEXTFIELD_POSITION = Pos.CENTER;	
	private ObjectAttributes objAttributesInstance;	
	
	public AttributeNamesPane(ObjectAttributes objAttributesInstance) {
		this.objAttributesInstance = objAttributesInstance;
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
		
		for(String attribute_name : objAttributesInstance.getAttributeNames()) {
			createTextField(attribute_name);
		}
		createTextField("");
		
	}
	
	private void createTextField(String attribute_name) {
		TextField attribute_name_field = new TextField(attribute_name);
		attribute_name_field.setAlignment(TEXTFIELD_POSITION);
		attribute_name_field.setPrefSize(width, height / TEXTFIELD_SIZE);
		flow_pane.getChildren().add(attribute_name_field);
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
		
//	public static void addButton(Button button) {
//		flow_pane.getChildren().add(button);
//	}
}