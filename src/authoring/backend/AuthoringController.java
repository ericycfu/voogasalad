package authoring.backend;

import authoring.created_items.CreatedMapsView;
import authoring.view.DraggableScrollPane;
import gui_elements.tabs.DesignTab;
import gui_elements.tabs.MapSettingsTab;
/**
 * Class to bridge various authoring front-end classes
 * to pass information and give access across hierarchy
 * @author xlany
 *
 */
public class AuthoringController {
	private DraggableScrollPane myScroll;
	private MapEntity myMap;
	private AuthoringObject myObject;
	private CreatedMapsView myCreatedMapsView;
	private DesignTab myDesignTab;
	private MapSettingsTab myMapSettingsTab;
	/**
	 * Adds DraggableScrollPane instance
	 * @param scroll
	 */
	public void addToAuthorController(DraggableScrollPane scroll) {
		myScroll = scroll;
	}
	/**
	 * Adds CreatedMapsView instance
	 * @param createdmapsview
	 */
	public void addToAuthorController(CreatedMapsView createdmapsview) {
		myCreatedMapsView = createdmapsview;
	}
	/**
	 * Adds DesignTab instance
	 * @param designtab
	 */
	public void addToAuthorController (DesignTab designtab) {
		myDesignTab = designtab;
	}
	/**
	 * Adds MapSettingsTab instance
	 * @param mapSettingsTab
	 */
	public void addToAuthorController (MapSettingsTab mapSettingsTab) {
		myMapSettingsTab = mapSettingsTab;
	}
	/**
	 * Updates current map 
	 * @param map
	 * Calls MapSettingsTab to update with current map settings
	 */
	public void updateMap(MapEntity map) {
		myMap = map;
		if (myMapSettingsTab != null) {
			myMapSettingsTab.update();
		}
	}
	/**
	 * Updates current AuthoringObject
	 * @param obj
	 * Calls DesignTab to update with current object settings
	 */
	public void updateObject(AuthoringObject obj) {
		myObject = obj;
		myDesignTab.assignCurrentAuthoringObject();
	}
	/**
	 * Calls DesignTab to update build costs
	 */
	public void updateBuildCost() {
		myDesignTab.updateBuildCost();
	}	
	/**
	 * 
	 * @return DraggableScrollPane
	 */
	public DraggableScrollPane getScroll() {
		return myScroll;
	}
	/**
	 * 
	 * @return CreatedMapsView
	 */
	public CreatedMapsView getCreatedMapsView() {
		return myCreatedMapsView;
	}
	/**
	 * 
	 * @return current map
	 */
	public MapEntity getCurrentMap() {
		return myMap;
	}
	/**
	 * 
	 * @return current object
	 */
	public AuthoringObject getCurrentObject() {
		return myObject;
	}

	
}