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
	private MakeGameTabs myMakeGameTabs;
	private DraggableScrollPane myScroll;
	private MapEntity myMap;
	private CreatedObjectsTabs myCreatedObjectsTabs;
	private AuthoringObject myObject;
	private PlaceTab myPlaceTab;
	private CreatedMapsView myCreatedMapsView;
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
	
	public MapEntity getMap() {
		return myMap;
	}
	
	public CreatedMapsView getCreatedMapsView() {
		return myCreatedMapsView;
	}
}