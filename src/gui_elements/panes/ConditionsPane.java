package gui_elements.panes;

import conditions.ConditionManager;
import javafx.scene.layout.GridPane;

public class ConditionsPane {
	private GridPane pane;
	public ConditionsPane(ConditionManager cm) {
		createPane();
	}
	
	private void createPane() {
		pane = new GridPane();
		pane.setLayoutX(270);
		pane.setLayoutY(120);
		pane.setPrefWidth(200);
		pane.setPrefHeight(380);
	}
}
