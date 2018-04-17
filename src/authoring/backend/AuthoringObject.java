package authoring.backend;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import game_object.ObjectAttributes;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.scene.Group;
import javafx.scene.image.Image;
import transform_library.Vector2;
public class AuthoringObject {
	//extends group?
	public static final String TEST_IMAGE = "/images/station.png";
	public static final String TEST_IMAGE_DUVALL= "/images/rcd.png";
	public static final int ICON_PREF_WIDTH = 70;
	public static final int ICON_PREF_HEIGHT = 70;

	@XStreamOmitField
	private transient DraggableImageView myDragImage;
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
		addTestObject();
//		addDuvall();
	}
	
	private void defaultObject() {
		myDragImage = null;		
		myName = "";
		myX = 0;
		myY = 0;
		myObjectLogic = new ObjectLogic();
		myAttributes = myObjectLogic.accessAttributes();
		myInteractions = myObjectLogic.accessInteractions();
	}
	
	private void addTestObject() {
		Image image = new Image(getClass().getResourceAsStream(TEST_IMAGE));
		myDragImage = new DraggableImageView(this, image);
		myDragImage.setFitWidth(ICON_PREF_WIDTH);
		myDragImage.setFitHeight(ICON_PREF_HEIGHT);
		myName = "Station";
	}
	
	private void addDuvall() {
		Image image = new Image(getClass().getResourceAsStream(TEST_IMAGE_DUVALL));
		myDragImage = new DraggableImageView(this, image);
		myDragImage.setFitWidth(ICON_PREF_WIDTH);
		myDragImage.setFitHeight(ICON_PREF_HEIGHT);
		myName = "Final Boss";
	}
	
	public Image getImage() {
		return myDragImage.getImage();
	}
	
	public DraggableImageView getDragImage() {
		return myDragImage;
	}
	
	public void updateImage() {
		myDragImage.setX(myX);
		myDragImage.setY(myY);
	}
	
	public void setImage(String image_filename) {
//		Image img = new Image(getClass().getClassLoader().getResourceAsStream(image_filename));
//		myDragImage = new DraggableImageView(this, img);
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
		return myDragImage.getX();
	}
	
	public double getY() {
		return myDragImage.getY();
	}
	
	public void changeX(double newX) {
		myX = newX;
	}
	
	public void changeY(double newY) {
		myY = newY;
	}
	
	public void changeByX(double deltaX) {
		myX = myX + deltaX;
	}
	
	public void changeByY(double deltaY) {
		myY = myY + deltaY;
	}

	public ObjectAttributes getObjectAttributesInstance() {
		return myAttributes;
	}
}
