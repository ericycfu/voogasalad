package main;

import authoring.view.StartScreen;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import game_player.SinglePlayerGamePlayer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Launch extends Application {
	
    private final int FRAMES_PER_SECOND = 60;
    private final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private GamePlayer myGamePlayer;
    
	@Override
	public void start(Stage primaryStage) {
		//new Main(primaryStage);
		StartScreen ss = new StartScreen(primaryStage);
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY, ss.getGP(), ss.getGOM()));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        ss.setTimeline(animation);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void step(double timeElapsed, SinglePlayerGamePlayer gp, GameObjectManager gom) {
		gp.update();
		gom.runGameObjectLoop(SECOND_DELAY);
	}
}
