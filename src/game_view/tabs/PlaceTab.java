package game_view.tabs;

import javafx.scene.control.Tab;
import authoring.view.AuthoringMapView;

public class PlaceTab extends Tab {
	public PlaceTab() {
		this.setText("Place");
		this.setContent(new AuthoringMapView());
	}
}
