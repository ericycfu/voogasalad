package game_player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private SinglePlayerGamePlayer myGP;
	private GameObjectManager myGOM;
	
	@Override
	public void start(Stage gpStage) throws Exception {	
		// Test
		Map<String, List<String>> unitSkills = new HashMap<>();
		Map<String, Image> skillImages = new HashMap<>();	
		GameObjectManager gom = new GameObjectManager();
		myGOM = gom;
		
		/**
		GameObject go = new GameObject(new Vector2(100,100));
		go.accessLogic().accessAttributes().createAttribute("Health", 200);
		go.accessLogic().accessAttributes().createAttribute("Mana", 200);
		go.accessLogic().accessAttributes().createAttribute("Attack", -10);
		go.accessLogic().accessAttributes().createAttribute("Armor", 10);
		go.accessLogic().accessAttributes().setAttributeValue("Health", 100);

	
		go.setMovementSpeed(1);
		Renderer renderer = new Renderer("/images/robert.png");
		int k = go.accessLogic().accessInteractions().createInteraction();
		Interaction test = go.accessLogic().accessInteractions().getInteraction(k);
		test.setDescription("attack: damage = 5");
		test.setImg("defend_icon.png");
		test.setName("Attack");
		test.setRange(50);
		CustomFunction cf0 = test.generateCustomFunction("ModifyVariable");
		test.addCustomFunction(cf0);
		test.getCustomFunction(0).getParameterFormat().setFieldValue("Variable", "Health");
		test.getCustomFunction(0).getParameterFormat().setFieldValue("Delta", "Attack");
		test.getCustomFunction(0).setParameters(test.getCustomFunction(0).getParameterFormat());
		go.setRenderer(renderer);
		
		
		int n = go.accessLogic().accessInteractions().createInteraction();
		Interaction build = go.accessLogic().accessInteractions().getInteraction(n);
		build.setDescription("build");
		build.setImg("arrow_down.png");
		build.setName("Build");
		build.setRange(200);
		CustomFunction cf4 = build.generateCustomFunction("Build");
		build.isBuild(true);
		build.addCustomFunction(cf4);
		build.addTag("ghoul");
		build.addTag("empty");
		**/
		
		//gom.addElement(go);
		int i = gom.createGameObject(new Vector2(50, 100), null, "ghoul", null);
		GameObject go2 = gom.getGameObject(i);
		go2.accessLogic().accessAttributes().createAttribute("Health", 100);
		go2.accessLogic().accessAttributes().createAttribute("Mana", 100);
		go2.accessLogic().accessAttributes().createAttribute("Attack", -20);
		go2.accessLogic().accessAttributes().createAttribute("Armor", 5);
		go2.accessLogic().accessAttributes().createAttribute("Heal", 20);
		go2.accessLogic().accessAttributes().createAttribute("Attack2", -30);
		go2.setMovementSpeed(4);
		
		int m = go2.accessLogic().accessInteractions().createInteraction();
		Interaction test1 = go2.accessLogic().accessInteractions().getInteraction(m);
		test1.setDescription("heal: health + 10");
		test1.setImg("arrow_up.png");
		test1.setName("Heal");
		test1.setRange(50);
		CustomFunction cf = test1.generateCustomFunction("ModifyVariable");
		test1.addCustomFunction(cf);
		test1.getCustomFunction(0).getParameterFormat().setFieldValue("Variable", "Health");
		test1.getCustomFunction(0).getParameterFormat().setFieldValue("Delta", "5");
		System.out.println("delta" + test1.getCustomFunction(0).getParameterFormat().getParameterValue("Delta"));
		//test1.getCustomFunction(0).setParameters(test.getCustomFunction(0).getParameterFormat());
		
		int j = go2.accessLogic().accessInteractions().createInteraction();
		Interaction test2 = go2.accessLogic().accessInteractions().getInteraction(j);
		test2.setDescription("attack: damage = 5");
		test2.setImg("defend_icon.png");
		test2.setName("Attack");
		test2.setRange(1);
		CustomFunction cf2 = test2.generateCustomFunction("ModifyVariable");
		test2.addCustomFunction(cf2);
		test2.getCustomFunction(0).getParameterFormat().setFieldValue("Variable", "Health");
		test2.getCustomFunction(0).getParameterFormat().setFieldValue("Delta", "Attack");
		//test2.getCustomFunction(0).setParameters(test.getCustomFunction(0).getParameterFormat());
		
		int o = go2.accessLogic().accessInteractions().createInteraction();
		Interaction test3 = go2.accessLogic().accessInteractions().getInteraction(o);
		test3.setDescription("attack: damage = 5");
		test3.setImg("defend_icon.png");
		test3.setName("Attack2");
		test3.setRange(1);
		CustomFunction cf3 = test3.generateCustomFunction("ModifyVariable");
		test3.addCustomFunction(cf3);
		test3.getCustomFunction(0).getParameterFormat().setFieldValue("Variable", "Health");
		test3.getCustomFunction(0).getParameterFormat().setFieldValue("Delta", "Attack2");
		//test3.getCustomFunction(0).setParameters(test.getCustomFunction(0).getParameterFormat());
		
		Renderer renderer2 = new Renderer("ghoul.png");
		go2.setRenderer(renderer2);
		Set<GameObject> possibleunits = new HashSet<>();
		//possibleunits.add(go);
		possibleunits.add(go2);
		
		Timeline animation = new Timeline();
        
        
		myGP = new SinglePlayerGamePlayer(animation, gom, null, possibleunits);
		System.out.println(myGP);
		Scene scene = myGP.getScene();  
        gpStage.setScene(scene);
        gpStage.show();
        //myGP.update(myGOM.getElements());

        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeElapsed) {
		myGP.update();
		myGOM.runGameObjectLoop(SECOND_DELAY);
	}
}
