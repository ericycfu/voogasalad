package game_player.visual_element;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import game_data.Reader;
import game_data.Writer;
import game_object.GameObject;
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
public class TopPanel implements VisualUpdate {
	
	public static final String MENU = "Menu";
	public static final String START = "Start";
	public static final String PAUSE = "Pause";
	public static final String SAVE = "Save";
	public static final String LOAD = "Load";
	public static final String TIME = "Time";
	public static final String SCORE = "Scores";
	public static final String[] SCORES = {"Player1: "};
	public static final String COLON = ": ";
	public static final String FILEPATH = "data/";
	public static final String SAVETEXT = "Save Game";
	public static final String LOADTEXT = "Load Game";
	public static final double MENUWIDTH = 0.125;
	public static final double SBWIDTH = 0.125;
	public static final double TAWIDTH = 0.25;
	
	private GridPane gp;
	private MenuButton menu;
	private List<TextArea> myTA;
	private TextArea time;
	private ComboBox<String> scoreboard;
	private TextArea r1;
	private TextArea r2;
	private String r1Name;
	private String r2Name;
	private List<GameObject> myGameObjects;
	private int menuSpan;
	private Timeline tl;
	private Reader myReader;
	private Writer myWriter;
	
	public TopPanel(double xsize, double ysize) {
		gp = new GridPane();
		menuSpan = 0;
		
		setupMenu(xsize, ysize);
		setupScores(xsize, ysize);
		r1Name = "Gold";
		r2Name = "Wood";
		time = new TextArea(TIME + COLON + 0);
		r1 = new TextArea(r1Name + COLON + 0);
		r2 = new TextArea(r2Name + COLON + 0);
		TextArea[] tas = {time, r1, r2};
		myTA = Arrays.asList(tas);
		myTA.forEach(ta -> {
			ta.setEditable(false);
			ta.setPrefWidth(xsize * TAWIDTH);
			ta.setMaxHeight(ysize);
			addToPane(ta);
		});
	}

	private void setupMenu(double xsize, double ysize) {
		MenuItem menuItem1 = new MenuItem(START);
		menuItem1.setOnAction(e -> {
			tl.play();
		});
		MenuItem menuItem2 = new MenuItem(PAUSE);
		menuItem2.setOnAction(e -> {
			tl.pause();
		});
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
	
	private void addToPane(Node n) {
		gp.add(n, menuSpan, 0);
		menuSpan++;
	}
	
	private void save() {
		FileChooser fc = new FileChooser();
		Stage stage = new Stage();
		fc.setInitialDirectory(new File(FILEPATH));
		fc.setTitle(SAVETEXT);
		File file = fc.showSaveDialog(stage);
		try {
			myWriter.write(file.getCanonicalPath(), myGameObjects);
		} catch (IOException e) {
			System.out.print("Error!");
		}
	}
	
	private void load() {
		FileChooser fc = new FileChooser();
		Stage stage = new Stage();
		fc.setInitialDirectory(new File(FILEPATH));
		fc.setTitle(LOADTEXT);
		File file = fc.showOpenDialog(stage);
		try {
			List<Object> gameObjects= myReader.read(file.getCanonicalPath());
			myGameObjects.clear();
			for(Object o: gameObjects) {
				myGameObjects.add((GameObject) o);
			}
		} catch (ClassNotFoundException e) {
			// TODO deal with this error
			
		} catch (IOException e) {
			// TODO deal with this error
		}
	}
	
	public void setTimeline(Timeline timeline) {
		tl = timeline;
	}
	
	/**
	 * allow the game player to set the resources amounts displayed in the top panel
	 * @param amount1 amount for first resource
	 * @param amount2 amount for second resource
	 */
	public void setResourcesAmount(int amount1, int amount2) {
		r1.setText(r1Name + COLON + amount1);
		r2.setText(r2Name + COLON + amount2);
	}
	
	/**
	 * allow the game player to set the time displayed in the top panel
	 * @param time current time
	 */
	public void setTime(double timeValue) {
		time.setText(TIME + COLON + timeValue);
	}
	
	/**
	 * allow the game player to set the scores displayed in the top panel
	 * @param scores current scores for each player
	 */
	public void setScores(Map<String, Integer> scores) {
		int counter = 0;
		for(String s: scores.keySet()) {
			scoreboard.getItems().set(counter, s + COLON + scores.get(s));
			counter++;
		}
	}

	@Override
	public void update(List<GameObject> gameObjects) {
		myGameObjects = gameObjects;
	}
	
	public Node getNodes() {
		return gp;
	}
}
