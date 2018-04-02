package authoring;

import gui_elements.tabs.DesignTab;
import gui_elements.tabs.PlaceTab;
import javafx.scene.control.TabPane;

public class MakeGameTabs extends TabPane {
	public MakeGameTabs() {
		this.getTabs().addAll(new DesignTab(),
				new PlaceTab());
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}	
}
