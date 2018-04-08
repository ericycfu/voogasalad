package game_player.visual_element;

import java.util.Collections;
import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainDisplay implements VisualUpdate {

	private double myCurrentXCoor; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor; 
	private List<GameObject> myTerrains;
	private List<GameObject> myUnits;
	private Group myDisplayables;
	private Group myMoveWindowButtons;
	private List<GameObject> mySelectedUnits;
	
	public MainDisplay() {
		initialize();
	}

	private void initialize() {
		initializeMoveButtons();
		display();
	}
	
	private void display() {
		for (GameObject go: myTerrains) {
			myDisplayables.getChildren().add(go.getRenderer().getDisp());
		}
		for (GameObject go: myUnits) {
			myDisplayables.getChildren().add(go.getRenderer().getDisp());
		}
	}
	
	private void initializeMoveButtons() {
		Button right = new Button();
		right.setGraphic(new ImageView(new Image("images/arrow_right")));
		right.setOnMousePressed(e -> {
			myCurrentXCoor += 1;
		});
		myMoveWindowButtons.getChildren().add(right);
		
		Button left = new Button();
		left.setGraphic(new ImageView(new Image("images/arrow_left")));
		left.setOnMousePressed(e -> {
			myCurrentXCoor -= 1;
		});
		myMoveWindowButtons.getChildren().add(left);
		
		Button up = new Button();
		up.setGraphic(new ImageView(new Image("images/arrow_up")));
		up.setOnMousePressed(e -> {
			myCurrentYCoor -= 1;
		});
		myMoveWindowButtons.getChildren().add(up);
		
		Button down = new Button();
		down.setGraphic(new ImageView(new Image("images/arrow_down")));
		down.setOnMousePressed(e -> {
			myCurrentYCoor += 1;
		});
		myMoveWindowButtons.getChildren().add(down);
	}
	
	private void select() {

	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		select();
	}

	@Override
	public Node getNodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<GameObject> getSelectedUnits(){
		return Collections.unmodifiableList(mySelectedUnits);
	}
	
}
