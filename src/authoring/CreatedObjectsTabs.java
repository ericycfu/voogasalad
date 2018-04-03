package authoring;

import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class CreatedObjectsTabs extends TabPane {
	public CreatedObjectsTabs() {
		this.getTabs().addAll(
				new ObjectTypeTab("Buildings"), 
				new ObjectTypeTab("Units"));
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

	}
}
