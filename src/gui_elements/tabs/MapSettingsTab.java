package gui_elements.tabs;

import authoring.backend.AuthoringController;
import authoring.view.MapSettingsView;
import game_engine.ResourceManager;
import javafx.scene.control.Tab;
import observables.Listener;

public class MapSettingsTab extends Tab implements Listener {
	
	public MapSettingsTab(AuthoringController ac) {
		this.setText("Map Settings");
		this.setContent(new MapSettingsView(ac.getCurrentMap().getMapSettings()));
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
