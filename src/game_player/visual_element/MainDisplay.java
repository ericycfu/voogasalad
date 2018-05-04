 package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import game_player.selected_unit_manager.SelectedUnitManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pathfinding.GridMap;
import transform_library.Vector2;

/**
 * The MainDisplay class displays all the units currently in the view of the user and establishes the interactions (unit-to-map and unit-to-unit) correctly. 
 * 
 * It records its relative position on the map, based on which all existing game units get filtered to have the ones on the current view be displayed. 
 * 
 * Group selection is implemented in this class so that the user is allowed 
 * 
 * @author Frank Yin, Siyuan Chen
 *
 */
public class MainDisplay implements VisualUpdate {
	
	private static final double MOVEBUTTONSIZE = 60;
	
	private double myCurrentXCoor = 0; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor = 0; 
	private double myWidth;
	private double myHeight;
	private List<GameObject> myDisplayGameObjects;
	private Rectangle mySelectionBox;
	private Group myDisplayables;
	private Group myMoveWindowButtons;
	private SelectedUnitManager mySelectedUnitManager;
	private Group myMainDisplay;
	private ImageView myMap;
	private GameObjectManager myGameObjectManager;
	private UnitActionDisplay myUnitActionDisp;
	
	private double myMouseXInitPosition;
	private double myMouseYInitPosition;
	private double myMouseXFinalPosition;
	private double myMouseYFinalPosition;
	private boolean isMultipleSelectAvailable;
	
	private BooleanProperty isUpHovered;
	private BooleanProperty isDownHovered;
	private BooleanProperty isLeftHovered;
	private BooleanProperty isRightHovered;
	
	/**
	 * constructor for main display
	 * @param selectedUnitManager selectedUnitManager of Gameplayer current instance
	 * @param gom current instance of GameObjectManager
	 * @param uadisp current instance of UnitActionDisplay
	 * @param width MainDisplay width
	 * @param height MainDisplay height
	 * @param map game map
	 */
	public MainDisplay(SelectedUnitManager selectedUnitManager, GameObjectManager gom, UnitActionDisplay uadisp, double width, double height, ImageView map) {
		myUnitActionDisp = uadisp;
		myGameObjectManager = gom;
		myDisplayGameObjects = new ArrayList<>();
		mySelectedUnitManager = selectedUnitManager;
		myMainDisplay = new Group();
		myWidth = width;
		myHeight = height;
		myMap = map;
		initialize();
		myMainDisplay.getChildren().addAll(myMap, myDisplayables, myMoveWindowButtons);
	}
	
	private void initialize() {
		myDisplayables = new Group();
		initializeMapClick();
		initializeMultipleUnitSelect();
		initializeMoveButtons();
	}
	
	private void initializeMapClick() {
		myMap.setOnMouseClicked(e -> {
			double mouseX = e.getX();
			double mouseY = e.getY();
			if (e.getButton() == MouseButton.SECONDARY && this.myUnitActionDisp.getCurrentActionID() == -1) {
				mySelectedUnitManager.move(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), myGameObjectManager, 
						new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
			}
			else if (e.getButton() == MouseButton.SECONDARY && this.myUnitActionDisp.getCurrentActionID() != -1) {
				int ID = this.myUnitActionDisp.getCurrentActionID();
				initializeMapClickInteraction(ID, mouseX, mouseY);
				try {
					if (mySelectedUnitManager.getSelectedUnits().get(0).accessLogic().accessInteractions().getInteraction(ID).isBuild()) {
						GameObject unitToBeBuilt = myUnitActionDisp.getBuildTarget(); 
						unitToBeBuilt.getTransform().setPosition(new Vector2(detranslateX(mouseX), detranslateY(mouseY)));
						mySelectedUnitManager.takeInteraction(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), myUnitActionDisp.getBuildTarget(), ID, myGameObjectManager, new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
					}
					else {
						mySelectedUnitManager.takeInteraction(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), null, ID, myGameObjectManager, new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
					}
					myUnitActionDisp.setCurrentActionID(-1);
				} catch (UnmodifiableGameObjectException e1) {
					// do nothing
				}	
			}
		});
		myMap.toBack();
	}
	
	private void initializeMapClickInteraction(int ID, double mouseX, double mouseY) {
		try {
			if (mySelectedUnitManager.getSelectedUnits().get(0).accessLogic().accessInteractions().getInteraction(ID).isBuild()) {
				GameObject unitToBeBuilt = myUnitActionDisp.getBuildTarget(); 
				unitToBeBuilt.getTransform().setPosition(new Vector2(detranslateX(mouseX), detranslateY(mouseY)));
				mySelectedUnitManager.takeInteraction(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), myUnitActionDisp.getBuildTarget(), ID, myGameObjectManager, new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
			}
			else {
				mySelectedUnitManager.takeInteraction(new Vector2(detranslateX(mouseX), detranslateY(mouseY)), null, ID, myGameObjectManager, new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
			}
			myUnitActionDisp.defaultCurrentActionID();
		} catch (UnmodifiableGameObjectException e1) {
			// do nothing
		}	
	}
	
	private void initializeMultipleUnitSelect() {
		setMultipleUnitSelectStartEvent();
		setMultipleUnitSelectEndEvent();
	}
	
	private void setMultipleUnitSelectStartEvent() {
		myMap.setOnMousePressed(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				myMouseXInitPosition = e.getSceneX();
				myMouseYInitPosition = e.getY() - GamePlayer.SCENE_SIZE_Y * GamePlayer.TOP_HEIGHT;
				createSelectionBox();
				isMultipleSelectAvailable = true;
			}
		});
	}
	
	private void setMultipleUnitSelectEndEvent() {
		myMap.setOnMouseReleased(e -> {
			if (isMultipleSelectAvailable && e.getButton() == MouseButton.PRIMARY) {
				mySelectedUnitManager.clear();
				myUnitActionDisp.defaultCurrentActionID();
				myMouseXFinalPosition = e.getSceneX();
				myMouseYFinalPosition = e.getY() - GamePlayer.SCENE_SIZE_Y * GamePlayer.TOP_HEIGHT;
				for (GameObject go : myDisplayGameObjects) {
					if (isInSelectionWindow(go.getRenderer().getDisp().getX(), go.getRenderer().getDisp().getY())) {
						mySelectedUnitManager.add(go);
					}
				}
				mySelectionBox.setVisible(false);
			}
			isMultipleSelectAvailable = false;
		});
	}

	private void createSelectionBox() {
		mySelectionBox = new Rectangle();
		mySelectionBox.setX(myMouseXInitPosition);
		mySelectionBox.setY(myMouseYInitPosition + GamePlayer.SCENE_SIZE_Y * GamePlayer.TOP_HEIGHT);
		mySelectionBox.setStroke(Color.BLACK);
		mySelectionBox.setFill(Color.TRANSPARENT);
		myMainDisplay.getChildren().add(mySelectionBox);
		myMap.setOnMouseDragged(e -> {
			if(e.getButton() == MouseButton.PRIMARY) {
				double xDiff = e.getSceneX() - myMouseXInitPosition;
				double yDiff = e.getSceneY() - myMouseYInitPosition - 2 * GamePlayer.SCENE_SIZE_Y * GamePlayer.TOP_HEIGHT;
				if(xDiff < 0) {
					mySelectionBox.setX(myMouseXInitPosition + xDiff);
				}
				if(yDiff < 0) {
					mySelectionBox.setY(myMouseYInitPosition + GamePlayer.SCENE_SIZE_Y * GamePlayer.TOP_HEIGHT + yDiff);
				}
				mySelectionBox.setWidth(Math.abs(xDiff));
				mySelectionBox.setHeight(Math.abs(yDiff));
			}
		});
	}
	
	private void initializeMoveButtons() {
		myMoveWindowButtons = new Group();
		
		isRightHovered = new SimpleBooleanProperty(false);
		Button right = new MoveButton("right", isRightHovered, myWidth - MOVEBUTTONSIZE, myHeight/2 - MOVEBUTTONSIZE);
		
		isLeftHovered = new SimpleBooleanProperty(false);
		Button left = new MoveButton("left", isLeftHovered, 0, myHeight/2 - MOVEBUTTONSIZE);
		
		isUpHovered = new SimpleBooleanProperty(false);
		Button up = new MoveButton("up", isUpHovered, myWidth/2 - MOVEBUTTONSIZE, 0);
		
		isDownHovered = new SimpleBooleanProperty(false);
		Button down = new MoveButton("down", isDownHovered, myWidth/2 - MOVEBUTTONSIZE, myHeight - MOVEBUTTONSIZE);
		
		myMoveWindowButtons.getChildren().addAll(right, left, up, down);
	}
	
	private void updateCurrentXYCoor() {
		if (isDownHovered.getValue() && myCurrentYCoor < myHeight*GamePlayer.MAP_DISPLAY_RATIO - GamePlayer.SCENE_SIZE_Y*(1-GamePlayer.TOP_HEIGHT-GamePlayer.BOTTOM_HEIGHT)) {
			myCurrentYCoor += GamePlayer.WINDOW_STEP_SIZE;
		}
		if (isUpHovered.getValue() && myCurrentYCoor > 0) {
			myCurrentYCoor -= GamePlayer.WINDOW_STEP_SIZE;
		}
		if (isLeftHovered.getValue() && myCurrentXCoor > 0) {
			myCurrentXCoor -= GamePlayer.WINDOW_STEP_SIZE;
		}
		if (isRightHovered.getValue() && myCurrentXCoor < myWidth*GamePlayer.MAP_DISPLAY_RATIO - GamePlayer.SCENE_SIZE_X) {
			myCurrentXCoor += GamePlayer.WINDOW_STEP_SIZE;
		}
	}
	
	@Override
	/**
	 * updates main display, its window and displayed units per frame
	 */
	public void update(List<GameObject> gameObjects) {
		myDisplayGameObjects = filterDisplayGameObjects(gameObjects);
		List<ImageView> imgvList = new ArrayList<>();
		updatePositionChanges(imgvList);
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
		
		updateCurrentXYCoor();
		updateCurrentWindow();
	}
	private void updatePositionChanges(List<ImageView> imgvList) {
		for (GameObject go : myDisplayGameObjects) {
			double xloc = translateX(go.getTransform().getPosition().getX());
			double yloc = translateY(go.getTransform().getPosition().getY());
			go.getRenderer().getDisp().setX(xloc);
			go.getRenderer().getDisp().setY(yloc);
			imgvList.add(go.getRenderer().getDisp());
		}	
	}

	private void updatePositionChanges(List<ImageView> imgvList) {
		myDisplayGameObjects.forEach(go -> {
			double xloc = translateX(go.getTransform().getPosition().getX());
			double yloc = translateY(go.getTransform().getPosition().getY());
			go.getRenderer().getDisp().setX(xloc);
			go.getRenderer().getDisp().setY(yloc);
			imgvList.add(go.getRenderer().getDisp());
		});
	}

	@Override
	/**
	 * gets current javafx node objects in main display
	 */
	public Node getNodes() {
		return myMainDisplay;
	}
	
	public double translateX(double x) {
		return x - myCurrentXCoor;
	}
	
	public double translateY(double y) {
		return y - myCurrentYCoor;
	}
	
	public double detranslateX(double x){
		return x + myCurrentXCoor;
	}
	
	public double detranslateY(double y){
		return y + myCurrentYCoor;
	}
	
	public double getMapWidth() {
		return myMap.getFitWidth();
	}
	
	public double getMapHeight() {
		return myMap.getFitHeight();
	}
	
	private void updateCurrentWindow() {
		myMap.setX(-myCurrentXCoor);
		myMap.setY(-myCurrentYCoor);
	}
	
	private boolean isInSelectionWindow(double x, double y) {
		boolean isX = Math.abs(x - myMouseXInitPosition) < Math.abs(myMouseXInitPosition - myMouseXFinalPosition) && Math.abs(x - myMouseXFinalPosition) < Math.abs(myMouseXInitPosition - myMouseXFinalPosition);
		boolean isY = Math.abs(y - myMouseYInitPosition) < Math.abs(myMouseYInitPosition - myMouseYFinalPosition) && Math.abs(y - myMouseYFinalPosition) < Math.abs(myMouseYInitPosition - myMouseYFinalPosition);
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
		return x > myCurrentXCoor && x < myCurrentXCoor + GamePlayer.SCENE_SIZE_X;
	}
	
	private boolean isYInWindow(double y) {
		return y > myCurrentYCoor && y < myCurrentYCoor + GamePlayer.SCENE_SIZE_Y * (1 - GamePlayer.TOP_HEIGHT - GamePlayer.BOTTOM_HEIGHT);
	}
}
