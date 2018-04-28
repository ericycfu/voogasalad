package authoring.backend;

import java.util.ArrayList;
import java.util.List;

public class MainComponentPropertyManager {
	
	private String myImagePath;
	private String myName;
	private List<String> myTags;
	private double myMovementSpeed;
	private boolean isBuilding;
	private int myTeam;
	
	public MainComponentPropertyManager() {
		myImagePath = "";
		myName = "";
		myTags = new ArrayList<String>();
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
	
	public int getTeam() {
		return myTeam;
	}
	
	public void setTeam(int team) {
		myTeam = team;
	}
}