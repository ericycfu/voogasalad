package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
	
	public SkillButton() {
		initialize();
	}
	
	public SkillButton(Image skillImage, String interactionName, int interactionNumber, String skillDescription) {
		mySkillImage = skillImage;
		mySkillName = interactionName;
		myInteractionID = interactionNumber;
		mySkillDescription = skillDescription;
		initialize();
	}
	
	private void initialize() {
		Tooltip tooltip = new Tooltip("hello");
		this.setTooltip(tooltip);
	}
	
	/**
	 * How is this useful?
	public void setGraphic(double width) {
		mySkillImageView = new ImageView(mySkillImage);
		mySkillImageView.setFitHeight(width);
		mySkillImageView.setFitWidth(width);
		this.setGraphic(mySkillImageView);
	}
	**/
	
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

