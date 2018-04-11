package authoring.backend;

import java.util.List;
import game_object.ObjectAttributes;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.scene.image.Image;
public class AuthoringObject {
	public static final String TEST_IMAGE = "/images/station.png";
	public static final String TEST_IMAGE2 = "./src/resources/images/station.png";
	private Image myImage;
	private DraggableImageView myDragImage;
	private String myName;
	private String myTag;
	private double myX;
	private double myY;
	private double myMovementSpeed;
	private ObjectLogic myObjectLogic;
	private ObjectAttributes myAttributes;
	private InteractionManager myInteractions;
	
	public AuthoringObject() {
		defaultObject();
		testObject();
	}
	
	private void defaultObject() {
		myImage = null;
		myName = "";
		myX = 0;
		myY = 0;
		myObjectLogic = new ObjectLogic();
		myAttributes = myObjectLogic.accessAttributes();
		myInteractions = myObjectLogic.accessInteractions();
		myInteractions = myObjectLogic.accessInteractions();
		
	}
	
	private void testObject() {
		myImage = new Image(getClass().getResourceAsStream(TEST_IMAGE));
		myName = "Station";
	}
	
	public Image getImage() {
		return myImage;
	}
	
	public void setImage(String image_filename) {
		myImage = new Image(getClass().getClassLoader().getResourceAsStream(image_filename));
	}
	
	public String getName() {
		return myName;
	}
	
	public void setName(String name) {
		myName = name;
	}
	
	public double getMovementSpeed() {
		return myMovementSpeed;
	}
	
	public void setMovementSpeed(double movementSpeed) {
		myMovementSpeed = movementSpeed;
	}
	
	public String getTag() {
		return myTag;
	}
	
	public void setTag(String tag) {
		myTag = tag;
	}
	
	public double getX() {
		return myX;
	}
	
	public double getY() {
		return myY;
	}
	
	public void changeX(double newX) {
		myX = newX;
	}
	
	public void changeY(double newY) {
		myY = newY;
	}

	public ObjectAttributes getObjectAttributesInstance() {
		return myAttributes;
	}
}
