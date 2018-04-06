package authoring.view;

import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CreatedObjectsView extends ScrollPane {
	public static final int THUMBNAIL_WIDTH = 200;
	public static final int THUMBNAIL_HEIGHT = 200;
	private CreatedObjects createdobjects;
	
	public CreatedObjectsView(CreatedObjects cb) {
		createdobjects = cb;
		setupBox();
	}
	
	private void setupBox() {
		VBox box = new VBox();
		int size = createdobjects.getSize();
		for (int i=0; i<size; i++) {
			box.getChildren().add(setupIndivBox(createdobjects.getObjectByIndex(i)));
		}
		this.setContent(box);
	}
	
	private VBox setupIndivBox(AuthoringObject obj) {
		VBox box = new VBox();
		box.getChildren().add(extractImage(obj));
		box.getChildren().add(new Text(extractName(obj)));
		return box;
	}
	
	private ImageView extractImage(AuthoringObject obj) {
		ImageView imgview = new ImageView(obj.getImage());
		imgview.setFitWidth(THUMBNAIL_WIDTH);
		imgview.setFitHeight(THUMBNAIL_HEIGHT);
		return imgview;
	}
	
	private String extractName(AuthoringObject obj) {
		return obj.getName();
	}
	
	
	
}
