package game_player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.Renderer;
import interactions.Interaction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import transform_library.Vector2;

public class TestInteractionMain extends Application {

    private final int FRAMES_PER_SECOND = 5;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final String TITLE = "Gameplayer";
	private GamePlayer myGP;
	private GameObjectManager myGOM;
	GameObject go;
	GameObject go2;
	
	@Override
	public void start(Stage gpStage) throws Exception {	
		// Test
		Map<String, List<String>> unitSkills = new HashMap<>();
		Map<String, Image> skillImages = new HashMap<>();	
		GameObjectManager gom = new GameObjectManager();
		myGOM = gom;
		
		go = new GameObject(new Vector2(100,100));
		go.setTags(Arrays.asList("professor"));
		go.accessLogic().accessAttributes().createAttribute("Health");
		go.accessLogic().accessAttributes().createAttribute("Code Commits");
		go.accessLogic().accessAttributes().createAttribute("Attack");
		go.accessLogic().accessAttributes().createAttribute("Design Checklist Score");
		go.accessLogic().accessAttributes().setAttributeValue("Health", 500);
		go.accessLogic().accessAttributes().setAttributeValue("Code Commits", 9001);
		go.accessLogic().accessAttributes().setAttributeValue("Attack", 0);
		go.accessLogic().accessAttributes().setAttributeValue("Design Checklist Score", 0);
		go.setMovementSpeed(5);
		Renderer renderer = new Renderer(new Image("robert.png"));
		go.setRenderer(renderer);
		
		go2 = new GameObject(new Vector2(50,100));
		go2.accessLogic().accessAttributes().createAttribute("Health");
		go2.accessLogic().accessAttributes().createAttribute("Mana");
		go2.accessLogic().accessAttributes().createAttribute("Attack");
		go2.accessLogic().accessAttributes().createAttribute("Armor");
		go2.accessLogic().accessAttributes().setAttributeValue("Health", 100);
		go2.accessLogic().accessAttributes().setAttributeValue("Mana", 100);
		go2.accessLogic().accessAttributes().setAttributeValue("Attack", 1);
		go2.accessLogic().accessAttributes().setAttributeValue("Armor", 5);
		Interaction attack = new Interaction();
		attack.addToManager(go2.accessLogic().accessInteractions());
		attack.addTag("professor");
		attack.addCustomFunction("ModifyVariable");
		attack.getCustomFunction(0).getParameterFormat().setFieldValue("Variable", "Health");
		attack.getCustomFunction(0).getParameterFormat().setFieldValue("Delta", "-1");
		attack.getCustomFunction(0).setParameters(attack.getCustomFunction(0).getParameterFormat());
		attack.setRange(100);
		
		
		go2.setMovementSpeed(30);
		Renderer renderer2 = new Renderer(new Image("ghoul.png"));
		go2.setRenderer(renderer2);
		
		gom.addElementToManager(go);
		gom.addElementToManager(go2);
		
		go2.queueInteraction(go);

        //myGP.update(myGOM.getElements());

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        
		GamePlayer gp = new GamePlayer(animation, gom, unitSkills, skillImages, new HashMap<String, Image>(), new HashMap<String, Image>());		
        myGP = gp;
		Scene scene = gp.getScene();  
        gpStage.setScene(scene);
        gpStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeElapsed) {
		myGP.update(myGOM.getElements());
		myGOM.runGameObjectLoop();
		go2.queueInteraction(go);
	}
}
