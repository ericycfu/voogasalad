package game_engine;

/**
 * 
 * @author Rayan
 * Allows the engine to manage cooldown or build times.
 */

public class Timer {

	private boolean isOn;	
	private double initialTime;
	private double elapsedTime;
	
	public Timer()
	{
		isOn = false;
		initialTime = 0;		//set when powerup triggered
	}
	
	public boolean isTimerOn() 
	{
		return isOn;
	}
	public void setTimerOn(boolean bool)
	{
		isOn = bool;
	}
	
	public double getInitialTime()
	{
		return initialTime;
	}
	public void setInitialTime(double time)
	{
		initialTime = time;
	}
	
	public boolean timeLimit(double currentTime, double duration)
	{
		double elapsed = currentTime - initialTime;
		System.out.println(elapsed + "elapsed");
		return (elapsed >= duration);
		
	}
	
	
}
