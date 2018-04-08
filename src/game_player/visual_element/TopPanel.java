package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 * the interface that all top panel UI elements implement
 * 
 */
public class TopPanel implements VisualUpdate {
	
	public static final String MENU = "Menu";
	public static final String SCORE = "Score";
	protected static final String[] MENUITEMS = {"Start", "Pause", "Load", "Save"};
	
	private GridPane gp;
	private ComboBox<String> menu;
	private TextArea time;
	private TextArea score;
	private TextArea r1;
	private TextArea r2;
	private String r1Name;
	private String r2Name;
	
	private int menuSpan;
	private double timeValue;
	private int scoreValue;
	private int r1Amount;
	private int r2Amount;
	
	public TopPanel() {
		gp = new GridPane();
		menuSpan = 0;
		timeValue = 0;
		scoreValue = 0;
		r1Amount = 0;
		r2Amount = 0;
		
		setupMenu();
		setupTime();
		setupScore();
		setupResources();
	}

	private void setupMenu() {
		menu = new ComboBox<>();
		menu.setPromptText(MENU);
		menu.getItems().addAll(MENUITEMS);
		addToPane(menu);
	}

	private void setupTime() {
		time = new TextArea();
		time.setText(timeValue + "");
		time.setEditable(false);
	}
	
	private void setupScore() {
		score = new TextArea();
		score.setText(SCORE + ": " + scoreValue);
		score.setEditable(false);
	}
	
	private void setupResources() {
		setupResource(r1, r1Name, r1Amount);
		setupResource(r2, r2Name, r2Amount);
	}
	
	private void setupResource(TextArea ta, String type, int amount) {
		ta = new TextArea();
		ta.setEditable(false);
		ta.setText(type + ": " + amount);
		addToPane(ta);
	}

	private void addToPane(Node n) {
		gp.add(n, menuSpan, 0);
		menuSpan++;
	}

	/**
	 * allow the game player to set the resources amounts displayed in the top panel
	 * @param amount1 amount for first resource
	 * @param amount2 amount for second resource
	 */
	public void setResourcesAmount(int amount1, int amount2) {
		r1Amount = amount1;
		r2Amount = amount2;
		r1.setText(r1Name + ": " + r1Amount);
		r2.setText(r2Name + ": " + r2Amount);
	}
	
	/**
	 * allow the game player to set the time displayed in the top panel
	 * @param time current time
	 */
	public void setTime(double time) {
		timeValue = time;
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
