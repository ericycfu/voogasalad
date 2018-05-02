package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import authoring.created_items.CreatedMapsView;
import authoring.created_items.CreatedObjectsView;
import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;

public class CreatedObjectsTabs extends TabPane implements AuthoringView {

	public CreatedObjectsTabs(AuthoringController ac, GameEntity game) {
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings", new CreatedObjectsView(ac, game.getCreatedObjects())), 
//				new ObjectTypeTab("Buildings"),
				new ObjectTypeTab("Maps", new CreatedMapsView(ac, game.getCreatedMaps())));
		this.setPrefSize(RIGHT_TABS_WIDTH, TABS_HEIGHT);
		this.getStyleClass().add("tab_pane");
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
}
