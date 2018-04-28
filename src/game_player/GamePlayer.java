package game_player;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game_engine.EndStateWrapper;
import game_engine.EndStateWrapper.EndState;
import game_engine.EngineObject;
import game_engine.GameInstance;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import game_player.alert.AlertMaker;
import game_player.visual_element.ChatBox;
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
import javafx.scene.ImageCursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import scenemanager.NullEndConditionException;
import scenemanager.SceneManager;
import transform_library.Vector2;

/**
 * 
 * @author Siyuan Chen
 *
 */
public class GamePlayer {
	
	public static final int SCENE_SIZE_X = 1200;
	public static final int SCENE_SIZE_Y = 800;
	public static final double BOTTOM_HEIGHT = 0.25;
	public static final double MINIMAP_WIDTH = 0.25;
	public static final double INFO_DISPLAY_WIDTH = 0.49;
	public static final double ACTION_DISPLAY_WIDTH = 0.25;
	public static final double TOP_HEIGHT = 0.05;
	public static final double CHATBOX_WIDTH = 0.20;
	public static final double CHATBOX_HEIGHT = 0.30;
	private GameObjectManager myGameObjectManager;
	private TopPanel myTopPanel;
	private MiniMap myMiniMap;
	private UnitDisplay myUnitDisplay;
	private MainDisplay myMainDisplay;
	private ChatBox myChatBox;
	private Group myRoot;
	private Map<String, List<SkillButton>> myUnitSkills;
	private Map<String, List<SkillButton>> myUnitBuilds;
	private SelectedUnitManager mySelectedUnitManager;
	private Scene myScene;
	private Team myTeam;
	
	private Set<GameObject> myPossibleUnits;
	private SceneManager mySceneManager;
	
	public GamePlayer(Timeline timeline, GameObjectManager gameManager, Team team, Set<GameObject> allPossibleUnits) { 
		// public GamePlayer(GameObjectManager gom, Set<GameOjbect> allPossibleUnits) {
		//Timeline: pause requests to server
		
		myPossibleUnits = allPossibleUnits;
		myGameObjectManager = gameManager;
		myTeam = team;
		myUnitSkills = new HashMap<>();
		mySelectedUnitManager = new SelectedUnitManager(myTeam);		
		initialize();
		initializeSingleUnitSelect();
		myTopPanel.setTimeline(timeline);
		unitSkillMapInitialize();
	}
	
	// network constructor
	public GamePlayer(GameObjectManager gom, Set<GameObject> allPossibleUnits, Socket socket, Team team, SceneManager scenemanager) {
		myPossibleUnits = allPossibleUnits;
		mySceneManager = scenemanager;
	}
	
	private void unitBuildsMapInitialize() {
		myUnitBuilds = new HashMap<>();
		for (GameObject go : myPossibleUnits) {
			List<SkillButton> skillList = new ArrayList<>();
			try {
				for (Interaction i : go.accessLogic().accessInteractions().getElements()) {
					if (i.isBuild()) {
						List<String> tags = i.getTargetTags();
						for (String s : tags) {
							for (GameObject go2 : myPossibleUnits) {
								if (s.equals(go2.getName())) {
									SkillButton sb = new SkillButton(go2.getRenderer().getDisp().getImage(), s, i.getID(), i.getDescription() + " " + s, 
											SCENE_SIZE_Y*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH, 
											SCENE_SIZE_X*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT);
									sb.setOnAction(e -> {
										myUnitDisplay.getUnitActionDisp().setCurrentActionID(i.getID());
										myUnitDisplay.getUnitActionDisp().setBuildTarget(go2);
									});
									skillList.add(sb);
								}
							}
						}
						myUnitBuilds.put(go.getName(), skillList);
					}
				}
			} catch (UnmodifiableGameObjectException e) {
				// do nothing
			}
		}
	}
	
	private void unitSkillMapInitialize() {
		//unitBuildsMapInitialize();
		
		for (GameObject go : myPossibleUnits) {
			List<SkillButton> skillList = new ArrayList<>();
			SkillButton cancel = new SkillButton(new Image("cancel_icon.png"), "Cancel", -1, "Restore the interaction to default", SCENE_SIZE_X*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH*0.8, SCENE_SIZE_Y*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT*0.8);
			try {
				for (Interaction ia : go.accessLogic().accessInteractions().getElements()) {
					SkillButton sb = new SkillButton(ia.getImg(), ia.getName(), ia.getID(), ia.getDescription(), SCENE_SIZE_X*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH*0.8, 0.8*SCENE_SIZE_Y*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT);
					System.out.println(SCENE_SIZE_X*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH);
					System.out.println(SCENE_SIZE_Y*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT);
					System.out.println(ia.getID());
					System.out.println(sb.getInteractionID());
					List<GameObject> temp = new ArrayList<>();
					temp.add(go);
					cancel.setOnAction(e -> {
						this.myUnitDisplay.getUnitActionDisp().update(temp);
						this.myUnitDisplay.getUnitActionDisp().setCurrentActionID(-1);
						System.out.println(this.myUnitDisplay.getUnitActionDisp().getCurrentActionID());
					});
					if (!ia.isBuild()) {
						sb.setOnAction(e->{
							myUnitDisplay.getUnitActionDisp().setCurrentActionID(sb.getInteractionID());
						});
						skillList.add(sb);
					}
					else {
						sb.setOnAction(e -> {
							List<SkillButton> sblist = new ArrayList<>(myUnitBuilds.get(go.getName()));
							sblist.add(cancel);
							myUnitDisplay.getUnitActionDisp().build(sblist);
						});
					}
				}
				skillList.add(cancel);
			} catch (UnmodifiableGameObjectException e) {
				// do nothing
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
					if (myUnitDisplay.getUnitActionDisp().getCurrentActionID() == -1) {
						mySelectedUnitManager.clear();
						mySelectedUnitManager.add(go);
					}
					else {
						int ID = myUnitDisplay.getUnitActionDisp().getCurrentActionID();
						try {
							if (!mySelectedUnitManager.getSelectedUnits().isEmpty() && !mySelectedUnitManager.getSelectedUnits().get(0).accessLogic().accessInteractions().getInteraction(ID).isBuild()) {
								mySelectedUnitManager.takeInteraction(go.getTransform().getPosition(), go, ID, myGameObjectManager);
								myUnitDisplay.getUnitActionDisp().setCurrentActionID(-1);
							}
						} catch (UnmodifiableGameObjectException e1) {
							// do nothing
						}
					}
				}
			});
		}
	}
	
	private void initialize() {
		myRoot = new Group();
		
		myTopPanel = new TopPanel(myTeam, myGameObjectManager, myPossibleUnits, SCENE_SIZE_X, TOP_HEIGHT*SCENE_SIZE_Y);
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
		mainDisp.toBack();
		
		myChatBox = new ChatBox(SCENE_SIZE_X * CHATBOX_WIDTH, SCENE_SIZE_Y * CHATBOX_HEIGHT);
		Node chatBox = myChatBox.getGroup();
		chatBox.setLayoutX(SCENE_SIZE_X * (1 - CHATBOX_WIDTH));
		chatBox.setLayoutY(SCENE_SIZE_Y * (1 - BOTTOM_HEIGHT - CHATBOX_HEIGHT));
		myRoot.getChildren().add(chatBox);
	
		myScene = new Scene(myRoot, SCENE_SIZE_X, SCENE_SIZE_Y);
	}

	public Scene getScene() {
		return myScene;
	}
	
	public void update(List<GameObject> gameobject) {
		//myTopPanel.update();
		myMiniMap.update(gameobject);
		myUnitDisplay.update(mySelectedUnitManager.getSelectedUnits());
		myMainDisplay.update(gameobject);
		if (myUnitDisplay.getUnitActionDisp().getCurrentActionID()!=-1) {
			try {
				Image img = mySelectedUnitManager.getSelectedUnits().get(0).accessLogic().accessInteractions().getInteraction(this.myUnitDisplay.getUnitActionDisp().getCurrentActionID()).getImg();
				ImageCursor cursor = new ImageCursor(img);
				
				myScene.setCursor(cursor);
			} catch (UnmodifiableGameObjectException e) {

			}
			
		}
		else {
			myScene.setCursor(Cursor.DEFAULT);
		}
		
	}
	
	// TO-DO: set select when a new unit is created
	
	private void end(String result) {
		myRoot.getChildren().clear();
		Text text = new Text(result);
		text.setLayoutX(SCENE_SIZE_X/2-100);
		text.setLayoutY(SCENE_SIZE_Y/2-100);
		myRoot.getChildren().add(text);
	}
	
	
}
