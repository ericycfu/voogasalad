package authoring;

import game_view.tabs.ObjectTypeTab;
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
