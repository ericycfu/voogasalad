package game_authoring;
/**
 * Highest level of Game Authoring Environment presents front-end layout. 
 * Encompasses tabs between “design” and “place” with fixed CreatedObjects
 * display on the side.
 * @author xlany
 *
 */
public interface AuthoringEnvironmentView extends Pane {
	/**
	 * Distributes layout of tabs and CreatedObjectsView
	 */
	public void createLayout();
	/**
	 * Makes tabs for "design" and "place", and populate with relevent classes
	 */
	public void createTabs();
}
