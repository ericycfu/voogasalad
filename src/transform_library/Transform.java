package transform_library;

/**
 * 
 * @author Rayan
 *	This class will handle transformation in world space for any object.
 */

public class Transform {
	
	private Vector2 position;
	private double rotation;

	
	public Transform(Vector2 position)
	{
		this.position = position;
	}
	
	public Transform(Vector2 position, float rotation)
	{
		this.position = position;
		this.rotation = rotation;
	}
	
	public Vector2 getPosition()
	{
		return position;
	}
	
	public void setPosition(Vector2 position)
	{
		this.position = position;
	}
	
	public double getRotation()
	{
		return rotation;
	}
	
	public void setRotation(double rotation)
	{
		this.rotation = rotation;
	}
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between this object and another Transform
	 */
	public Vector2 getDisplacementVector(Transform target)
	{
		return getDisplacementVector(this, target);
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * Returns the distance vector between any two Tranform objects
	 */
	public Vector2 getDisplacementVector(Transform origin, Transform target)
	{
		return target.getPosition().SubtractVector(origin.getPosition());
	}
	
	/**]
	 * 
	 * @param target
	 * @return
	 * Get the magnitude of the displacement between this object and another
	 */
	public double getDisplacement(Transform target)
	{
		return getDisplacement(this, target);
	}
	
	/**
	 * 
	 * @param origin
	 * @param target
	 * @return
	 * Get the magnitude of the displacement between two gameobjects.
	 */
	public double getDisplacement(Transform origin, Transform target)
	{
		return getDisplacementVector(origin, target).getMagnitude();
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 * unsigned angle between this object and another
	 */
	public double getAngle(Transform target)
	{
		//return Math.acos(position.getDotProduct(target.getPosition()) / (position.getMagnitude() * target.getPosition().getMagnitude()));
		return getAngle(this, target);
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
	
	/**
	 * 
	 * @param direction
	 * @param stepDistance: the distance to be moved each step
	 * 
	 * Move a transform object in a specific direction
	 */
	public void Move(Transform direction, double stepDistance)
	{
		position = position.AddVector(direction.getPosition().MultiplyVector(stepDistance));
	}
	/**
	 * 
	 * @param target: target gameobject
	 * @param stepDistance: the distance to be moved at every step
	 * 
	 * Moves the current object towards a new object. 
	 * Returns false if the gameobject reaches the target
	 */
	public boolean MoveTowards(Transform target, double stepDistance)
	{
		Vector2 resultantVector = this.getDisplacementVector(target);
		position = position.AddVector(resultantVector.getNormalized().MultiplyVector(stepDistance));
		if(getDisplacement(this, target) <= stepDistance)
		{
			return false;
		}
		return true;
	}
	
}
