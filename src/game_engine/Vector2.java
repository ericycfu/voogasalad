package game_engine;


/**
 * 
 * @author Rayan
 *	Class for representing Vector coordinates
 *  Allows users to perform vector operations
 *	The positions stored here are taken from the origin i.e. from 0,0
 */

public class Vector2 {
	
	private double x;
	private double y;
	
	/**
	 * Empty constructor if only needed for functions
	 */
	public Vector2(){}
	
	public Vector2(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double getMagnitude()
	{
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * to Calculate the difference of two vectors, 
	 * returns current - a
	 */
	public Vector2 SubtractVector(Vector2 b)
	{
		return new Vector2(x - b.getX(), y - b.getY());
	}
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 * To calculate the sum of two vectors
	 * returns a + b
	 */
	public Vector2 AddVector(Vector2 b)
	{
		return new Vector2(x + b.getX(), y + b.getY());
	}
	
	/**
	 * 
	 * @param a
	 * @param divisor
	 * @return
	 * Divides a vector by a scalar
	 * returns a / divisor
	 */
	public Vector2 DivideVector(double divisor)
	{
		return new Vector2(x / divisor,  y / divisor);
	}
	
	/**
	 * 
	 * @param a
	 * @param multiple
	 * @return
	 * Multiplies a vector by a scalar
	 */
	public Vector2 MultiplyVector(double multiple)
	{
		return new Vector2(x * multiple, y * multiple);
	}
}
