package gui_elements.tabs;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.backend.MapSettings;
import authoring.view.MapSettingsView;
import game_engine.ResourceManager;
import javafx.scene.control.Tab;
import observables.Listener;

public class MapSettingsTab extends Tab implements Listener {
	AuthoringController ac;
	
	public MapSettingsTab(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.setText("Map Settings");
		System.out.println("i am in mapsettingstab");
		this.setContent(new MapSettingsView(ac, game));
	}

	@Override
	public void update() {
		((MapSettingsView) this.getContent()).setMapSettings(ac.getCurrentMap().getMapSettings());
		
	}

}
