package gui_elements.tabs;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.edit_map.MapSettingsView;
import javafx.scene.control.Tab;
import observables.Listener;

public class MapSettingsTab extends Tab implements Listener {
	AuthoringController ac;
	
	public MapSettingsTab(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.setText("Map Settings");
		this.setContent(new MapSettingsView(ac, game));
		this.getStyleClass().add("tab_title");
	}

	@Override
	public void update() {
		((MapSettingsView) this.getContent()).setMapSettings(ac.getCurrentMap().getMapSettings());
		
	}

}
