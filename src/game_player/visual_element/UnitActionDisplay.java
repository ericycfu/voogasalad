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
	private GridPane myGridPane;
	private double myCellWidth;
	private double myCellHeight;
	private String myCurrentAction;
	Map<String, List<String>> myUnitSkills;
	Map<String, Image> mySkillImages;
	
	public UnitActionDisplay(double width, double height, Map<String, List<String>> unitSkills, 
			Map<String, Image> skillImages) {
		mySkillImages = skillImages;
		myUnitSkills = unitSkills;
		myCellWidth = width/ACTION_GRID_WIDTH;
		myCellHeight = height/ACTION_GRID_HEIGHT;
		
		myGridPane = new GridPane();
		myGridPane.setMaxWidth(width);
		myGridPane.setMaxHeight(height);
		myGridPane.setStyle("-fx-background-color: #FFFFFF;");
		
		initialize();
	}
	
	private void initialize() {
		myCurrentAction = "";
		for (int i = 0; i < ACTION_GRID_WIDTH; i++) {
			for (int j = 0; j < ACTION_GRID_HEIGHT; j++) {
				Image img = new Image("attack_icon.png");
				SkillButton cell = new SkillButton();
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
		if (gameObjects.isEmpty()) return;
		GameObject gameObject = gameObjects.get(0);
		ArrayList<String> skills = (ArrayList<String>) myUnitSkills.get(gameObject.getName());
		if (skills==null) return;
		for (int i = 0; i < skills.size(); i++) {
			if (i < 12) {
				SkillButton curr = (SkillButton) myGridPane.getChildren().get(i);
				curr.setSkillName(skills.get(i));
				curr.setGraphic(new ImageView(mySkillImages.get(skills.get(i))));
				curr.setOnAction(e -> {
					myCurrentAction = curr.getSkillName();
					//TO-BE-COMPLETED
				});
			}
			else return;
		}
	}
	
	public String getCurrentAction() {
		return myCurrentAction;
	}
	
	public void setDefault() {
		myCurrentAction = "";
	}
	
	@Override
	public Node getNodes() {
		return myGridPane;
	}
	
	
	
}
