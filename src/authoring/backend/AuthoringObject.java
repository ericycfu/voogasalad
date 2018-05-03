package authoring.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import authoring.support.DraggableImageView;
import conditions.ConditionManager;
import game_object.ObjectAttributes;
import game_object.ObjectLogic;
import interactions.InteractionManager;
import javafx.scene.image.Image;

public class AuthoringObject {
	//extends group?
	public static final String TEST_IMAGE = "/images/station.png";
	public static final String TEST_IMAGE_DUVALL= "/images/rcd.png";
	public static final int ICON_PREF_WIDTH = 70;
	public static final int ICON_PREF_HEIGHT = 70;
	public static final int DEFAULT_TEAM = 1;
	@XStreamOmitField
	private transient DraggableImageView myDragImage;
	private List<String> myTags;
	private double myX;
	private double myY;
	private int myTeam;
	private Map<String, Double> buildCosts;
	private ObjectLogic myObjectLogic;
	private MainComponentPropertyManager myMainComponentPropertyManager;
	private ObjectAttributes myAttributes;
	private InteractionManager myInteractions;
	private ConditionManager myConditionManager;
	
	public AuthoringObject() {
		defaultObject();
//		addTestObject();
//		addDuvall();
	}
		
	public AuthoringObject(DraggableImageView img) {
		myDragImage = img;
		myX = 0;
		myY = 0;
		myTeam = DEFAULT_TEAM;
	}
	
	private void defaultObject() {
		myDragImage = null;		
		myX = 0;
		myY = 0;
		myObjectLogic = new ObjectLogic();
		myMainComponentPropertyManager = new MainComponentPropertyManager();
		myAttributes = myObjectLogic.accessAttributes();
		myInteractions = myObjectLogic.accessInteractions();
		myConditionManager = myObjectLogic.accessConditions();
		myTags = myMainComponentPropertyManager.getTags();
		buildCosts = myAttributes.getCosts();
	}
	
//	private void addTestObject() {
//		setImage(TEST_IMAGE);
//		myMainComponentPropertyManager.setName("Station");
//	}
//	
//	private void addDuvall() {
//		setImage(TEST_IMAGE_DUVALL);
//		myMainComponentPropertyManager.setName("Final Boss");
//	}
	
	public Image getImage() {
//		System.out.println("myDragImage: " + myDragImage);
		System.out.println("DragImage's Image: " + myDragImage.getImage());
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
		myMainComponentPropertyManager.setImagePath(image_path);
		Image image = new Image(getClass().getResourceAsStream(image_path));
		myDragImage = new DraggableImageView(this, image, ICON_PREF_WIDTH, ICON_PREF_HEIGHT);
	}
	
	public String getName() {
		return myMainComponentPropertyManager.getName();
	}
	
	public void setName(String name) {
		myMainComponentPropertyManager.setName(name);
	}

	public double getMovementSpeed() {
		return myMainComponentPropertyManager.getMovementSpeed();
	}
	
	public void setMovementSpeed(double movementSpeed) {
		myMainComponentPropertyManager.setMovementSpeed(movementSpeed);
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
	
	public boolean isBuilding() {
		return myMainComponentPropertyManager.isBuilding();
	}
	
	public void setBuilding(boolean b) {
		myMainComponentPropertyManager.setBuilding(b);
	}
	
	public double getBuildTime() {
		return myAttributes.getBuildTime();
	}
	
	public void setBuildTime(double time) {
		myAttributes.setBuildTime(time);
	}
	
	public List<String> getBuildCostResources() {
		List<String> resources = new ArrayList<>();
		for(String resource : buildCosts.keySet()) {
			resources.add(resource);
		}
		return resources;
	}
	
	public List<Double> getBuildCostAmounts() {
		List<Double> amounts = new ArrayList<>();
		for(double amount : buildCosts.values()) {
			amounts.add(amount);
		}
		return amounts;
	}
	
	public void setBuildCost(String resource, double amount) {
		//buildCosts.put(resource, amount);
	}
	
	public MainComponentPropertyManager getMainComponentPropertyManager() {
		return myMainComponentPropertyManager;
	}

	public ObjectLogic getObjectLogic() {
		return myObjectLogic;
	}

	public ObjectAttributes getObjectAttributesInstance() {
		return myAttributes;
	}
	
	public InteractionManager getInteractionsManagerInstance() {
		return myInteractions;
	}
	
	public ConditionManager getConditionManager() {
		return myConditionManager;
	}

//	public DraggableImageView duplicateImgView() {
//		Image image = myDragImage.getImage();
//		DraggableImageView imageview = new DraggableImageView(image, myDragImage.getFitWidth(), myDragImage.getFitHeight());
//		AuthoringObject newobj = new AuthoringObject(imageview);
//		imageview.setAction(newobj);
//		return imageview;
//	}
	
	public AuthoringObject duplicateObj() {
		Image image = myDragImage.getImage();
		DraggableImageView imageview = new DraggableImageView(image, myDragImage.getFitWidth(), myDragImage.getFitHeight());
		AuthoringObject newobj = new AuthoringObject(imageview);
		imageview.setAction(newobj);
		return newobj;
	}
		
	public void resetImageAfterLoad() {
		setImage(myMainComponentPropertyManager.getImagePath());
	}
	
	public String getImagePath() {
		return myMainComponentPropertyManager.getImagePath();
	}
	
	public int getTeam() {
		return myTeam;
	}
	
	public void setTeam(int team) {
		myTeam = team;
	}
}