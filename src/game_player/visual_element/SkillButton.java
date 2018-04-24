package game_player.visual_element;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Siyuan Chen, Frank Yin
 *
 */
public class SkillButton extends Button {

	private String mySkillName;
	private Image mySkillImage;
	private String mySkillDescription;
	private int myInteractionID;
	private ImageView mySkillImageView;
	
	public SkillButton(){
		
	}
	
	public SkillButton(Image skillImage, String interactionName, int interactionNumber, String skillDescription, double width, double height) {
		mySkillImage = skillImage;
		mySkillName = interactionName;
		myInteractionID = interactionNumber;
		mySkillDescription = skillDescription;
		setPicture(width, height);
		setDescription();
	}
	
	private void setDescription() {
		Tooltip tooltip = new Tooltip("hello");
		this.setTooltip(tooltip);
	}
	
	private void setPicture(double width, double height) {
		mySkillImageView = new ImageView(mySkillImage);
		mySkillImageView.setFitHeight(height);
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

