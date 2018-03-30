package game_authoring;
/**
 * Author selects attributes according to available list provided by 
 * Game Engine, fills in necessary information, again provided by Game Engine. 
 * Stored as map. Called by CustomizeObject to link back to specific object. 
 * @author xlany
 *
 */
public interface CustomizeAttribute {
	/**
	 * 
	 * @return current object attributes
	 */
	public Map<> getAttributes();
}
