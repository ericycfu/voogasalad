package game_player.visual_element;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * the interface that all top panel UI elements implement
 * 
 */
public class TopPanel implements VisualUpdate {
	
	public static final String MENU = "Menu";
	public static final String START = "Start";
	public static final String PAUSE = "Pause";
	public static final String SAVE = "Save";
	public static final String LOAD = "Load";
	public static final String TIME = "Time";
	public static final String SCORE = "Scores";
	protected static final String[] SCORES = {"Player1: ", "Player2: "};
	public static final String COLON = ": ";
	
	private GridPane gp;
	private MenuButton menu;
	private List<TextArea> myTA;
	private TextArea time;
	private ComboBox<String> scores;
	private TextArea r1;
	private TextArea r2;
	private String r1Name;
	private String r2Name;
	
	private int menuSpan;
	
	public TopPanel() {
		gp = new GridPane();
		menuSpan = 0;
		
		setupMenu();
		setupScores();
		time = new TextArea(TIME + COLON + 0);
		r1 = new TextArea(r1Name + COLON + 0);
		r2 = new TextArea(r2Name + COLON + 0);
		TextArea[] tas = {time, r1, r2};
		myTA = Arrays.asList(tas);
		myTA.forEach(ta -> {
			ta.setEditable(false);
			ta.setPrefColumnCount(1);
			ta.setMaxHeight(10);
			ta.setPrefWidth(160);
			addToPane(ta);
		});
	}

	private void setupMenu() {
		MenuItem menuItem1 = new MenuItem(START);
		menuItem1.setOnAction(e -> {});
		MenuItem menuItem2 = new MenuItem(PAUSE);
		menuItem1.setOnAction(e -> {});
		MenuItem menuItem3 = new MenuItem(SAVE);
		menuItem1.setOnAction(e -> save());
		MenuItem menuItem4 = new MenuItem(LOAD);
		menuItem1.setOnAction(e -> load());
		menu = new MenuButton(MENU, null, menuItem1, menuItem2, menuItem3, menuItem4);
		menu.setMinHeight(40);
		addToPane(menu);
	}

	private void setupScores() {
		scores = new ComboBox<>();
		scores.setPromptText(SCORE);
		scores.getItems().addAll(SCORES);
		scores.setMinHeight(40);
		addToPane(scores);
	}
	
	private void addToPane(Node n) {
		gp.add(n, menuSpan, 0);
		menuSpan++;
	}
	
	private void save() {
		
	}
	
	private void load() {
		
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
		
	}

	@Override
	public void update(List<GameObject> gameObjects) {
		
	}
	
	public Node getNodes() {
		return gp;
	}
}
