package game_object;

import transform_library.Vector2;

public class GameUnit extends GameObject 
{
	ObjectAttributes attributes;
	
	public GameUnit(Vector2 startingPosition, String tag, String name)
	{
		super(startingPosition, tag, name);
		this.attributes = new ObjectAttributes();
	}
}
