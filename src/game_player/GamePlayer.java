package game_player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_player.visual_element.MiniMap;
import game_player.visual_element.TopPanel;
import game_player.visual_element.UnitDisplay;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 
 * @author Siyuan Chen
 *
 */
public class GamePlayer {
	
	public static final int SCENE_SIZE_X = 1000;
	public static final int SCENE_SIZE_Y = 1000;
	private double myCurrentXCoor; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor; 
	private List<GameObject> myGameObjects;
	private List<GameObject> mySelectedGameObjects;
	private TopPanel myTopPanel;
	private MiniMap myMiniMap;
	private UnitDisplay myUnitDisplay;
	private Group myRoot;
	private Map<String, List<String>> myUnitSkills;
	private Map<String, Image> mySkillImages;
	private Map<String, Image> myUnitInfoImg;
	private Map<String, Image> myUnitDispImg;
	
	public GamePlayer(List<GameObject> gameobjects, Map<String, List<String>> unitSkills, Map<String, Image> skillImages, Map<String, Image> unitInfoImgs,  Map<String, Image> unitDispImgs) {
		myGameObjects = gameobjects;
		myUnitInfoImg = unitInfoImgs;
		myUnitDispImg = unitDispImgs;
		myUnitSkills = unitSkills;
		mySkillImages = skillImages;
		initialize();
	}
	
	private void initialize() {
		myRoot = new Group();
		mySelectedGameObjects = new ArrayList<GameObject>();
		myTopPanel = new TopPanel();
		myMiniMap = new MiniMap(0, 0.85*SCENE_SIZE_Y,0.15*SCENE_SIZE_X,0.15*SCENE_SIZE_X, Color.BLACK, Color.GREENYELLOW);
		//myUnitDisplay = new UnitDisplay(0.15*SCENE_SIZE_X, 0.85*SCENE_SIZE_Y, 0.85*SCENE_SIZE_X, 0.85*SCENE_SIZE_Y, myUnitSkills, mySkillImages);
		myRoot.getChildren().add(myTopPanel.getNodes());
		//myRoot.getChildren().add(myMiniMap.getNodes());
		//myRoot.getChildren().add(myUnitDisplay.getNodes());
	}

	public Scene setScene(Stage gpStage) {
		Scene scene = new Scene(myRoot, SCENE_SIZE_X, SCENE_SIZE_Y);
		return scene;
	}
	
	public void update(List<GameObject> gameobject) {
		List<GameObject> displayGameObjects = filterDisplayGameObjects(gameobject);
		myTopPanel.update(displayGameObjects);
		myMiniMap.update(displayGameObjects);
		myUnitDisplay.update(displayGameObjects);
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
		if (x>myCurrentXCoor & x<myCurrentXCoor+SCENE_SIZE_X) {
			return true;
		}
		return false;
	}
	
	private boolean isYInWindow(double y) {
		if (y>myCurrentYCoor & y<myCurrentYCoor+SCENE_SIZE_Y*0.8) {
			return true;
		}
		return false;
	}
	
	private void updateCurrentWindow() {
		
	}
	
	private double translateX(double x) {
		// TO-DO
		return 0;
	}
	
	private double translateY(double y) {
		// TO-DO
		return 0;
	}
	
}