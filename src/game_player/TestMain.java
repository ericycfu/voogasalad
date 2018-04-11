package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Reader;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.Renderer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import transform_library.Vector2;

public class TestMain extends Application {

    private final int FRAMES_PER_SECOND = 5;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String TITLE = "Gameplayer";
	private GamePlayer myGP;
	private GameObjectManager myGOM;
	
	@Override
	public void start(Stage gpStage) throws Exception {	
		// Test
		Map<String, List<String>> unitSkills = new HashMap<>();
		Map<String, Image> skillImages = new HashMap<>();	
		GameObjectManager gom = new GameObjectManager();
		myGOM = gom;
		
		GameObject go = new GameObject(new Vector2(100,100));
		go.setMovementSpeed(10);
		Renderer renderer = new Renderer(new Image("ghoul.png"));
		go.setRenderer(renderer);
		
		GameObject go2 = new GameObject(new Vector2(50,100));
		go2.setMovementSpeed(10);
		Renderer renderer2 = new Renderer(new Image("ghoul.png"));
		go2.setRenderer(renderer2);
		
		
		
		gom.addElementToManager(go);
		gom.addElementToManager(go2);
		GamePlayer gp = new GamePlayer(gom, unitSkills, skillImages, new HashMap<String, Image>(), new HashMap<String, Image>());		
        myGP = gp;
		Scene scene = gp.getScene();  
        gpStage.setScene(scene);
        gpStage.show();
        //myGP.update(myGOM.getElements());

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
	
	private void step(double timeElapsed) {
		myGP.update(myGOM.getElements());
		myGOM.runGameObjectLoop();
	}
}
