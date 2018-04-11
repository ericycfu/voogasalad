package game_player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_player.visual_element.MainDisplay;
import game_player.visual_element.MiniMap;
import game_player.visual_element.TopPanel;
import game_player.visual_element.UnitDisplay;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * 
 * @author Siyuan Chen
 *
 */
public class GamePlayer {
	
	public static final int SCENE_SIZE_X = 1200;
	public static final int SCENE_SIZE_Y = 700;
	public static final double BOTTOM_HEIGHT = 0.25;
	public static final double MINIMAP_WIDTH = 0.25;
	public static final double INFO_DISPLAY_WIDTH = 0.50;
	public static final double ACTION_DISPLAY_WIDTH = 0.25;
	public static final double TOP_HEIGHT = 0.05;
	private double myCurrentXCoor; // current MAP-x-coordinate of window left corner
	private double myCurrentYCoor; // GET FROM MAIN DISPLAY
	private List<GameObject> myGameObjects;
	private List<GameObject> mySelectedGameObjects;
	private TopPanel myTopPanel;
	private MiniMap myMiniMap;
	private UnitDisplay myUnitDisplay;
	private MainDisplay myMainDisplay;
	private Group myRoot;
	private Map<String, List<String>> myUnitSkills;
	private Map<String, Image> mySkillImages;
	private Map<String, Image> myUnitInfoImg;
	private Map<String, Image> myUnitDispImg;
	private Scene myScene;
	private String myCurrentAction;
	
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
		
		myMainDisplay = new MainDisplay(SCENE_SIZE_X, (1-TOP_HEIGHT-BOTTOM_HEIGHT)*SCENE_SIZE_Y);
		Node mainDisp = myMainDisplay.getNodes();
		mainDisp.setLayoutY(TOP_HEIGHT*SCENE_SIZE_Y);
		myRoot.getChildren().add(mainDisp);
		
		myTopPanel = new TopPanel(SCENE_SIZE_X, TOP_HEIGHT*SCENE_SIZE_Y);
		myRoot.getChildren().add(myTopPanel.getNodes());
		
		myMiniMap = new MiniMap(MINIMAP_WIDTH*SCENE_SIZE_X, BOTTOM_HEIGHT*SCENE_SIZE_Y);
		Node minimap = myMiniMap.getNodes();
		minimap.setLayoutY((1-BOTTOM_HEIGHT)*SCENE_SIZE_Y);
		myRoot.getChildren().add(minimap);
		
		myUnitDisplay = new UnitDisplay(INFO_DISPLAY_WIDTH*SCENE_SIZE_X, BOTTOM_HEIGHT*SCENE_SIZE_Y, ACTION_DISPLAY_WIDTH*SCENE_SIZE_X, BOTTOM_HEIGHT*SCENE_SIZE_Y, myUnitSkills, mySkillImages);
		Node unitDisp = myUnitDisplay.getNodes();
		unitDisp.setLayoutX(MINIMAP_WIDTH*SCENE_SIZE_X);
		unitDisp.setLayoutY((1-BOTTOM_HEIGHT)*SCENE_SIZE_Y);
		myRoot.getChildren().add(unitDisp);
		
		myScene = new Scene(myRoot, SCENE_SIZE_X, SCENE_SIZE_Y);
	}

	public Scene getScene() {
		return myScene;
	}
	
	public void update(List<GameObject> gameobject) {
		myCurrentAction = ""; // returns action selection to default
		
		List<GameObject> displayGameObjects = filterDisplayGameObjects(gameobject);
		myTopPanel.update(displayGameObjects); //resources
		myMiniMap.update(displayGameObjects);
		myUnitDisplay.update(mySelectedGameObjects); // selection TO-DO
		//myMainDisplay.update(displayGameObjects);
		
		myCurrentAction = myUnitDisplay.getUnitActionDisp().getCurrentAction();
		if (!myCurrentAction.equals("")) {
			myScene.setCursor(Cursor.CROSSHAIR);
		}
		myUnitDisplay.getUnitActionDisp().setDefault();
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
		if (y>myCurrentYCoor & y<myCurrentYCoor+SCENE_SIZE_Y*(1-TOP_HEIGHT-BOTTOM_HEIGHT)) {
			return true;
		}
		return false;
	}
	
	private void updateCurrentWindow() {
		
	}
	
}
