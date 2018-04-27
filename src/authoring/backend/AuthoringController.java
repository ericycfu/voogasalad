package authoring.backend;

import java.util.List;

import authoring.view.AuthoringView;
import authoring.view.CreatedMapsView;
import authoring.view.CreatedObjectsTabs;
import authoring.view.MakeGameTabs;
import gui_elements.tabs.GameSettingsTab;
import gui_elements.tabs.PlaceTab;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;

public class AuthoringController {
	private DraggableScrollPane myScroll;
	private MapEntity myMap;
	private AuthoringObject myObject;
	private CreatedMapsView myCreatedMapsView;

	public void addToAuthorController(DraggableScrollPane scroll) {
		myScroll = scroll;
	}
	
	public void addToAuthorController(CreatedMapsView createdmapsview) {
		myCreatedMapsView = createdmapsview;
	}
	
	public void updateMap(MapEntity map) {
		myMap = map;
	}
	
	public void updateObject(AuthoringObject obj) {
		myObject = obj;
	}
		
	public DraggableScrollPane getScroll() {
		return myScroll;
	}
	
	public MapEntity getCurrentMap() {
		return myMap;
	}
	
	public AuthoringObject getCurrentObject() {
		return myObject;
	}
	
	public CreatedMapsView getCreatedMapsView() {
		return myCreatedMapsView;
	}
}