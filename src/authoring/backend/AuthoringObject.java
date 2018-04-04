package authoring.backend;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import game_object.ObjectAttributes;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import interactions.Interaction;
import javafx.scene.image.Image;

public class AuthoringObject {
	public static final String TEST_IMAGE = "images/station.png";
	public static final String TEST_IMAGE_2 = "";
	private Image myImage;
	private ObjectLogic myObjectLogic;
	private ObjectAttributes myAttributes;
	private List<Interaction> myInteractions;
	
	public AuthoringObject() {
		defaultObject();
		testObject();
	}
	
	private void defaultObject() {
		myImage = null;
		myObjectLogic = new ObjectLogic();
		myAttributes = myObjectLogic.accessAttributes();
		myInteractions = myObjectLogic.accessInteractions();
	}
	
	private void testObject() {
		myImage = new Image(getClass().getResourceAsStream(TEST_IMAGE));
		// OBJECT ATTRIBUTES: INITIALIZE MAP OF ATTRIBUTES
//		myAttributes.CreateAttribute("Health");
//		try {
//			myAttributes.SetAttributeValue("Health", 50);
//		} catch (PropertyNotFoundException e) {
//			myAttributes.CreateAttribute("Health");
//			System.err.print("Retry setting health");
//		}
		myInteractions.add(null);
//		System.out.print(myAttributes.toString());
		System.out.print(myInteractions.toString());

	}
	
	
}
