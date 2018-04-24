package gui_elements.panes;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import interactions.InteractionManager;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class AllSelectedInteractionTagsPane extends MainPane {

	private FlowPane flow_pane;
	private String full_directory_name = DIRECTORY_STRING + "all_selected_interaction_tags_pane.properties";
	private final String PANE_STYLE = "-fx-background-color: #ffffff";
	private int x, y, width, height;
	private InteractionManager interaction_manager;
	private boolean inOldInteractionMode = false;
	private int current_interaction_id;
		
	public AllSelectedInteractionTagsPane(InteractionManager interaction_manager) {
		this.interaction_manager = interaction_manager;
		current_interaction_id = interaction_manager.getElements().size();
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
	
	public void changePaneWithNewName(int id_selected) {
		flow_pane.getChildren().clear();
		List<String> targetTags = interaction_manager.getInteraction(id_selected).getTargetTags();
		for(String targetTag : targetTags) {
			flow_pane.getChildren().add(new Button(targetTag));
		}
		current_interaction_id = id_selected;
	}
	
	public void updatePane(String tag_selected) {
		flow_pane.getChildren().add(new Button(tag_selected));
		if(inOldInteractionMode) {
			interaction_manager.getInteraction(current_interaction_id).getTargetTags().add(tag_selected);
		}
	}
	
	public void setToOldInteractionMode() {
		inOldInteractionMode = true;
	}
	
	public void setToNewInteractionMode() {
		inOldInteractionMode = false;
		current_interaction_id = interaction_manager.getElements().size();
		flow_pane.getChildren().clear();
	}
}