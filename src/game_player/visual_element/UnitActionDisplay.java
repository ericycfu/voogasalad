package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class UnitActionDisplay implements VisualUpdate{
	
	public static final int ACTION_GRID_WIDTH = 4;
	public static final int ACTION_GRID_HEIGHT = 3;
	private int myCurrentActionID;
	private GridPane myGridPane;
	private double myCellWidth;
	private double myCellHeight;
	private GameObject myBuildTarget;
	Map<String, List<SkillButton>> myUnitSkills;
	Map<String, Image> mySkillImages;
	
	public UnitActionDisplay(double width, double height, Map<String, List<SkillButton>> unitSkills) {
		myUnitSkills = unitSkills;
		myCellWidth = width/ACTION_GRID_WIDTH;
		myCellHeight = height/ACTION_GRID_HEIGHT;
		myGridPane = new GridPane();
		myGridPane.setMaxWidth(width);
		myGridPane.setPrefHeight(height);
		myGridPane.setStyle("-fx-background-color: #FFFFFF;");
		setCurrentActionID(-1);
		initialize();
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
				Image img = new Image("default_icon.png");
				SkillButton cell = new SkillButton();
				cell.setMaxSize(myCellWidth, myCellHeight);
				ImageView imgv = new ImageView(img);
				imgv.setFitHeight(myCellHeight*0.8);
				imgv.setFitWidth(myCellWidth*0.8);
				cell.setGraphic(imgv);
				myGridPane.add(cell, i, j);
			}
		}
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		myGridPane.getChildren().clear();
		if (gameObjects.isEmpty()) {
			initialize();
			return;
		}
		GameObject gameObject = gameObjects.get(0);
		List<SkillButton> unitSkills = myUnitSkills.get(gameObject.getName());
		for (int i = 0; i < unitSkills.size() && i < 12; i++) {
			myGridPane.add(unitSkills.get(i), i%ACTION_GRID_WIDTH, i/ACTION_GRID_HEIGHT);
		}
	}
	
	public void build(List<SkillButton> sblist) {
		myGridPane.getChildren().clear();
		for (int i = 0; i < sblist.size()-1; i++) {
			myGridPane.add(sblist.get(i), i%ACTION_GRID_WIDTH, i/ACTION_GRID_HEIGHT);
		}
		myGridPane.add(sblist.get(sblist.size()-1), ACTION_GRID_WIDTH, ACTION_GRID_HEIGHT);
	}
	
	private SkillButton getCancelButton() {
		SkillButton ret = new SkillButton();
		
		return ret;
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
	
}
