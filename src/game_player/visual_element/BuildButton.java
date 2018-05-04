package game_player.visual_element;

import game_object.GameObject;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import javafx.scene.image.Image;

public class BuildButton extends SkillButton {
	private static final String BUILD_TIME = "Build Time: ";
	private GameObject myTarget;
	private String myDescription;
	private String myBuildCost;
	private String myBuildTime; 
	
	public BuildButton(Image skillImage, String interactionName, int interactionNumber, double width, double height, GameObject target) {
		super(skillImage, interactionName, interactionNumber, null, width, height);
		myBuildCost = GamePlayer.EMPTY;
		try {
			for (String key : target.accessLogic().accessAttributes().getCosts().keySet()) {
				myBuildCost = myBuildCost + key + GamePlayer.COLON + target.accessLogic().accessAttributes().getCosts().get(key);
			}
		} catch (UnmodifiableGameObjectException e) {
			// do nothing
		}
		try {
			myBuildTime = BUILD_TIME + Double.toString(target.accessLogic().accessAttributes().getBuildTime());
		} catch (UnmodifiableGameObjectException e) {
			// do nothing
		}
		myDescription = target.getName() + GamePlayer.LINEBREAK + myBuildTime + GamePlayer.LINEBREAK + myBuildCost;
		setDescription(myDescription);
	}
	
	public GameObject getTarget() {
		return myTarget;
	}
}
