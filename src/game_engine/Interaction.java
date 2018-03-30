package game_engine;
/**
 * Codes for an action that occurs in the game. Different actions might simulate a GameObject acting on itself or acting on another object
 * @author andrew
 *
 */
public interface Interaction {
	/**
	 * Causes the defined action to occur 
	 */
	public void act();
}
