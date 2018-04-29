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
	public final static double TEMP_OPACITY = 0.5;
	public final static double NORMAL_OPACITY = 1;

	@XStreamOmitField
	private transient ImageView myDisp;
	

	public Renderer(String imageLocation){
		myImageLocation = imageLocation;
		myDisp = new ImageView(new Image(imageLocation));
	}
	
	public Renderer(Renderer other)
	{
		this.myImageLocation = other.myImageLocation;
		setupImage();
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
