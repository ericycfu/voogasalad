package authoring.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import conditions.ConditionManager;
import game_object.ObjectAttributes;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import interactions.Interaction;
import interactions.InteractionManager;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import transform_library.Vector2;

public class AuthoringObject {
	//extends group?
	public static final String TEST_IMAGE = "/images/station.png";
	public static final String TEST_IMAGE_DUVALL= "/images/rcd.png";
	public static final int ICON_PREF_WIDTH = 70;
	public static final int ICON_PREF_HEIGHT = 70;
	
	@XStreamOmitField
	private transient DraggableImageView myDragImage;
	private String myImagePath;
	private String myName;
	private List<String> myTags;
	private double myX;
	private double myY;
	private double myMovementSpeed;
	private boolean isBuilding;
	private double buildTime;
	private Map<String, Double> buildCost;
	private ObjectLogic myObjectLogic;
	private ObjectAttributes myAttributes;
	private InteractionManager myInteractions;
	private ConditionManager myConditionManager;
	
	public AuthoringObject() {
		defaultObject();
		addTestObject();
//		addDuvall();
	}
		
	public AuthoringObject(DraggableImageView img) {
		myDragImage = img;
		myX = 0;
		myY = 0;
	}
	
	private void defaultObject() {
		myDragImage = null;		
		myName = "";
		myX = 0;
		myY = 0;
		myObjectLogic = new ObjectLogic();
		myAttributes = myObjectLogic.accessAttributes();
		myInteractions = myObjectLogic.accessInteractions();
		myTags = new ArrayList<String>();
		myConditionManager = new ConditionManager();
		myTags = new ArrayList<>();
		isBuilding = false;
		buildTime = 0;
		buildCost = new HashMap<>();
	}
	
	private void addTestObject() {
		setImage(TEST_IMAGE);
		myName = "Station";
	}
	
	private void addDuvall() {
		setImage(TEST_IMAGE_DUVALL);
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
	
	public void setImage(String image_path) {
		myImagePath = image_path;
		Image image = new Image(getClass().getResourceAsStream(image_path));
		myDragImage = new DraggableImageView(this, image, ICON_PREF_WIDTH, ICON_PREF_HEIGHT);
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
	
	public List<String> getTags() {
		return myTags;
	}
	
	public void addTag(String tag) {
		myTags.add(tag);
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
	
	public void setBuilding(boolean b) {
		isBuilding = b;
	}
	
	public void setBuildTime(double time) {
		buildTime = time;
	}
	
	public void setBuildCost(String resource, double amount) {
		buildCost.put(resource, amount);
	}

	public ObjectAttributes getObjectAttributesInstance() {
		return myAttributes;
	}
	
	public InteractionManager getInteractionsManagerInstance() {
		return myInteractions;
	}

	public DraggableImageView duplicateImgView() {
		Image image = myDragImage.getImage();
		DraggableImageView imageview = new DraggableImageView(image, myDragImage.getFitWidth(), myDragImage.getFitHeight());
		AuthoringObject newobj = new AuthoringObject(imageview);
		imageview.setAction(newobj);
		return imageview;
	}

	public ObjectLogic getObjectLogic() {
		return myObjectLogic;
	}
	
	public ConditionManager getConditionManager() {
		return myConditionManager;
	}
}