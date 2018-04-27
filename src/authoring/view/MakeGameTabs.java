package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import gui_elements.tabs.DesignTab;
import gui_elements.tabs.GameSettingsTab;
import gui_elements.tabs.MapSettingsTab;
import gui_elements.tabs.PlaceTab;
import javafx.scene.control.TabPane;

public class MakeGameTabs extends TabPane implements AuthoringView {
	private PlaceTab myPlaceTab;
	public MakeGameTabs(AuthoringController ac, GameEntity game) {
		myPlaceTab = new PlaceTab(ac, game);
		
		this.getTabs().addAll(
//				new GameSettingsTab(),
				new MapSettingsTab(ac),
				new DesignTab(),
				myPlaceTab);
//		ac.addToAuthorController(myPlaceTab.getMap());
		this.setPrefSize(700, 800);
		this.getStyleClass().add("tab_pane");
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
}
