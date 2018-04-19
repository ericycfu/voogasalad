package game_engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author Rayan
 *
 * @param <E>
 * 
 * Manager interface for creating engine managers like gameobject manager etc.
 * Manager itself can only set the id for the gameobject 
 */

public abstract class ElementManager<E> {
	
	private Map<Integer, E> elementMap;
	
	/**
	 * 
	 * @param element
	 * @return
	 * returns an id that can be assigned to the object just added to the manager
	 */
	public int addElementToManager(E element)
	{
		int id = 1;
		if(elementMap.isEmpty())
		{
			elementMap.put(id, element);
		}
		else
		{
			id = elementMap.size() + 1;
			elementMap.put(id, element);
		}
		return id;
	}
	
	/**
	 * 
	 * @param element
	 * Remove that element from the manager
	 */
	public void removeElement(EngineObject element)
	{
		elementMap.remove(element.getID());
	}
	
	/**
	 * 
	 * @return
	 * Returns a list of key value pairs from the manager which can be accessed 
	 */
	public List<E> getElements() 
	{
		List<E> elementList = new ArrayList<>();
		
		for(Map.Entry<Integer, E> var : elementMap.entrySet())
		{
			elementList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(elementList);
	}	
	
	public E get(int id) 
	{
		return elementMap.get(id);
	}
	
	/**
	 * 
	 * @return
	 * Allows subclasses to directly access the map, but prevents objects that call the manager from directly accessing it
	 */
	protected Map<Integer, E> getElementMap()
	{
		return elementMap;
	}

}
