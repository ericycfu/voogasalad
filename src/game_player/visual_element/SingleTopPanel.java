package game_player.visual_element;

import java.util.Set;

import authoring.view.StartScreen;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import javafx.animation.Timeline;

public class SingleTopPanel extends TopPanel {

	public static final int SECPERMIN = 60;
	private Timeline myTimeline;
	
	public SingleTopPanel(int teamID, GameObjectManager gom, Set<GameObject> possibleUnits, double xsize, double ysize) {
		super(teamID, gom, possibleUnits, xsize, ysize);
		getButtons().get(0).setOnAction(e -> {
			myTimeline.play();
		});
		getButtons().get(1).setOnAction(e -> {
			myTimeline.pause();
		});
	}
	
	public void setTimeline(Timeline timeline) {
		myTimeline = timeline;
	}

	@Override
	public void setTimeTA(int time) {
		String temp = TIME + GamePlayer.COLON + GamePlayer.SPACE + time / StartScreen.FRAMES_PER_SECOND / SECPERMIN + GamePlayer.COLON + time / StartScreen.FRAMES_PER_SECOND % SECPERMIN;
		getTimeTA().setText(temp);
	}

	@Override
	public void startTimeline() {
		myTimeline.play();
	}
}
