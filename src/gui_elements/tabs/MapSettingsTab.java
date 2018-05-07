package gui_elements.tabs;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.edit_map.MapSettingsView;
import javafx.scene.control.Tab;
import observables.Listener;
/**
 * 
 * @author Eric Fu
 * @author Xiaolan You
 */

public class MapSettingsTab extends Tab implements Listener {
	AuthoringController ac;
	/**
	 * initializes a new map settings tab
	 * @param ac
	 * @param game
	 */
	public MapSettingsTab(AuthoringController ac, GameEntity game) {
		this.ac = ac;
		this.setText("Map Settings");
		this.setContent(new MapSettingsView(ac, game));
		this.getStyleClass().add("tab_title");
	}
/**
 * updates the tab whenever something in the other tabs change
 */
	@Override
	public void update() {
		((MapSettingsView) this.getContent()).setMapSettings(ac.getCurrentMap().getMapSettings());
		
	}

}
