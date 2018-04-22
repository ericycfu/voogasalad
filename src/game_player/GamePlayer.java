package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_engine.EngineObject;
import game_engine.GameInstance;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import game_player.visual_element.MainDisplay;
import game_player.visual_element.MiniMap;
import game_player.visual_element.SkillButton;
import game_player.visual_element.TopPanel;
import game_player.visual_element.UnitActionDisplay;
import game_player.visual_element.UnitDisplay;
import interactions.Interaction;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;

/**
 * 
 * @author Siyuan Chen
 *
 */
public class GamePlayer {
	
	public static final int SCENE_SIZE_X = 800;
	public static final int SCENE_SIZE_Y = 800;
	public static final double BOTTOM_HEIGHT = 0.25;
	public static final double MINIMAP_WIDTH = 0.25;
	public static final double INFO_DISPLAY_WIDTH = 0.50;
	public static final double ACTION_DISPLAY_WIDTH = 0.25;
	public static final double TOP_HEIGHT = 0.05;
	private GameObjectManager myGameObjectManager;
	private TopPanel myTopPanel;
	private MiniMap myMiniMap;
	private UnitDisplay myUnitDisplay;
	private MainDisplay myMainDisplay;
	private Group myRoot;
	private Map<String, List<SkillButton>> myUnitSkills;
	private SelectedUnitManager mySelectedUnitManager;
	private Scene myScene;
	
	public GamePlayer(Timeline timeline, GameObjectManager gameManager) {
		myGameObjectManager = gameManager;
		myUnitSkills = new HashMap<>();
		mySelectedUnitManager = new SelectedUnitManager();		
		initialize();
		initializeSingleUnitSelect();
		myTopPanel.setTimeline(timeline);
		//unitSkillMapInitialize();
	}
	
	private void unitSkillMapInitialize() {
		for (GameObject go : myGameObjectManager.getPossibleUnits()) {
			List<SkillButton> skillList = new ArrayList<>();
			try {
				for (Interaction ia : go.accessLogic().accessInteractions().getElements()) {
					SkillButton sb = new SkillButton(ia.getImage(), ia.getName(), ia.getID(), ia.getDescription(), SCENE_SIZE_Y*this.ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH, SCENE_SIZE_X*this.BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT);
					skillList.add(sb);
					sb.setOnAction(e->{
						myUnitDisplay.getUnitActionDisp().setCurrentActionID(sb.getInteractionID());
					});
				}
			} catch (UnmodifiableGameObjectException e) {
				// TODO Auto-generated catch block
			}
			myUnitSkills.put(go.getName(), skillList);
		}
	}
	
	private void initializeSingleUnitSelect() {
		for (GameObject eo : myGameObjectManager.getElements()) {
			GameObject go = (GameObject)eo;
			go.getRenderer().getDisp().toFront();
			go.getRenderer().getDisp().setOnMouseClicked(e-> {
				if (e.getButton()==MouseButton.PRIMARY) {
					if (myUnitDisplay.getUnitActionDisp().getCurrentActionID() != -1) {
						mySelectedUnitManager.clear();
						mySelectedUnitManager.add(go);
					}
					else {
						mySelectedUnitManager.takeInteraction(go.getTransform().getPosition(), go, myUnitDisplay.getUnitActionDisp().getCurrentActionID(), myGameObjectManager);
						myUnitDisplay.getUnitActionDisp().setCurrentActionID(-1);
					}
				}
			});
		}
	}
	
	private void initialize() {
		myRoot = new Group();
		
		myTopPanel = new TopPanel(SCENE_SIZE_X, TOP_HEIGHT*SCENE_SIZE_Y);
		myRoot.getChildren().add(myTopPanel.getNodes());
		
		myMiniMap = new MiniMap(MINIMAP_WIDTH*SCENE_SIZE_X, BOTTOM_HEIGHT*SCENE_SIZE_Y);
		Node minimap = myMiniMap.getNodes();
		minimap.setLayoutY((1-BOTTOM_HEIGHT)*SCENE_SIZE_Y);
		myRoot.getChildren().add(minimap);
		
		myUnitDisplay = new UnitDisplay(INFO_DISPLAY_WIDTH*SCENE_SIZE_X, BOTTOM_HEIGHT*SCENE_SIZE_Y, ACTION_DISPLAY_WIDTH*SCENE_SIZE_X, BOTTOM_HEIGHT*SCENE_SIZE_Y, myUnitSkills);
		Node unitDisp = myUnitDisplay.getNodes();
		unitDisp.setLayoutX(MINIMAP_WIDTH*SCENE_SIZE_X);
		unitDisp.setLayoutY((1-BOTTOM_HEIGHT)*SCENE_SIZE_Y);
		myRoot.getChildren().add(unitDisp);
		
		myMainDisplay = new MainDisplay(mySelectedUnitManager, myGameObjectManager, myUnitDisplay.getUnitActionDisp(), SCENE_SIZE_X, (1-TOP_HEIGHT-BOTTOM_HEIGHT)*SCENE_SIZE_Y);
		Node mainDisp = myMainDisplay.getNodes();
		mainDisp.setLayoutY(TOP_HEIGHT*SCENE_SIZE_Y);
		myRoot.getChildren().add(mainDisp);
		
		myScene = new Scene(myRoot, SCENE_SIZE_X, SCENE_SIZE_Y);
	}

	public Scene getScene() {
		return myScene;
	}
	
	public void update(List<GameObject> gameobject) {
		myTopPanel.update(gameobject); //resources
		myMiniMap.update(gameobject);
		myUnitDisplay.update(mySelectedUnitManager.getSelectedUnits());
		myMainDisplay.update(gameobject);
		if (myUnitDisplay.getUnitActionDisp().getCurrentActionID()!=-1) {
			myScene.setCursor(Cursor.CROSSHAIR);
		}
		else {
			myScene.setCursor(Cursor.DEFAULT);
		}
	}
	
	// TO-DO: set select when a new unit is created
	

	
}
