package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_player.SelectedUnitManager;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import transform_library.Vector2;

public class MainDisplay implements VisualUpdate {

	private double myCurrentXCoor = 0; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor = 0; 
	private double myWidth;
	private double myHeight;
	private Group myDisplayables;
	private Group myMoveWindowButtons;
	private SelectedUnitManager mySelectedUnitManager;
	//private double currentPressedLocation; 
	private Group myMainDisplay;
	
	public MainDisplay(SelectedUnitManager selectedUnitManager, double width, double height) {
		mySelectedUnitManager = selectedUnitManager;
		myMainDisplay = new Group();
		myWidth = width;
		myHeight = height;
		initialize();
		myMainDisplay.setOnMouseClicked(e -> {
			if (e.isSecondaryButtonDown()) {
				double mouseX = e.getX();
				double mouseY = e.getY();
				mySelectedUnitManager.move(new Vector2(detranslateX(mouseX), detranslateY(mouseY)));
			}
		});
		myMainDisplay.getChildren().addAll(myDisplayables, myMoveWindowButtons);
	}
	
	private void initialize() {
		myDisplayables = new Group();
		initializeMoveButtons();
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
	
	@Override
	public void update(List<GameObject> gameObjects) {
		myDisplayables.getChildren().clear();
		for (GameObject go : gameObjects) {
			double xloc = translateX(go.getTransform().getPosition().getX());
			double yloc = translateY(go.getTransform().getPosition().getY());
			go.getRenderer().getDisp().setX(xloc);
			go.getRenderer().getDisp().setY(yloc);
			myDisplayables.getChildren().add(go.getRenderer().getDisp());
		}
	}

	@Override
	public Node getNodes() {
		return myMainDisplay;
	}
	
	private double translateX(double x) {
		double retX = x - myCurrentXCoor;
		return retX;
	}
	
	private double translateY(double y) {
		double retY = y - myCurrentYCoor;
		return retY;
	}
	
	private double detranslateX(double x){
		return x + myCurrentXCoor;
	}
	
	private double detranslateY(double y){
		return y + myCurrentYCoor;
	}
	
	private void updateCurrentWindow() {
		
	}
	
}
