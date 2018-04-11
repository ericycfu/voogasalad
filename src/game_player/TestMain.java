package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestMain extends Application {

    private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String TITLE = "Gameplayer";
	private GamePlayer myGP;
	
	@Override
	public void start(Stage gpStage) throws Exception {	
		// Test
		ArrayList<GameObject> go = new ArrayList<>();
		Map<String, List<String>> unitSkills = new HashMap<>();
		Map<String, Image> skillImages = new HashMap<>();	
		GamePlayer gp = new GamePlayer(go, unitSkills, skillImages, new HashMap<String, Image>(), new HashMap<String, Image>());		
        myGP = gp;
		Scene scene = gp.getScene();  
        gpStage.setScene(scene);
        gpStage.show();
        
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeelapsed) {
		myGP.update(new ArrayList<GameObject>());
	}
}
