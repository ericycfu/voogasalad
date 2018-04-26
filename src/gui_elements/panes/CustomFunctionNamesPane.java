package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class CustomFunctionNamesPane extends MainPane {

	private FlowPane flow_pane;
	private String full_directory_name = DIRECTORY_STRING + "custom_function_names_pane.properties";
	private static final String PANE_STYLE = "-fx-background-color: #000000";
	private static final String LABEL_STYLE = "-fx-text-fill: #ffff00; -fx-font-size: 12pt";
	private int x, y, width, height;
	private static final int LABEL_SIZE = 10;
	private static final Pos LABEL_POSITION = Pos.CENTER;	
	
	public CustomFunctionNamesPane() {
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

	public void addCustomFunctionNameLabel(String text) {
		Label custom_function_name_label = new Label(text);
		custom_function_name_label.setAlignment(LABEL_POSITION);
		custom_function_name_label.setPrefSize(width, height / LABEL_SIZE);
		custom_function_name_label.setStyle(LABEL_STYLE);
		flow_pane.getChildren().add(custom_function_name_label);
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
}