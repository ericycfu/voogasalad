package game_player.visual_element;

import javafx.scene.control.Button;

public class SkillButton extends Button {

	private String mySkillName;

	public String getSkillName() {
		return mySkillName;
	}
	
	public void setSkillName(String skillname) {
		mySkillName = skillname;
	}
}
