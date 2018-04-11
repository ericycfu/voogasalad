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
    
    public DraggableImageView(AuthoringObject obj, Image image) {
    		super(image);
    		this.setOnMousePressed(e -> {
        		mouseX = e.getSceneX();
        		mouseY = e.getSceneY();
        });
    		
    		this.setOnMouseDragged(event -> {
    			   double deltaX = event.getSceneX() - mouseX ;
    			   double deltaY = event.getSceneY() - mouseY ;
    			   obj.changeX(obj.getX() + deltaX);
    			   obj.changeY(obj.getY() + deltaY);
    			   mouseX = event.getSceneX();
    			   mouseY = event.getSceneY();
    			   
    		});
    }
}
