package game_object;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import transform_library.Vector2;

/**
 * 
 * @author Rayan
 * Contains data about the game object that will be vital for rendering.
 * Will deal with java fx logic
 */

public class Renderer {
	
	private ImageView myDisp;
	

	public Renderer(Image img){
		myDisp = new ImageView(img);
	}
	
	public ImageView getDisp() {
		return myDisp;
	}

	public Renderer()
	{
		
	}
}
