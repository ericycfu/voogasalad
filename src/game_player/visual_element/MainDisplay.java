package game_player.visual_element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainDisplay implements VisualUpdate {

	private double myCurrentXCoor; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor; 
	private double myWidth;
	private double myHeight;
	private List<GameObject> myTerrains = new ArrayList<>();
	private List<GameObject> myUnits = new ArrayList<>();
	private Group myDisplayables;
	private Group myMoveWindowButtons;
	private List<GameObject> mySelectedUnits;
	
	public MainDisplay(double width, double height) {
		myWidth = width;
		myHeight = height;
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
		myMoveWindowButtons = new Group();
		Button right = new Button();
		right.setLayoutX(myWidth - 60);
		right.setLayoutY(myHeight/2 - 60);
		right.setGraphic(new ImageView(new Image("arrow_right.png")));
		right.setOnMousePressed(e -> {
			myCurrentXCoor += 1;
		});
		myMoveWindowButtons.getChildren().add(right);
		
		Button left = new Button();
		left.setLayoutX(0);
		left.setLayoutY(myHeight/2 - 60);
		left.setGraphic(new ImageView(new Image("arrow_left.png")));
		left.setOnMousePressed(e -> {
			myCurrentXCoor -= 1;
		});
		myMoveWindowButtons.getChildren().add(left);
		
		Button up = new Button();
		up.setLayoutX(myWidth/2 - 60);
		up.setLayoutY(0);
		up.setGraphic(new ImageView(new Image("arrow_up.png")));
		up.setOnMousePressed(e -> {
			myCurrentYCoor -= 1;
		});
		myMoveWindowButtons.getChildren().add(up);
		
		Button down = new Button();
		down.setLayoutX(myWidth/2 - 60);
		down.setLayoutY(myHeight - 60);
		down.setGraphic(new ImageView(new Image("arrow_down.png")));
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
		Group group = new Group();
		//group.getChildren().add(this.myDisplayables);
		group.getChildren().add(this.myMoveWindowButtons);
		return group;
	}
	
	public List<GameObject> getSelectedUnits(){
		return Collections.unmodifiableList(mySelectedUnits);
	}
	
	private double translateX(double x) {
		double retX = x - myCurrentXCoor;
		return retX;
	}
	
	private double translateY(double y) {
		double retY = y - myCurrentYCoor;
		return retY;
	}
	
	private void updateCurrentWindow() {
		
	}
	
}
