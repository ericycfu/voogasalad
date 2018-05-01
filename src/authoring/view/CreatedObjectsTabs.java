package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.CreatedObjects;
import authoring.backend.GameEntity;
import authoring.created_items.CreatedMapsView;
import authoring.created_items.CreatedObjectsView;
import gui_elements.factories.ButtonFactory;
import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;

public class CreatedObjectsTabs extends TabPane implements AuthoringView {
	private GameEntity game;
	private CreatedObjects myCreatedObjects;
	private CreatedObjectsView myCreatedObjectsView;
	public CreatedObjectsTabs(AuthoringController ac, GameEntity game) {
		this.game = game;
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings", new CreatedObjectsView(ac, game.getCreatedObjects())), 
//				new ObjectTypeTab("Buildings"),
				new ObjectTypeTab("Maps", new CreatedMapsView(ac, game.getCreatedMaps())));
		this.setPrefSize(500, 800);
		this.getStyleClass().add("tab_pane");
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
	
}
