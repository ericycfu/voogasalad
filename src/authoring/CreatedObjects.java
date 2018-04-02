package authoring;

import gui_elements.tabs.ObjectTypeTab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class CreatedObjects extends TabPane {
	public CreatedObjects() {
		this.getTabs().addAll(
				new ObjectTypeTab("Building"), 
				new ObjectTypeTab("Unit"));
		this.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

	}
	
}
