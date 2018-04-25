package game_player.visual_element;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import game_data.Reader;
import game_data.Writer;
import game_engine.ResourceManager;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * the interface that all top panel UI elements implement
 * @author Eddie
 */
public class TopPanel {
	
	public static final String MENU = "Menu";
	public static final String START = "Start";
	public static final String PAUSE = "Pause";
	public static final String SAVE = "Save";
	public static final String LOAD = "Load";
	public static final String TIME = "Time";
	public static final String SCORE = "Scores";
	public static final String[] SCORES = {"Player1: "};
	public static final String RESOURCE = "Resources";
	public static final String COLON = ": ";
	public static final String FILEPATH = "data/";
	public static final String SAVETEXT = "Save Game";
	public static final String LOADTEXT = "Load Game";
	public static final double MENUWIDTH = 0.125;
	public static final double SBWIDTH = 0.125;
	public static final double TAWIDTH = 0.25;
	public static final String DEFAULTBGSTYLE = "-fx-background-color: #FFFFFF);";
	
	private GridPane myPane;
	private MenuButton menu;
	private List<TextArea> myTA;
	private TextArea time;
	private ComboBox<String> scoreboard;
	private ComboBox<String> resourceBoard;
	private GameObjectManager myGameObjectManager;
	private Team myTeam;
	private int menuSpan;
	private Timeline tl;
	private Reader myReader;
	private Writer myWriter;
	private boolean isLoaded;
	
	public TopPanel(Team team, GameObjectManager gom, double xsize, double ysize) {
		myPane = new GridPane();
		myPane.setStyle(DEFAULTBGSTYLE);
		myTeam = team;
		myGameObjectManager = gom;
		myWriter = new Writer();
		myReader = new Reader();
		menuSpan = 0;
		
		setupMenu(xsize, ysize);
		setupScores(xsize, ysize);
		setupTime(xsize, ysize);
		setupResources(xsize, ysize);
	}

	private void setupMenu(double xsize, double ysize) {
		MenuItem menuItem1 = new MenuItem(START);
		menuItem1.setOnAction(e -> tl.play());
		MenuItem menuItem2 = new MenuItem(PAUSE);
		menuItem2.setOnAction(e -> tl.pause());
		MenuItem menuItem3 = new MenuItem(SAVE);
		menuItem3.setOnAction(e -> save());
		MenuItem menuItem4 = new MenuItem(LOAD);
		menuItem4.setOnAction(e -> load());
		menu = new MenuButton(MENU, null, menuItem1, menuItem2, menuItem3, menuItem4);
		menu.setPrefWidth(xsize * MENUWIDTH);
		menu.setMinHeight(ysize);
		addToPane(menu);
	}

	private void setupScores(double xsize, double ysize) {
		scoreboard = new ComboBox<>();
		scoreboard.setPromptText(SCORE);
		scoreboard.getItems().addAll(SCORES);
		scoreboard.setPrefWidth(xsize * SBWIDTH);
		scoreboard.setMinHeight(ysize);
		addToPane(scoreboard);
	}
	
	private void setupTime(double xsize, double ysize) {
		time = new TextArea(TIME + COLON + 0);
		time.setEditable(false);
		time.setPrefWidth(xsize * TAWIDTH);
		time.setMaxHeight(ysize);
		addToPane(time);
	}
	
	private void setupResources(double xsize, double ysize) {
		resourceBoard = new ComboBox<>();
		scoreboard.setPromptText(RESOURCE);
		scoreboard.setMaxHeight(ysize);
		addToPane(resourceBoard);
	}
	
	private void addToPane(Node n) {
		myPane.add(n, menuSpan, 0);
		menuSpan++;
	}
	
	private void save() {
		FileChooser fc = new FileChooser();
		Stage stage = new Stage();
		fc.setInitialDirectory(new File(FILEPATH));
		fc.setTitle(SAVETEXT);
		File file = fc.showSaveDialog(stage);
		List<GameObjectManager> ListRepresentation = new ArrayList<>();
		ListRepresentation.add(myGameObjectManager);
		try {
			myWriter.write(file.getCanonicalPath(), ListRepresentation);
		} catch (IOException e) {
			System.out.print("Error!"); // TODO: add alert
		}
	}
	
	private void load() {
		FileChooser fc = new FileChooser();
		Stage stage = new Stage();
		fc.setInitialDirectory(new File(FILEPATH));
		fc.setTitle(LOADTEXT);
		File file = fc.showOpenDialog(stage);
		try {
			List<Object> gameObjects = myReader.read(file.getCanonicalPath());
			myGameObjectManager.clearManager();
			myGameObjectManager = (GameObjectManager) gameObjects.get(0); // TODO: don't create new
		} catch (ClassNotFoundException e) {
			// TODO alert prompt
		} catch (IOException e) {
			// TODO deal with this error
		}
	}
	
	public void setTimeline(Timeline timeline) {
		tl = timeline;
	}
	
	private void setResources() {
		scoreboard.getItems().clear();
		List<Entry<String, Double>> entryList = myTeam.getResourceManager().getResourceEntries();
		String[] resources = new String[entryList.size()];
		for(int i = 0; i < entryList.size(); i++) {
			resources[i] = entryList.get(i).getKey() + COLON + entryList.get(i).getValue();
		}
		scoreboard.getItems().addAll(resources);
	}
	
	private void setTime(double timeValue) {
		time.setText(TIME + COLON + timeValue);
	}
	
	private void setScores(Map<String, Integer> scores) {
		int counter = 0;
		for(String s: scores.keySet()) {
			scoreboard.getItems().set(counter, s + COLON + scores.get(s));
			counter++;
		}
	}
	
	public boolean getIsLoaded() {
		boolean temp = isLoaded;
		isLoaded = false;
		return temp;
	}
	
	public void update() {
		setResources(); // TODO: set resource amount using gameobjectmanager
		setTime(0); // TODO: set time
		setScores(null); // TODO: set scores
	}
	
	public Node getNodes() {
		return myPane;
	}
}
