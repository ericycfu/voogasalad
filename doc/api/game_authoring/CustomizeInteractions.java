package game_authoring;

/**
 * Author selects object interactions according to available list 
 * provided by Game Engine. Stored as map. Called by CustomizeObject 
 * to link back to specific object.
 * @author xlany
 *
 */
public interface CustomizeInteractions {
	/**
	 * 
	 * @return current object iteractions
	 */
	public Map<> getInteractions();
}
