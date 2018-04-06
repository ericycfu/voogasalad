package authoring.view;

import authoring.backend.CreatedObjects;
import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class CreatedObjectsTabs extends TabPane {
	private CreatedObjects myCreatedObjects;
	private CreatedObjectsView myCreatedObjectsView;
	public CreatedObjectsTabs() {
		myCreatedObjects = new CreatedObjects();
//		myCreatedObjectsView = new CreatedObjectsView(myCreatedObjects);
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings", new CreatedObjectsView(myCreatedObjects)), 
//				new ObjectTypeTab("Buildings"),
				new ObjectTypeTab("Units"));
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	}
}
