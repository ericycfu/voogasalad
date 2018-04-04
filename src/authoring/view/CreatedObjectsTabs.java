package authoring.view;

import authoring.backend.CreatedObjects;
import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class CreatedObjectsTabs extends TabPane {
	private CreatedObjects myCreatedObjects;
	public CreatedObjectsTabs() {
		myCreatedObjects = new CreatedObjects();
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings"), 
				new ObjectTypeTab("Units"));
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

	}
}
