package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.CreatedObjects;
import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class CreatedObjectsTabs extends TabPane implements AuthoringView {
	private CreatedObjects myCreatedObjects;
	private CreatedObjectsView myCreatedObjectsView;
	public CreatedObjectsTabs(AuthoringController ac) {
		myCreatedObjects = new CreatedObjects();
		ac.addToAuthorController(this);
//		myCreatedObjectsView = new CreatedObjectsView(myCreatedObjects);
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings", new CreatedObjectsView(ac, myCreatedObjects)), 
//				new ObjectTypeTab("Buildings"),
				new ObjectTypeTab("Units"));
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
}
