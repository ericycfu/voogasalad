package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import gui_elements.tabs.DesignTab;
import gui_elements.tabs.MapSettingsTab;
import gui_elements.tabs.PlaceTab;
import javafx.scene.control.TabPane;

public class MakeGameTabs extends TabPane implements AuthoringView {
		PlaceTab myPlaceTab;
	public MakeGameTabs(AuthoringController ac, GameEntity game) {
		myPlaceTab = new PlaceTab(ac, game);
		this.getTabs().addAll(
				new MapSettingsTab(ac, game),
				new DesignTab(ac, game),
				myPlaceTab);
		this.setPrefSize(800, 900);
		this.getStyleClass().add("tab_pane");
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
}
