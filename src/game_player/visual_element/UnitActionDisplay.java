package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import transform_library.Vector2;

public class UnitActionDisplay implements VisualUpdate{
	
	public static final int ACTION_GRID_WIDTH = 4;
	public static final int ACTION_GRID_HEIGHT = 3;
	public static final double JAVAFX_IMAGEVIEW_SHRINK_RATIO = 0.8;
	public static final int DEFAULT_CURRENT_ACTION_ID = -1;
	public static final String DEFAULT_BUTTON_IMAGE_PATH = "default_icon.png";
	public static final String CANCEL_BUTTON_IMAGE_PATH = "cancel_icon.png";
	private int myCurrentActionID;
	private GridPane myGridPane;
	private double myCellWidth;
	private double myCellHeight;
	private GameObject myBuildTarget;
	Map<String, List<SkillButton>> myUnitSkills;
	Map<String, Image> mySkillImages;
	private GameObject myCurrentGameObject;
	
	public UnitActionDisplay(double width, double height, Map<String, List<SkillButton>> unitSkills) {
		myUnitSkills = unitSkills;
		myCellWidth = width/ACTION_GRID_WIDTH;
		myCellHeight = height/ACTION_GRID_HEIGHT;
		myGridPane = new GridPane();
		myGridPane.setPrefWidth(width);
		myGridPane.setPrefHeight(height);
		myGridPane.setStyle(UnitDisplay.WHITE_BACKGROUND_CSS);
		defaultCurrentActionID();
		initialize();
	}
	
	public void defaultCurrentActionID() {
		setCurrentActionID(DEFAULT_CURRENT_ACTION_ID);
	}
	
	public void setBuildTarget(GameObject go) {
		myBuildTarget = go;
	}
	
	public GameObject getBuildTarget() {
		return myBuildTarget;
	}
	
	private void initialize() {
		for (int i = 0; i < ACTION_GRID_WIDTH; i++) {
			for (int j = 0; j < ACTION_GRID_HEIGHT; j++) {
				completeGridPaneWithDefaultButtons(i, j);
			}
		}
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		if (gameObjects.isEmpty()) {
			myCurrentGameObject = new GameObject(new Vector2(0,0));
			myGridPane.getChildren().clear();
			initialize();
			return;
		}
		GameObject gameObject = gameObjects.get(0);
		if (gameObject==myCurrentGameObject) {
			return;
		}
		myCurrentGameObject = gameObject;
		List<SkillButton> unitSkills = myUnitSkills.get(gameObject.getName());
		fill(unitSkills);
	}
	
	public void fill(List<SkillButton> unitSkills) {
		myGridPane.getChildren().clear();
		for (int i = 0; i < 12; i++) {
			if (i < unitSkills.size()) {
				myGridPane.add(unitSkills.get(i), i%ACTION_GRID_WIDTH, i/ACTION_GRID_WIDTH);
			}
			else {
				completeGridPaneWithDefaultButtons(i%ACTION_GRID_WIDTH, i/ACTION_GRID_WIDTH);
			}
		}
	}
	
	private void completeGridPaneWithDefaultButtons(int row, int col) {
		SkillButton cell = new SkillButton();
		cell.setMaxSize(myCellWidth, myCellHeight);
		ImageView imgv = new ImageView(new Image(DEFAULT_BUTTON_IMAGE_PATH));
		imgv.setFitHeight(myCellHeight*JAVAFX_IMAGEVIEW_SHRINK_RATIO);
		imgv.setFitWidth(myCellWidth*JAVAFX_IMAGEVIEW_SHRINK_RATIO);
		cell.setGraphic(imgv);
		myGridPane.add(cell, row, col);
	}
	
	
	@Override
	public Node getNodes() {
		return myGridPane;
	}
	
	public int getCurrentActionID() {
		return myCurrentActionID;
	}

	public void setCurrentActionID(int myCurrentActionID) {
		this.myCurrentActionID = myCurrentActionID;
	}
	
	public void setUnitSkills(Map<String, List<SkillButton>> newmap) {
		this.myUnitSkills = newmap;
	}
	
}
