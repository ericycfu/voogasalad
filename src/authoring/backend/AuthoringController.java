package authoring.backend;

import authoring.created_items.CreatedMapsView;
import authoring.view.DraggableScrollPane;
import gui_elements.tabs.DesignTab;
import gui_elements.tabs.MapSettingsTab;

public class AuthoringController {
	
	private DraggableScrollPane myScroll;
	private MapEntity myMap;
	private AuthoringObject myObject;
	private CreatedMapsView myCreatedMapsView;
	private DesignTab myDesignTab;
	private MapSettingsTab myMapSettingsTab;

	public void addToAuthorController(DraggableScrollPane scroll) {
		myScroll = scroll;
	}
	
	public void addToAuthorController(CreatedMapsView createdmapsview) {
		myCreatedMapsView = createdmapsview;
	}
	
	public void addToAuthorController (DesignTab designtab) {
		myDesignTab = designtab;
	}
	public void addToAuthorController (MapSettingsTab mapSettingsTab) {
			myMapSettingsTab = mapSettingsTab;
	}
	
	public void updateMap(MapEntity map) {
		myMap = map;
		if (myMapSettingsTab != null) {
			myMapSettingsTab.update();
		}
	}
	
	public void updateObject(AuthoringObject obj) {
		myObject = obj;
		myDesignTab.assignCurrentAuthoringObject();
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
	
	public void updateBuildCost() {
		myDesignTab.updateBuildCost();
	}	
}