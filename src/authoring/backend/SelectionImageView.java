package authoring.backend;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SelectionImageView extends ImageView {
	public SelectionImageView(AuthoringObject obj) {
		super();
		this.setImage(obj.getImage());
	}
	
	public SelectionImageView(AuthoringObject obj, MapEntity pane) {
		this(obj);
		actionImgToScrollPane(obj, pane);
	}
	
	public void actionImgToScrollPane(AuthoringObject obj, MapEntity pane) {
		this.setOnMouseClicked(e -> {
			DraggableImageView dragimgview = new DraggableImageView(obj, this.getImage());
			pane.getChildren().add(dragimgview);
		});
	}
}
