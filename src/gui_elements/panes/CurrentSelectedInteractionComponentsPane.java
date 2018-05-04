package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import authoring.backend.AuthoringObject;
import authoring.backend.TagController;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class CurrentSelectedInteractionComponentsPane extends MainPane {

	private FlowPane flow_pane;
	private String full_directory_name = DIRECTORY_STRING + "current_selected_interaction_components_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #ffffff";
	private int x, y, width, height;
	private TagController tag_controller;
	
	public CurrentSelectedInteractionComponentsPane(TagController tag_controller) {
		this.tag_controller = tag_controller;
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
	
	public void updatePane(String tag_selected) {
		flow_pane.getChildren().clear();
		Map<String, List<AuthoringObject>> tagMap = tag_controller.getTagMap();
		List<AuthoringObject> authoring_objects = tagMap.get(tag_selected);
		for(AuthoringObject ao : authoring_objects) {
			flow_pane.getChildren().add(new Button(ao.getName()));
//			ImageView imageView = new ImageView(ao.getImage());
//			addImageViewProperties(imageView);
//			flow_pane.getChildren().add(imageView);
		}
	}	
	
//	private void addImageViewProperties(ImageView imageView) {
//		imageView.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
//    		if(e.isPrimaryButtonDown()) {
//    			List<Node> imageViews = interaction_selected_pane.getPane().getChildren();
//    			if(e.getClickCount() == 2 && !imageViews.contains(imageView)) {
//    				interaction_selected_pane.getPane().getChildren().add(imageView);
//            	}
//            }
//        });
//	}
}