package authoring.backend;

import java.util.List;

import authoring.view.AuthoringView;
import authoring.view.CreatedObjectsTabs;
import authoring.view.MakeGameTabs;
import gui_elements.tabs.GameSettingsTab;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public class AuthoringController {
	private MakeGameTabs myMakeGameTabs;
	private DraggableScrollPane myMap;
	private CreatedObjectsTabs myCreatedObjectsTabs;
	public AuthoringController() {
		
	}
	public AuthoringController(MakeGameTabs gametabs, CreatedObjectsTabs objtabs) {
		myMakeGameTabs = gametabs;
		myCreatedObjectsTabs = objtabs;
	}
	
	public void addToAuthorController(MakeGameTabs gametabs) {
		myMakeGameTabs = gametabs;
	}
	
	public void addToAuthorController(CreatedObjectsTabs objtabs) {
		myCreatedObjectsTabs = objtabs;
	}
	
	public void addToAuthorController(DraggableScrollPane map) {
		myMap = map;
	}
	
	public void addToAuthoringController() {
		
	}
	
	public DraggableScrollPane getMap() {
		return myMap;
	}
}


