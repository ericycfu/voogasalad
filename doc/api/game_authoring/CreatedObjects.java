package game_authoring;
/**
 * Maintains a list of objects created by the game designer; it is contained 
 * within a Group object that acts as a “temporary infinite memory cache” 
 * of gameObject objects
 * @author xlany
 *
 */
public interface CreatedObjects {
	/**
	 * 
	 * @return list of current created objects
	 */
	public List<> getCreatedObjects();
}
