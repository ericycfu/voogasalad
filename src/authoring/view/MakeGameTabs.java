package authoring.view;

import authoring.backend.AuthoringController;
import gui_elements.tabs.DesignTab;
import gui_elements.tabs.GameSettingsTab;
import gui_elements.tabs.PlaceTab;
import javafx.scene.control.TabPane;

public class MakeGameTabs extends TabPane implements AuthoringView {
	private PlaceTab myPlaceTab;
	public MakeGameTabs(AuthoringController ac) {
		myPlaceTab = new PlaceTab(ac);
		
		this.getTabs().addAll(
				new GameSettingsTab(),
				new DesignTab(),
				myPlaceTab);
		ac.addToAuthorController(this);
//		ac.addToAuthorController(myPlaceTab.getMap());
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
}
