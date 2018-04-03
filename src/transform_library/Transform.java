package transform_library;

/**
 * 
 * @author Rayan
 *	This class will handle transformation in world space for any object.
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
	 * Returns the distance vector between this object and another Transform
	 */
	public Vector2 getDistanceVector(Transform target)
	{
		return target.getPosition().SubtractVector(this.getPosition());
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between any two Tranform objects
	 */
	public Vector2 getDistanceVector(Transform origin, Transform target)
	{
		return target.getPosition().SubtractVector(origin.getPosition());
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * unsigned angle between this object and another
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
	
	
	public void Move(Transform direction, double stepDistance)
	{
		position = position.AddVector(direction.getPosition().MultiplyVector(stepDistance));
	}
	/**
	 * 
	 * @param target target gameobject
	 * @param stepDistance the distance to be moved at every step
	 * Moves the current object towards a new object. 
	 */
	public void MoveTowards(Transform target, double stepDistance)
	{
		Vector2 resultantVector = this.getDistanceVector(target);
		position = position.AddVector(resultantVector.getNormalized().MultiplyVector(stepDistance));
	}
	
}
