package game_player.visual_element;

import game_object.GameObject;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import javafx.scene.image.Image;

public class BuildButton extends SkillButton {
	private static final String buildtime = "Build Time: ";
	private GameObject myTarget;
	private String myDescription;
	private String myBuildCost;
	private String myBuildTime; 
	
	public BuildButton(Image skillImage, String interactionName, int interactionNumber, double width, double height, GameObject target) {
		super(skillImage, interactionName, interactionNumber, null, width, height);
		myBuildCost = "";
		try {
			System.out.println("tried to get into map");
			for (String key : target.accessLogic().accessAttributes().getCosts().keySet()) {
				myBuildCost = myBuildCost + key + GamePlayer.COLON + target.accessLogic().accessAttributes().getCosts().get(key);
				System.out.println(key);
			}
		} catch (UnmodifiableGameObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			myBuildTime = buildtime + Double.toString(target.accessLogic().accessAttributes().getBuildTime());
		} catch (UnmodifiableGameObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myDescription = target.getName() + GamePlayer.LINEBREAK + myBuildTime + GamePlayer.LINEBREAK + myBuildCost;
		setDescription(myDescription);
	}
	
	public GameObject getTarget() {
		return myTarget;
	}
}
