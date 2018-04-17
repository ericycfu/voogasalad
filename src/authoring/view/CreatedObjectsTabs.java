package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.CreatedObjects;
import authoring.backend.GameEntity;
import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class CreatedObjectsTabs extends TabPane implements AuthoringView {
	private GameEntity game;
	private CreatedObjects myCreatedObjects;
	private CreatedObjectsView myCreatedObjectsView;
	public CreatedObjectsTabs(AuthoringController ac, GameEntity game) {
		this.game = game;
		ac.addToAuthorController(this);
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings", new CreatedObjectsView(ac, game.getCreatedObjects())), 
//				new ObjectTypeTab("Buildings"),
				new ObjectTypeTab("Maps"));
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
}
