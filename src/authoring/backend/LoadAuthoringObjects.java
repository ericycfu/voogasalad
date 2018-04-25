package authoring.backend;

import gui_elements.tabs.DesignTab;

public class LoadAuthoringObjects {
	
	public LoadAuthoringObjects(AuthoringObject authoring_object) {
		try {
			DesignTab.setAuthoringObject(authoring_object);
			DesignTab.assignComponents();
		} catch (Exception e) {
			System.err.println("Cannot load/edit authoring object!");
		}
	}
}