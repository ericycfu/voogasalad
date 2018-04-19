package game_engine;

/**
 * 
 * @author Rayan
 *
 * @param <E>
 * 
 * Engine objects are objects that the engine will specifically handle. Every engine object must have id assigned
 * by a manager.
 */

public interface EngineObject {

	/**
	 * 
	 * @return
	 * Get the id of the object. ID is local to the manager i.e. a gameobject and a team can have the same ids but 
	 * since their processing in the engine doesn't overlap, it won't be a problem.
	 */
	public int getID();
	

	
	
}
