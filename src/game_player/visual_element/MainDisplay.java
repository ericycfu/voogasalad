 package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import game_player.SelectedUnitManager;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import pathfinding.GridMap;
import transform_library.Vector2;

public class MainDisplay implements VisualUpdate {
	
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
	private GameObjectManager myGameObjectManager;
	private UnitActionDisplay myUnitActionDisp;
	
	private double myMouseXInitPosition;
	private double myMouseYInitPosition;
	private double myMouseXFinalPosition;
	private double myMouseYFinalPosition;
	private boolean isMultipleSelectAvailable;
	
	private boolean isUpHovered;
	private boolean isDownHovered;
	private boolean isLeftHovered;
	private boolean isRightHovered;
	
	public MainDisplay(SelectedUnitManager selectedUnitManager, GameObjectManager gom, UnitActionDisplay uadisp, double width, double height, ImageView map) {
		myUnitActionDisp = uadisp;
		myGameObjectManager = gom;
		myDisplayGameObjects = new ArrayList<>();
		mySelectedUnitManager = selectedUnitManager;
		myMainDisplay = new Group();
		myWidth = width;
		myHeight = height;
		initialize();
		myMap = map;
		//myMap.setFill(Color.GREEN);
		myMap.setOnMouseClicked(e -> {
			double mouseX = e.getX();
			double mouseY = e.getY();
			if (e.getButton()==MouseButton.SECONDARY && this.myUnitActionDisp.getCurrentActionID() == -1) {
				System.out.println("current interaction id" + this.myUnitActionDisp.getCurrentActionID());
				mySelectedUnitManager.move(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), myGameObjectManager, 
						new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
			}
			else if (e.getButton()==MouseButton.SECONDARY && this.myUnitActionDisp.getCurrentActionID() != -1) {
				int ID = this.myUnitActionDisp.getCurrentActionID();
				System.out.println("current interaction id" + ID);
				try {
					if (mySelectedUnitManager.getSelectedUnits().get(0).accessLogic().accessInteractions().getInteraction(ID).isBuild()) {
						System.out.println("print maindisp");
						mySelectedUnitManager.takeInteraction(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), myUnitActionDisp.getBuildTarget(), ID, myGameObjectManager);
						myUnitActionDisp.setBuildTarget(new GameObject(new Vector2(-1, -1)));
					}
					else {
						System.out.println("print maindisp");
						mySelectedUnitManager.takeInteraction(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), null, ID, myGameObjectManager);
					}
					myUnitActionDisp.setCurrentActionID(-1);
				} catch (UnmodifiableGameObjectException e1) {
					// do nothing
				}
				
				
			}
			System.out.println(this.myUnitActionDisp.getCurrentActionID());
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
		right.setOnMouseEntered(e -> {
			isRightHovered = true;
		});
		right.setOnMouseExited(e -> isRightHovered = false);
		myMoveWindowButtons.getChildren().add(right);
		
		Button left = new Button();
		left.setLayoutX(0);
		left.setLayoutY(myHeight/2 - 60);
		left.setGraphic(new ImageView(new Image("arrow_left.png")));
		left.setOnMouseEntered(e -> {
			isLeftHovered = true;
		});
		left.setOnMouseExited(e -> isLeftHovered = false);
		myMoveWindowButtons.getChildren().add(left);
		
		Button up = new Button();
		up.setLayoutX(myWidth/2 - 60);
		up.setLayoutY(0);
		up.setGraphic(new ImageView(new Image("arrow_up.png")));
		up.setOnMouseEntered(e -> {
			isUpHovered = true;
		});
		up.setOnMouseExited(e -> isUpHovered = false);
		myMoveWindowButtons.getChildren().add(up);
		
		Button down = new Button();
		down.setLayoutX(myWidth/2 - 60);
		down.setLayoutY(myHeight - 60);
		down.setGraphic(new ImageView(new Image("arrow_down.png")));
		down.setOnMouseEntered(e -> {
			isDownHovered = true;
		});
		down.setOnMouseExited(e -> isDownHovered = false);
		myMoveWindowButtons.getChildren().add(down);
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		myDisplayGameObjects = filterDisplayGameObjects(gameObjects);
		List<ImageView> imgvList = new ArrayList<>();
		for (GameObject go : myDisplayGameObjects) {
			double xloc = translateX(go.getTransform().getPosition().getX());
			double yloc = translateY(go.getTransform().getPosition().getY());
			//System.out.println(xloc);
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
		
		if (isDownHovered && myCurrentYCoor < myHeight*GamePlayer.MAP_DISPLAY_RATIO - GamePlayer.SCENE_SIZE_Y*(1-GamePlayer.TOP_HEIGHT-GamePlayer.BOTTOM_HEIGHT)) {
			myCurrentYCoor += GamePlayer.WINDOW_STEP_SIZE;
		}
		if (isUpHovered && myCurrentYCoor > 0) {
			myCurrentYCoor -= GamePlayer.WINDOW_STEP_SIZE;
		}
		if (isLeftHovered && myCurrentXCoor > 0) {
			myCurrentXCoor -= GamePlayer.WINDOW_STEP_SIZE;
		}
		if (isRightHovered && myCurrentXCoor < myWidth*GamePlayer.MAP_DISPLAY_RATIO - GamePlayer.SCENE_SIZE_X) {
			myCurrentXCoor += GamePlayer.WINDOW_STEP_SIZE;
		}
		updateCurrentWindow();
		
	}

	@Override
	public Node getNodes() {
		return myMainDisplay;
	}
	
	public double translateX(double x) {
		double retX = x - myCurrentXCoor;
		return retX;
	}
	
	public double translateY(double y) {
		double retY = y - myCurrentYCoor;
		return retY;
	}
	
	public double detranslateX(double x){
		return x + myCurrentXCoor;
	}
	
	public double detranslateY(double y){
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
				this.myUnitActionDisp.setCurrentActionID(-1);
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
