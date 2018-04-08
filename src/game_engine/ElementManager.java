package game_engine;

/**
 * 
 * @author Rayan
 *
 * @param <E>
 * 
 * Manager interface for creating engine managers like gameobject manager etc.
 * Manager itself can only set the id for the gameobject 
 */

public interface ElementManager<E> {
	
	/**
	 * 
	 * @param element
	 * @return
	 * returns an id that can be assigned to the object just added to the manager
	 */
	public int addElementToManager(E element);
	
	/**
	 * 
	 * @param element
	 * Remove that element from the manager
	 */
	public void removeElement(E element);
	
	

}
