package game_engine;
/**
 * Represents an object that can be shown on the screen
 * @author andrew
 *
 */
public interface DisplayObject {
	/**
	 * Moves the DisplayObject to the specified location
	 * @param xcor the xcoordinate of the new location
	 * @param ycor the ycoordinate of the new location
	 */
	public void move(double xcor, double ycor);
}
