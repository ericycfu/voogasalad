package authoring.backend;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the primary properties of a user-created component on the authoring
 * end. An instance is created in AuthoringObject.
 * 
 * @author Aditya Sridhar
 *
 */

public class MainComponentPropertyManager {
	
	private String myImagePath;
	private String myName;
	private List<String> myTags;
	private double myMovementSpeed;
	private boolean isBuilding;
	
	public MainComponentPropertyManager() {
		myImagePath = "";
		myName = "";
		myTags = new ArrayList<>();
		isBuilding = false;
	}
	
	public String getImagePath() {
		return myImagePath;
	}
	
	public void setImagePath(String imagePath) {
		myImagePath = imagePath;
	}
	
	public String getName() {
		return myName;
	}
	
	public void setName(String name) {
		myName = name;
	}
	
	public List<String> getTags() {
		return myTags;
	}
	
	public double getMovementSpeed() {
		return myMovementSpeed;
	}
	
	public void setMovementSpeed(double movementSpeed) {
		myMovementSpeed = movementSpeed;
	}
	
	public boolean isBuilding() {
		return isBuilding;
	}
	
	public void setBuilding(boolean b) {
		isBuilding = b;
	}	
}