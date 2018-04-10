package game_object;

import java.util.ArrayList;
import java.util.List;

import interactions.Interaction;
import transform_library.Vector2;


/**
 * 
 * @author Rayan
 * GameUnit contains all the game logic for the object that the user will be allowed to interact with like
 * attributes and interactions
 */

public class ObjectLogic {
	
	ObjectAttributes attributes;
	List<Interaction> interactions;
	
	public ObjectLogic()
	{
		this.attributes = new ObjectAttributes();
		interactions = new ArrayList<>();
	}
	
	public ObjectAttributes accessAttributes()
	{
		return attributes;
	}
	
	public List<Interaction> accessInteractions() {
		return interactions;
	}	
}