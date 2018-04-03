package authoring;

import game_view.tabs.DesignTab;
import game_view.tabs.GameSettingsTab;
import game_view.tabs.PlaceTab;

import javafx.scene.control.TabPane;

public class MakeGameTabs extends TabPane {
	public MakeGameTabs() {
		this.getTabs().addAll(
				new GameSettingsTab(),
				new DesignTab(),
				new PlaceTab());
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}	
}
