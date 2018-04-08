package game_engine;

public interface ElementManager<E> {
	
	/**
	 * 
	 * @param element
	 * @return
	 * returns an id that can be assigned to the object just added to the manager
	 */
	public int addElementToManager(E element);
	public void removeElement(E element);
	
	

}
