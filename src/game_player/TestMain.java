package game_player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import game_data.Reader;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.Renderer;
import interactions.CustomFunction;
import interactions.Interaction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import transform_library.Vector2;

public class TestMain extends Application {

    private final int FRAMES_PER_SECOND = 60;
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
		go.accessLogic().accessAttributes().createAttribute("Health");
		go.accessLogic().accessAttributes().createAttribute("Mana");
		go.accessLogic().accessAttributes().createAttribute("Attack");
		go.accessLogic().accessAttributes().createAttribute("Armor");
		go.accessLogic().accessAttributes().setAttributeValue("Health", 200);
		go.accessLogic().accessAttributes().setAttributeValue("Mana", 200);
		go.accessLogic().accessAttributes().setAttributeValue("Attack", -50);
		go.accessLogic().accessAttributes().setAttributeValue("Armor", 10);
		go.setMovementSpeed(1);
		Renderer renderer = new Renderer("robert.png");
		go.setRenderer(renderer);
		

		
		gom.addElement(go);
		int i = gom.createGameObject(new Vector2(50, 100), null, "ghoul", null);
		GameObject go2 = gom.getGameObject(i);
		go2.accessLogic().accessAttributes().createAttribute("Health");
		go2.accessLogic().accessAttributes().createAttribute("Mana");
		go2.accessLogic().accessAttributes().createAttribute("Attack");
		go2.accessLogic().accessAttributes().createAttribute("Armor");
		go2.accessLogic().accessAttributes().setAttributeValue("Health", 100);
		go2.accessLogic().accessAttributes().setAttributeValue("Mana", 100);
		go2.accessLogic().accessAttributes().setAttributeValue("Attack", 10);
		go2.accessLogic().accessAttributes().setAttributeValue("Armor", 5);
		go2.setMovementSpeed(4);
		int j = go2.accessLogic().accessInteractions().createInteraction();
		Interaction test = go2.accessLogic().accessInteractions().getInteraction(j);
		test.setDescription("attack: damage = 5");
		test.setImg(new Image("defend_icon.png"));
		test.setName("attack");
		test.setRange(50);
		CustomFunction cf = test.generateCustomFunction("ModifyVariable");
		test.addCustomFunction(cf);
		test.getCustomFunction(0).getParameterFormat().setFieldValue("Variable", "Health");
		test.getCustomFunction(0).getParameterFormat().setFieldValue("Delta", "-1");
		test.getCustomFunction(0).setParameters(test.getCustomFunction(0).getParameterFormat());
		Renderer renderer2 = new Renderer("ghoul.png");
		go2.setRenderer(renderer2);
		Set<GameObject> possibleunits = new HashSet<>();
		possibleunits.add(go);
		possibleunits.add(go2);

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        
		GamePlayer gp = new GamePlayer(animation, gom, null, possibleunits);
        myGP = gp;
		Scene scene = gp.getScene();  
        gpStage.setScene(scene);
        gpStage.show();
        //myGP.update(myGOM.getElements());

	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeElapsed) {
		myGP.update(myGOM.getElements());
		myGOM.runGameObjectLoop(SECOND_DELAY);
	}
}
