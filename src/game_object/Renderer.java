package game_object;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Rayan
 * Contains data about the game object that will be vital for rendering.
 * Will deal with java fx logic
 */

public class Renderer {
	private String myImageLocation;
	@XStreamOmitField
	private transient ImageView myDisp;
	

	public Renderer(String imageLocation){
		myImageLocation = imageLocation;
		myDisp = new ImageView(new Image(imageLocation));
	}
	
	public ImageView getDisp() {
		return myDisp;
	}
	public void setupImage() {
		myDisp = new ImageView(new Image(myImageLocation));
	}
	public Renderer()
	{
		
	}
}
