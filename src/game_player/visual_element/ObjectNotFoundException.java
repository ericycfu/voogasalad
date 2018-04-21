package game_player.visual_element;

public class ObjectNotFoundException extends Exception {
	
	@Override
	public String toString(){
		return "You have not chosen a unit to apply this interaction.";
	}
	
}
