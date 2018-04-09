package gui_elements.tabs;

import gui_elements.buttons.ExitButton;
import gui_elements.buttons.PauseButton;
import gui_elements.buttons.ResumeButton;
import javafx.scene.Group;
import javafx.scene.control.Tab;

public class GameSettingsTab extends Tab {
	
	private Group design_root;

	public GameSettingsTab() {
		initialize();
	}
	
	private void initialize() {
		setButtons();
		setText();
	}
	
	public void setText(){
		this.setText("Game Settings");
	}
	
	private void setButtons() {
		design_root.getChildren().addAll(new PauseButton().getButton(),
				new ResumeButton().getButton(),
				new ExitButton().getButton());

	}
	
	
}
