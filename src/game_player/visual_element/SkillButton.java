package game_player.visual_element;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkillButton extends Button {

	private String mySkillName;
	private Image mySkillImage;
	private String mySkillDescription;
	private int myInteractionID;
	private ImageView mySkillImageView;
	
	public SkillButton() {
		
	}
	
	public SkillButton(Image skillImage, String interactionName, int interactionNumber, String skillDescription) {
		mySkillImage = skillImage;
		mySkillName = interactionName;
		myInteractionID = interactionNumber;
		mySkillDescription = skillDescription;
	}
	
	public void setGraphic(double width) {
		mySkillImageView = new ImageView(mySkillImage);
		mySkillImageView.setFitHeight(width);
		mySkillImageView.setFitWidth(width);
		this.setGraphic(mySkillImageView);
	}
	
	public String getSkillName() {
		return mySkillName;
	}
	
	public String getSkillDescription() {
		return mySkillDescription;
	}
	
	public void setSkillName(String skillname) {
		mySkillName = skillname;
	}
	
	public int getInteractionID() {
		return myInteractionID;
	}
	
}

