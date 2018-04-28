package game_player.visual_element;

import game_object.GameObject;
import javafx.scene.image.Image;

public class BuildButton extends SkillButton {
	private GameObject myTarget;
	
	public BuildButton(Image skillImage, String interactionName, int interactionNumber, String skillDescription, double width, double height, GameObject target) {
		super(skillImage, interactionName, interactionNumber, skillDescription, width, height);
		myTarget = target;
	}
	
	public GameObject getTarget() {
		return myTarget;
	}
}
