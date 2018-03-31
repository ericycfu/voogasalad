package game_engine;

/**
 * 
 * @author Rayan
 *	This class will handle movement data and functions. Must be declared inside a GameObject.
 */

public class Transform {
	
	private Vector2 position;
	
	public Transform(Vector2 vector)
	{
		position = vector;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between the current Transform and another Transform
	 */
	public Vector2 getDistance(Transform target)
	{
		return target.getPosition().SubtractVector(this.getPosition());
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between any two Tranform objects
	 */
	public Vector2 getDistance(Transform origin, Transform target)
	{
		return target.getPosition().SubtractVector(origin.getPosition());
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * unsigned angle between the current object and another
	 */
	public double getAngle(Transform target)
	{
		return Math.acos(position.getDotProduct(target.getPosition()) / (position.getMagnitude() * target.getPosition().getMagnitude()));	
	}
	
	/**
	 * 
	 * @param origin
	 * @param target
	 * @return
	 * unsigned angle between two gameobjects
	 */
	public double getAngle(Transform origin, Transform target)
	{
		return Math.acos(origin.position.getDotProduct(target.getPosition()) / (origin.getPosition().getMagnitude() * target.getPosition().getMagnitude()));
	}
	

}
