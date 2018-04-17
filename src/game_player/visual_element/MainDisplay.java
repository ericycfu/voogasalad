 package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_player.GamePlayer;
import game_player.SelectedUnitManager;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import transform_library.Vector2;

public class MainDisplay implements VisualUpdate {

	public static final double WINDOW_STEP_SIZE = 50;
	public static final double MAP_DISPLAY_RATIO = 4;
	private double myCurrentXCoor = 0; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor = 0; 
	private double myWidth;
	private double myHeight;
	private List<GameObject> myDisplayGameObjects;
	private Group myDisplayables;
	private Group myMoveWindowButtons;
	private SelectedUnitManager mySelectedUnitManager;
	//private double currentPressedLocation; 
	private Group myMainDisplay;
	private ImageView myMap;
	
	private double myMouseXInitPosition;
	private double myMouseYInitPosition;
	private double myMouseXFinalPosition;
	private double myMouseYFinalPosition;
	private boolean isMultipleSelectAvailable;
	
	public MainDisplay(SelectedUnitManager selectedUnitManager, double width, double height) {
		myDisplayGameObjects = new ArrayList<>();
		mySelectedUnitManager = selectedUnitManager;
		myMainDisplay = new Group();
		myWidth = width;
		myHeight = height;
		initialize();
		myMap = new ImageView(new Image("map.jpeg"));
		myMap.setFitWidth(myWidth*MAP_DISPLAY_RATIO);
		myMap.setFitHeight(myHeight*MAP_DISPLAY_RATIO);
		//myMap.setFill(Color.GREEN);
		myMap.setOnMouseClicked(e -> {
			if (e.getButton()==MouseButton.SECONDARY) {
				double mouseX = e.getX();
				double mouseY = e.getY();
				mySelectedUnitManager.move(new Vector2(detranslateX(mouseX), detranslateY(mouseY)));
			}
		});
		myMap.toBack();
		myMainDisplay.getChildren().add(myMap);
		myMainDisplay.getChildren().addAll(myDisplayables, myMoveWindowButtons);
		initializeMultipleUnitSelect();
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
			if (myCurrentXCoor < myWidth*MAP_DISPLAY_RATIO) {
				myCurrentXCoor += WINDOW_STEP_SIZE;
			}
		});
		myMoveWindowButtons.getChildren().add(right);
		
		Button left = new Button();
		left.setLayoutX(0);
		left.setLayoutY(myHeight/2 - 60);
		left.setGraphic(new ImageView(new Image("arrow_left.png")));
		left.setOnMousePressed(e -> {
			if (myCurrentXCoor > 0) {
				myCurrentXCoor -= WINDOW_STEP_SIZE;
			}
		});
		myMoveWindowButtons.getChildren().add(left);
		
		Button up = new Button();
		up.setLayoutX(myWidth/2 - 60);
		up.setLayoutY(0);
		up.setGraphic(new ImageView(new Image("arrow_up.png")));
		up.setOnMousePressed(e -> {
			if (myCurrentYCoor > 0) {
				myCurrentYCoor -= WINDOW_STEP_SIZE;
			}
		});
		myMoveWindowButtons.getChildren().add(up);
		
		Button down = new Button();
		down.setLayoutX(myWidth/2 - 60);
		down.setLayoutY(myHeight - 60);
		down.setGraphic(new ImageView(new Image("arrow_down.png")));
		down.setOnMousePressed(e -> {
			if (myCurrentYCoor < myHeight*MAP_DISPLAY_RATIO) {
				myCurrentYCoor += WINDOW_STEP_SIZE;
			}
		});
		myMoveWindowButtons.getChildren().add(down);
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		myDisplayGameObjects = filterDisplayGameObjects(gameObjects);
		List<ImageView> imgvList = new ArrayList<>();
		for (GameObject go : myDisplayGameObjects) {
			double xloc = translateX(go.getTransform().getPosition().getX());
			double yloc = translateY(go.getTransform().getPosition().getY());
			go.getRenderer().getDisp().setX(xloc);
			go.getRenderer().getDisp().setY(yloc);
			imgvList.add(go.getRenderer().getDisp());
		}	
		for (int i = myDisplayables.getChildren().size() - 1; i >= 0; i--) {
			Node n = myDisplayables.getChildren().get(i);
			if (!imgvList.contains(n)) {
				myDisplayables.getChildren().remove(n);
			}
			imgvList.remove(n);
		}
		for (ImageView imgv : imgvList) {
			myDisplayables.getChildren().add(imgv);
		}
		updateCurrentWindow();
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
		myMap.setX(-myCurrentXCoor);
		myMap.setY(-myCurrentYCoor);
	}
	
	private void initializeMultipleUnitSelect() {
		myMap.setOnMousePressed(e -> {
			if (e.getButton()==MouseButton.PRIMARY) {
				myMouseXInitPosition = e.getSceneX();
				myMouseYInitPosition = e.getY() - GamePlayer.SCENE_SIZE_Y*GamePlayer.TOP_HEIGHT;
				isMultipleSelectAvailable = true;
			}
		});
		myMap.setOnMouseReleased(e -> {
			if (isMultipleSelectAvailable && e.getButton()==MouseButton.PRIMARY) {
				mySelectedUnitManager.clear();
				myMouseXFinalPosition = e.getSceneX();
				myMouseYFinalPosition = e.getY() - GamePlayer.SCENE_SIZE_Y*GamePlayer.TOP_HEIGHT;
				for (GameObject go : myDisplayGameObjects) {
					if (isInSelectionWindow(go.getRenderer().getDisp().getX(), go.getRenderer().getDisp().getY())) {
						mySelectedUnitManager.add(go);
					}
				}
			}
			isMultipleSelectAvailable = false;
			
		});
	}
	
	
	private boolean isInSelectionWindow(double x, double y) {
		boolean isX;
		boolean isY;
		if (myMouseXInitPosition < myMouseXFinalPosition) {
			isX = (x < myMouseXFinalPosition) && (x > myMouseXInitPosition);
		}
		else {
			isX = (x > myMouseXFinalPosition) && (x < myMouseXInitPosition);
		}
		
		if (myMouseYInitPosition < myMouseYFinalPosition) {
			isY = (y < myMouseYFinalPosition) && (y > myMouseYInitPosition);
		}
		else {
			isY = (y > myMouseXFinalPosition) && (y < myMouseXInitPosition);
		}
		return isX && isY;
	}
	
	private List<GameObject> filterDisplayGameObjects(List<GameObject> gameobjects) {
		List<GameObject> ret = new ArrayList<>();
		for (GameObject go : gameobjects) {
			if (isXInWindow(go.getTransform().getPosition().getX()) & isYInWindow(go.getTransform().getPosition().getY())) {
				ret.add(go);
			}
		}
		return ret;
	}
	
	private boolean isXInWindow(double x) {
		if (x>myCurrentXCoor & x<myCurrentXCoor+GamePlayer.SCENE_SIZE_X) {
			return true;
		}
		return false;
	}
	
	private boolean isYInWindow(double y) {
		if (y>myCurrentYCoor & y<myCurrentYCoor+GamePlayer.SCENE_SIZE_Y*(1-GamePlayer.TOP_HEIGHT-GamePlayer.BOTTOM_HEIGHT)) {
			return true;
		}
		return false;
	}
}
