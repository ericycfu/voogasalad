package game_player;

import game_player.visual_element.MiniMap;
import game_player.visual_element.TopPanel;
import game_player.visual_element.UnitDisplay;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Siyuan Chen
 *
 */
public class GamePlayer {
	
	TopPanel myTopPanel;
	MiniMap myMiniMap;
	UnitDisplay myUnitDisplay;
	
	public GamePlayer() {
		
	}
	
	private void initialize() {
		
	}

	public Scene setScene(Stage gpStage, int initXSize, int initYSize) {
		Scene scene = new Scene(new Group(), initXSize, initYSize);
		return scene;
	}
	
}
