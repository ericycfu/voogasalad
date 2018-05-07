package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import gui_elements.tabs.DesignTab;
import gui_elements.tabs.MapSettingsTab;
import gui_elements.tabs.PlaceTab;
import javafx.scene.control.TabPane;
/**
 * 
 * @author Eric Fu
 *creates the tabs on the left side of the make game screen.
 */
public class MakeGameTabs extends TabPane implements AuthoringView {
		PlaceTab myPlaceTab;
	/**
	 * initializes a new instance of the tabs
	 * @param ac the authoring controller
	 * @param game the game you are making
	 */
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
