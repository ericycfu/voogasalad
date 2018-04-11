package authoring.backend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SelectionImageView extends ImageView {
	public SelectionImageView(AuthoringObject obj) {
		super();
	}
	
	public SelectionImageView(AuthoringObject obj, Pane pane) {
		this(obj);
		actionImgToScrollPane(obj, pane);
	}
	
	public void actionImgToScrollPane(AuthoringObject obj, Pane pane) {
		this.setOnMouseClicked(e -> {
			DraggableImageView dragimgview = new DraggableImageView(obj, this.getImage());
			pane.getChildren().add(dragimgview);
		});
	}
}
