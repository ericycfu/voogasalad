package game_player.visual_element;

import game_object.GameObject;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import javafx.scene.image.Image;

/**
 * This class extends the SkillButton class and all its functionalities relating unit interactions. 
 * 
 * Its purpose is to have more specialized features than the original SkillButton so that the user can see the details of the available units. 
 * 
 * @author Frank Yin
 *
 */
public class BuildButton extends SkillButton {
	private static final String BUILD_TIME = "Build Time: ";
	private GameObject myTarget;
	private String myDescription;
	private String myBuildCost;
	private String myBuildTime; 
	
	/**
	 * This class takes in very similar constructors as the original SkillButton class, but further processes the parameters to get more details from the target unit to display its name and costs. 
	 * 
	 * @param skillImage: the image of the interaction 
	 * @param interactionName: the name of the interaction 
	 * @param interactionNumber: the interaction ID
	 * @param width: the width of this button
	 * @param height: the height of this button 
	 * @param target: the game object associated with this build interaction (what unit can be built) 
	 */
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
	
	/**
	 * This returns the to-be-built game object so that modifications can be introduced by other classes to add this unit to the GameObjectManager
	 * 
	 * @return: the game object that associated with this build interaction 
	 */
	public GameObject getTarget() {
		return myTarget;
	}
	
}
