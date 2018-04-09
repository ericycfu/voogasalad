package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class TestMain extends Application {


	public static final String TITLE = "Gameplayer";
	public static final int INIT_X_SIZE = 600;
	public static final int INIT_Y_SIZE = 600;
	
	@Override
	public void start(Stage gpStage) throws Exception {	
		// Test
		ArrayList<GameObject> go = new ArrayList<>();
		Map<String, List<String>> unitSkills = new HashMap<>();
		Map<String, Image> skillImages = new HashMap<>();	
		GamePlayer gp = new GamePlayer(go, unitSkills, skillImages, new HashMap<String, Image>(), new HashMap<String, Image>());		
        Scene scene = gp.getScene();  
        gpStage.setScene(scene);
        gpStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
