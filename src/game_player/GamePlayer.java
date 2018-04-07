package game_player;

import java.util.List;
import java.util.Map;

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
	private TopPanel myTopPanel;
	private MiniMap myMiniMap;
	private UnitDisplay myUnitDisplay;
	private Group myRoot;
	private Map<String, List<String>> myUnitSkills;
	private Map<String, Image> mySkillImages;
	
	public GamePlayer(Map<String, List<String>> unitSkills, Map<String, Image> skillImages) {
		myUnitSkills = unitSkills;
		mySkillImages = skillImages;
		initialize();
	}
	
	private void initialize() {
		myRoot = new Group();
		myTopPanel = new TopPanel();
		myMiniMap = new MiniMap(0, 0.85*SCENE_SIZE_Y,0.15*SCENE_SIZE_X,0.15*SCENE_SIZE_X, Color.BLACK, Color.GREENYELLOW);
		myUnitDisplay = new UnitDisplay(0.15*SCENE_SIZE_X, 0.85*SCENE_SIZE_Y, 0.85*SCENE_SIZE_X, 0.85*SCENE_SIZE_Y, myUnitSkills, mySkillImages);
		myRoot.getChildren().add(myTopPanel.getNodes());
		myRoot.getChildren().add(myMiniMap.getNodes());
		myRoot.getChildren().add(myUnitDisplay.getNodes());
	}

	public Scene setScene(Stage gpStage, int initXSize, int initYSize) {
		Scene scene = new Scene(new Group(), initXSize, initYSize);
		return scene;
	}
	
}
