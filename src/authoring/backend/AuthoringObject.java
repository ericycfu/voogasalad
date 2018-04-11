package authoring.backend;

import java.util.List;
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
	public static final int ICON_PREF_WIDTH = 50;
	public static final int ICON_PREF_HEIGHT = 50;

	private DraggableImageView myDragImage;
	private String myName;
	private double myX;
	private double myY;
	private ObjectLogic myObjectLogic;
	private ObjectAttributes myAttributes;
	private InteractionManager myInteractions;
	
	public AuthoringObject() {
		defaultObject();
//		addTestObject();
		addDuvall();
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
	
	public String getName() {
		return myName;
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
