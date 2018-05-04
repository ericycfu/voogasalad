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
	public static final String DEFAULT_BUTTON_IMAGE_PATH = "default_icon.png";
	public static final String CANCEL_BUTTON_IMAGE_PATH = "cancel_icon.png";
	public static final String CANCEL_BUTTON_NAME = "Cancel";
	public static final String CANCEL_BUTTON_DESCRIPTION = "Restore the interaction to default";
	
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
		this.setMaxSize(width, height);
		setPicture(width, height);
		setDescription(mySkillDescription);
	}
	
	public void setDescription(String des) {
		Tooltip tooltip = new Tooltip(des);
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

