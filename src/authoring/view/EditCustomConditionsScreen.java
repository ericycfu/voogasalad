package authoring.view;

import conditions.ConditionManager;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditCustomConditionsScreen implements AuthoringView {
	private Pane root;
	private ConditionManager cm;
	public EditCustomConditionsScreen(ConditionManager cm, String condition) {
		this.cm = cm;
		initializeScene();
	}
	
	private void initializeScene() {
		root = new Pane();
		Scene scene = new Scene (root, PANEL_WIDTH/2, PANEL_HEIGHT/2, DEFAULT_BACKGROUND);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Edit Custom Conditions");
		stage.setResizable(false);
		stage.show();
	}
	
	private void addLine() {
		
	}
	
}
