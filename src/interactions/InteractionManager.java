package interactions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import game_engine.ElementManager;
import game_engine.EngineObject;

public class InteractionManager extends ElementManager implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InteractionManager() {
		super();
	}

	public InteractionManager(InteractionManager other)
	{
		super(other);
	}
	
	
	public int createInteraction()
	{
		int newID = calculateID();
		Interaction interaction = new Interaction(newID);
		this.addElement(interaction);
		return newID;
	}
	
	public List<Interaction> getElements()
	{
		List<Interaction> interactions = new ArrayList<>();
		
		for(EngineObject eObj : getElementsRaw())
		{
			Interaction gObj = (Interaction) eObj;
			interactions.add(gObj);
		}
		return interactions;
	}
	
	public Interaction getInteraction(int id)
	{
		return (Interaction)(this.get(id));
	}
	
	public void removeLastAddedInteraction()
	{
		List<Interaction> inters = getElements();
		this.removeElement(inters.get(inters.size()-1));
	}
	
	public void setupImage() 
	{
		for(Interaction interaction: getElements()) 
		{
			interaction.setImageFromPath();
		}
	}
	
}
