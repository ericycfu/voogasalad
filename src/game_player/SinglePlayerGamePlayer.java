package game_player;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import authoring.backend.MapSettings;
import game_engine.EndStateWrapper;
import game_engine.EndStateWrapper.EndState;
import game_engine.EngineObject;
import game_engine.GameInstance;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.UnmodifiableGameObjectException;
import game_player.selected_unit_manager.SelectedUnitManager;
import game_player.selected_unit_manager.SinglePlayerSelectedUnitManager;
import game_player.visual_element.BuildButton;
import game_player.visual_element.ChatBox;
import game_player.visual_element.MainDisplay;
import game_player.visual_element.MiniMap;
import game_player.visual_element.SingleTopPanel;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pathfinding.GridMap;
import scenemanager.SceneManager;

/**
 * 
 * @author Siyuan Chen, Frank Yin
 *
 * This class initializes and controls all of the sub-components necessary for game-playing visualization and UIs (MainDisplay, UnitDisplay, 
 * 
 */
public class SinglePlayerGamePlayer {

	public static final double WINDOW_STEP_SIZE = 10;
	public static final double MAP_DISPLAY_RATIO = 4;
	public static final int SCENE_SIZE_X = 900;
	public static final int SCENE_SIZE_Y = 600;
	public static final double BOTTOM_HEIGHT = 0.25;
	public static final double MINIMAP_WIDTH = 0.25;
	public static final double INFO_DISPLAY_WIDTH = 0.49;
	public static final double ACTION_DISPLAY_WIDTH = 0.25;
	public static final double TOP_HEIGHT = 0.05;
	public static final double CHATBOX_WIDTH = 0.20;
	public static final double CHATBOX_HEIGHT = 0.30;
	public static final String LINEBREAK = "\n";
	public static final String EMPTY = "";
	public static final String COLON = ": ";
	public static final String SPACE = " ";
	public static final String SERVERALERTHEAD = "Communication Failed";
	public static final String SERVERALERTBODY = "Please try again.";
	public static final int UNIT_HEIGHT = 50;
	public static final int UNIT_WIDTH = 50;
	public static final int BUILDING_HEIGHT = 100;
	public static final int BUILDING_WIDTH = 100;
		
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
	private MapSettings myMapSettings;
	private ImageView myMap;
	private Socket mySocket;
	private Set<GameObject> myPossibleUnits;
	private SceneManager mySceneManager;
	private Stage myStage;

	public SinglePlayerGamePlayer(GameObjectManager gameManager,Set<GameObject> allPossibleUnits) {
		myMap = new ImageView(new Image("map4.jpg"));
		myMap.setFitWidth(SCENE_SIZE_X*MAP_DISPLAY_RATIO);
		myMap.setFitHeight((1-TOP_HEIGHT-BOTTOM_HEIGHT)*SCENE_SIZE_Y*MAP_DISPLAY_RATIO);
		myPossibleUnits = allPossibleUnits;
		myGameObjectManager = gameManager;
		myUnitSkills = new HashMap<>();
		myTeam = new Team(1, null);
		mySelectedUnitManager = new SinglePlayerSelectedUnitManager(myTeam);
		initialize();
		initializeSingleUnitSelect();
		unitSkillMapInitialize();
	}
		
	private void unitBuildsMapInitialize() {
		myUnitBuilds = new HashMap<>();
		for (GameObject go : myPossibleUnits) {
			List<SkillButton> skillList = new ArrayList<>();
			try {
				go.accessLogic().accessInteractions().getElements().stream()
				.filter(i -> i.isBuild())
				.forEach(i -> {
					i.getTargetTags().stream()
					.forEach(tag -> {
						myPossibleUnits.stream()
						.filter(o -> o.getTags().stream().anyMatch(s -> s.equals(tag)))
						.forEach(o -> {
							BuildButton bb = new BuildButton(new Image(o.getRenderer().getImagePath()), i.getDescription() + SPACE + tag, i.getID(), 
									SCENE_SIZE_X*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH*UnitActionDisplay.JAVAFX_IMAGEVIEW_SHRINK_RATIO, 
									SCENE_SIZE_Y*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT*UnitActionDisplay.JAVAFX_IMAGEVIEW_SHRINK_RATIO, o);
							bb.setOnAction(e -> {
								myUnitDisplay.getUnitActionDisp().setCurrentActionID(i.getID());
								myUnitDisplay.getUnitActionDisp().setBuildTarget(o);
							});
							skillList.add(bb);
						});
					});
					myUnitBuilds.put(go.getName(), skillList);
				});
			} catch (UnmodifiableGameObjectException e) {
				// do nothing
			}
		}
	}

	private void unitSkillMapInitialize() {
		unitBuildsMapInitialize();
		myUnitSkills.clear();
		for (GameObject go : myPossibleUnits) {
			List<SkillButton> skillList = new ArrayList<>();
			try {
				for (Interaction ia : go.accessLogic().accessInteractions().getElements()) {
					skillList.add(makeInteractionButton(ia, go));
				}
			} catch (UnmodifiableGameObjectException e) {
				// do nothing
			}
			skillList.add(makeCancelButton(go.getName()));
			myUnitSkills.put(go.getName(), skillList);
		}
	}

	private SkillButton makeCancelButton(String name) {
		SkillButton cancel = new SkillButton(new Image(SkillButton.CANCEL_BUTTON_IMAGE_PATH), SkillButton.CANCEL_BUTTON_NAME, -1, SkillButton.CANCEL_BUTTON_DESCRIPTION, SCENE_SIZE_X*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH*0.8, SCENE_SIZE_Y*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT*0.8);
		cancel.setOnAction(e -> {
			this.myUnitDisplay.getUnitActionDisp().fill(myUnitSkills.get(name));
			this.myUnitDisplay.getUnitActionDisp().defaultCurrentActionID();
		});
		return cancel;
	}

	private SkillButton makeInteractionButton(Interaction ia, GameObject go) {
		SkillButton sb = new SkillButton(new Image(ia.getImagePath()), ia.getName(), ia.getID(), ia.getDescription(), SCENE_SIZE_X*ACTION_DISPLAY_WIDTH/UnitActionDisplay.ACTION_GRID_WIDTH*UnitActionDisplay.JAVAFX_IMAGEVIEW_SHRINK_RATIO, SCENE_SIZE_Y*BOTTOM_HEIGHT/UnitActionDisplay.ACTION_GRID_HEIGHT*UnitActionDisplay.JAVAFX_IMAGEVIEW_SHRINK_RATIO);
		if (!ia.isBuild()) {
			sb.setOnAction(e->{
				myUnitDisplay.getUnitActionDisp().setCurrentActionID(sb.getInteractionID());
			});
		}
		else {
			sb.setOnAction(e -> {
				List<SkillButton> sblist = new ArrayList<>(myUnitBuilds.get(go.getName()));
				sblist.add(makeCancelButton(go.getName()));
				myUnitDisplay.getUnitActionDisp().fill(sblist);
			});
		}
		return sb;
	}

	private void initializeSingleUnitSelect() {
		for (GameObject go : myGameObjectManager.getElements()) {
			go.getRenderer().getDisp().toFront();
			go.getRenderer().getDisp().setOnMouseClicked(e-> {
				if (e.getButton()==MouseButton.PRIMARY) {
					mySelectedUnitManager.clear();
					mySelectedUnitManager.add(go);
					myUnitDisplay.getUnitActionDisp().defaultCurrentActionID();
				}
				if (e.getButton()==MouseButton.SECONDARY) {
					int ID = myUnitDisplay.getUnitActionDisp().getCurrentActionID();
					try {
						if (ID==-1) {
							mySelectedUnitManager.move(go.getTransform().getPosition(), myGameObjectManager, new GridMap(myMap.getFitWidth(),myMap.getFitHeight()));
						}
						else if (!mySelectedUnitManager.getSelectedUnits().isEmpty() && !mySelectedUnitManager.getSelectedUnits().get(0).accessLogic().accessInteractions().getInteraction(ID).isBuild()) {
							mySelectedUnitManager.takeInteraction(null, go, ID, myGameObjectManager, new GridMap(myMap.getFitWidth(), myMap.getFitHeight()));
							myUnitDisplay.getUnitActionDisp().defaultCurrentActionID();
						}
					} catch (UnmodifiableGameObjectException e1) {
						// do nothing
					}
				}	
			});
		}
	}

	private void initialize() {
		myRoot = new Group();
		myTopPanel = new SingleTopPanel(1, myGameObjectManager, myPossibleUnits, SCENE_SIZE_X, TOP_HEIGHT*SCENE_SIZE_Y);

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

		myMainDisplay = new MainDisplay(mySelectedUnitManager, myGameObjectManager, myUnitDisplay.getUnitActionDisp(), SCENE_SIZE_X, (1-TOP_HEIGHT-BOTTOM_HEIGHT)*SCENE_SIZE_Y, myMap);
		Node mainDisp = myMainDisplay.getNodes();
		mainDisp.setLayoutY(TOP_HEIGHT*SCENE_SIZE_Y);
		myRoot.getChildren().add(mainDisp);
		mainDisp.toBack();

		myChatBox = new ChatBox(mySocket, SCENE_SIZE_X * CHATBOX_WIDTH, SCENE_SIZE_Y * CHATBOX_HEIGHT);
		Node chatBox = myChatBox.getNodes();
		chatBox.setLayoutX(SCENE_SIZE_X * (1 - CHATBOX_WIDTH));
		chatBox.setLayoutY(SCENE_SIZE_Y * (1 - BOTTOM_HEIGHT - CHATBOX_HEIGHT));
		myRoot.getChildren().add(chatBox);

		myScene = new Scene(myRoot, SCENE_SIZE_X, SCENE_SIZE_Y);
		//myStage.setScene(myScene);
	}

	public Scene getScene() {
		return myScene;
	}

	public void update() {
		List<GameObject> gameobject = myGameObjectManager.getElements();
		if (myTopPanel.getIsLoaded()) {
			unitSkillMapInitialize();
			myUnitDisplay.getUnitActionDisp().setUnitSkills(myUnitSkills);
			myTopPanel.setIsLoaded(false);
		}
		initializeSingleUnitSelect();
		myTopPanel.update();
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

	private void receiveFromServer() {
		/**
			ObjectInputStream inputstream = getInputStream();
			try {
				myGameObjectManager = (GameObjectManager) inputstream.readObject();
				myTeam = (Team) inputstream.readObject();
				myTime.setValue(inputstream.readDouble());
				myChatBox.displayText(inputstream.readObject().toString());
			} catch (ClassNotFoundException | IOException e) {
				// do nothing
			}
			//gom team time chat 
		 **/
	}

	private void end(String result) {
		myRoot.getChildren().clear();
		Text text = new Text(result);
		text.setLayoutX(SCENE_SIZE_X/2-100);
		text.setLayoutY(SCENE_SIZE_Y/2-100);
		myRoot.getChildren().add(text);
	}

	public static ObjectOutputStream getObjectOutputStream(Socket socket) {
		try {
			return new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {
			return null;
		}
	}

	public void setTimeline(Timeline animation) {
		((SingleTopPanel) myTopPanel).setTimeline(animation);
	}
	
	public void reset() {
		myGameObjectManager.clearManager();
		myTopPanel.resetTime();
	}
	
}
