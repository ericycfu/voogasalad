package authoring.support;

import authoring.backend.AuthoringObject;
import authoring.backend.MapEntity;
import authoring.edit_map.ObjectTeamSelectionScreen;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class DraggableImageView extends ImageView {

//	private static final String TEAM = "Team ";
//	private static final String CSS_STYLE = "image-border-team";
	private double mouseX;
    private double mouseY;
    private MapEntity map_entity;
    private AuthoringObject objBase;

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

    public DraggableImageView(Image image, MapEntity map_entity, double width, double height) {
   		this(image);
   		this.setFitWidth(width);
   		this.setFitHeight(height);
   		this.map_entity = map_entity;
    }
    
    public DraggableImageView(AuthoringObject obj, Image image, double width, double height) {
		this(image, width, height);
		setAction(obj);
    }

    public DraggableImageView(AuthoringObject objBase, MapEntity map_entity, Image image, double width, double height) {
		this(image, map_entity, width, height);
		this.objBase = objBase;
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
		
		this.setOnMouseClicked(ex -> {
			if(ex.isShiftDown() && ex.getClickCount() == 1) {
				new ObjectTeamSelectionScreen(this, obj);
			}
			else if(ex.isControlDown() && ex.getClickCount() == 1) {
				map_entity.removeFromMap(objBase, obj);
			}

		});
		
		updateImageProperties(obj);
    }
    
    public void updateImageProperties(AuthoringObject authoring_object) {
//    	HBox hbox_inner = new HBox();
//    	hbox_inner.setStyle("-fx-border-image-color: #f0f8ff; -fx-border-image-style: solid; -fx-border-width: 5;");
//    	HBox hbox_outer = new HBox();
//        String style_outer = "-fx-border-color: #f0f8ff; -fx-border-width: 10;";
//        hbox_outer.setStyle(style_outer);
//        hbox_inner.getChildren().add(this);
//        hbox_outer.getChildren().add(hbox_inner);
//    	this.getStyleClass().add("image-border-team" + authoring_object.getTeam());
		Tooltip.install(this, new Tooltip("Team " + authoring_object.getTeam()));
    }
    
}