package game_player.visual_element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UnitActionDisplay implements VisualUpdate{
	
	public static final int ACTION_GRID_LENGTH = 4;
	public static final int ACTION_GRID_WIDTH = 3;
	public static final int CELL_SIZE = 50;
	private double myLeftCornerXCoor;
	private double myLeftCornerYCoor;
	private String myCurrentAction;
	SkillButton[][] myActionsGrid;
	Map<String, List<String>> myUnitSkills;
	Map<String, Image> mySkillImages;
	
	public UnitActionDisplay(double xcoor, double ycoor, Map<String, List<String>> unitSkills, 
			Map<String, Image> skillImages) {
		mySkillImages = skillImages;
		myUnitSkills = unitSkills;
		myLeftCornerXCoor = xcoor;
		myLeftCornerYCoor = ycoor;
		initialize();
	}
	
	private void initialize() {
		myCurrentAction = "";
		myActionsGrid = new SkillButton[ACTION_GRID_LENGTH][ACTION_GRID_WIDTH];
		for (int i = 0; i < myActionsGrid.length; i++) {
			for (int j = 0; j < myActionsGrid[0].length; j++) {
				Image img = new Image("images/default_icon.png");
				SkillButton cell = new SkillButton();
				cell.setGraphic(new ImageView(img));
				cell.setLayoutX(myLeftCornerXCoor + 50*i);
				cell.setLayoutY(myLeftCornerYCoor + 50*j);
				myActionsGrid[i][j] = cell;
			}
		}
	}

	@Override
	public void update(List<GameObject> gameObjects) {
		if (gameObjects.isEmpty()) return;
		GameObject gameObject = gameObjects.get(0);
		ArrayList<String> skills = (ArrayList<String>) myUnitSkills.get(gameObject.getName());
		for (int i = 0; i < skills.size(); i++) {
			if (i < 12) {
				SkillButton curr = myActionsGrid[i%4][i/4];
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
	
	@Override
	public Node getNodes() {
		Group group = new Group();
		for (int i = 0; i < myActionsGrid.length; i++) {
			for (int j = 0; j < myActionsGrid[0].length; j++) {
				group.getChildren().add(myActionsGrid[i][j]);
			}
		}
		return group;
	}
	
}
