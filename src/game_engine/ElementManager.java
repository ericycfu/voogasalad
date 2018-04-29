package game_engine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Rayan
 *
 * 
 * Manager interface for creating engine managers like gameobject manager etc.
 * Manager itself can only set the id for the gameobject 
 */

public abstract class ElementManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, EngineObject> elementMap;
	private int new_ID_val;
	
	public ElementManager()
	{
		elementMap = new HashMap<Integer, EngineObject>();
		new_ID_val = 1;
	}
	
	public ElementManager(ElementManager other)
	{
		Map<Integer, EngineObject> map = new HashMap<>();
		for(Map.Entry<Integer, EngineObject> element : other.elementMap.entrySet())
		{
			map.put(element.getKey(), element.getValue());
		}
		this.new_ID_val = other.new_ID_val; 
		this.elementMap = map;
	}
	
	
	/**
	 * 
	 * @return
	 * Allows the manager to calculate the id before adding
	 */
	protected int calculateID()
	{
		return new_ID_val++;
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
	protected List<EngineObject> getElementsRaw() 
	{
		List<EngineObject> elementList = new ArrayList<>();
		
		for(Map.Entry<Integer, EngineObject> var : elementMap.entrySet())
		{
			elementList.add(var.getValue());
		}
		
		return Collections.unmodifiableList(elementList);
	}	
	
	public EngineObject get(int id) 
	{
		return elementMap.get(id);
	}
	
	/**
	 * 
	 * @return
	 * Allows subclasses to directly access the map, but prevents objects that call the manager from directly accessing it
	 */
	public void addElement(EngineObject obj)
	{
		elementMap.put(obj.getID(), obj);
		new_ID_val++;
	}
	
	/**
	 * Clears the element database
	 */
	public void clearManager()
	{
		elementMap.clear();
	}
	
	

}
