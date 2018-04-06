package authoring.backend;

import java.util.List;
import game_object.ObjectAttributes;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import interactions.Interaction;
import javafx.scene.image.Image;
public class AuthoringObject {
	public static final String TEST_IMAGE = "/images/station.png";
	public static final String TEST_IMAGE2 = "./src/resources/images/station.png";
	private Image myImage;
	private String myName;
	private ObjectLogic myObjectLogic;
	private ObjectAttributes myAttributes;
	private List<Interaction> myInteractions;
	
	public AuthoringObject() {
		defaultObject();
		testObject();
	}
	
	private void defaultObject() {
		myImage = null;
		myName = "";
		myObjectLogic = new ObjectLogic();
		myAttributes = myObjectLogic.accessAttributes();
		myInteractions = myObjectLogic.accessInteractions();
	}
	
	private void testObject() {
		myImage = new Image(getClass().getResourceAsStream(TEST_IMAGE));
		myName = "Station";
//		myAttributes.CreateAttribute("Health");
//		try {
//			myAttributes.SetAttributeValue("Health", 50);
//		} catch (PropertyNotFoundException e) {
//			myAttributes.CreateAttribute("Health");
//			System.err.print("Retry setting health");
//		}
//		System.out.print(myAttributes.toString());
		myInteractions.add(null);
		System.out.print(myInteractions.toString());
	}
	
	public Image getImage() {
		return myImage;
	}
	
	public String getName() {
		return myName;
	}
}

