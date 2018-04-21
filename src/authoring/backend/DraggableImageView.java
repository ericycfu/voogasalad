package authoring.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class DraggableImageView extends ImageView {
	private double mouseX;
    private double mouseY;
    public DraggableImageView(Image image) {
    		super();
    		this.setImage(image);
    }
    
    public DraggableImageView(AuthoringObject obj) {
    		super();
    		setAction(obj);
    }

    public DraggableImageView(AuthoringObject obj, Image image) {
    		this(image);
    		setAction(obj);
    		
    }
        
    public DraggableImageView(Image image, double width, double height) {
    		this(image);
    		this.setFitWidth(width);
    		this.setFitHeight(height);
    }
    
    public DraggableImageView(AuthoringObject obj, Image image, double width, double height) {
		this(image, width, height);
		setAction(obj);
}
        
    public void setAction(AuthoringObject obj) {
	    	this.setOnMousePressed(e -> {
	    		mouseX = e.getSceneX();
	    		mouseY = e.getSceneY();
	    });
			
		this.setOnMouseDragged(event -> {
			   double deltaX = event.getSceneX() - mouseX ;
			   double deltaY = event.getSceneY() - mouseY ; 		
			   obj.changeX(obj.getX() + deltaX);
			   obj.changeY(obj.getY() + deltaY);
			   obj.updateImage();
			   mouseX = event.getSceneX();
			   mouseY = event.getSceneY();
		});
    }
    
}
