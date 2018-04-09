package game_engine;

/**
 * 
 * @author Rayan
 *
 * @param <E>
 * 
 * Engine objects are objects that the engine will specifically handle. Every engine object must have id and 
 * a manager that handles that object.
 */

public interface EngineObject<E>{

	/**
	 * 
	 * @return
	 * Get the id of the object. ID is local to the manager i.e. a gameobject and a team can have the same ids but 
	 * since their processing in the engine doesn't overlap, it won't be a problem.
	 */
	public int getID();
	
	/**
	 * 
	 * @param manager
	 * Each engine object will be handled by a manager which will also set its id.
	 */
	public void addToManager(E manager);
	
	
}
