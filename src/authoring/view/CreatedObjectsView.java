package authoring.view;

import authoring.backend.AuthoringController;
import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.backend.DraggableImageView;
import authoring.backend.DraggableScrollPane;
import authoring.backend.MapEntity;
import authoring.backend.SelectionImageView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import observables.Listener;

public class CreatedObjectsView extends ScrollPane implements AuthoringView, Listener {
	public static final int THUMBNAIL_WIDTH = 200;
	public static final int THUMBNAIL_HEIGHT = 200;
	private CreatedObjects createdobjects;
	private MapEntity map;
	
	public CreatedObjectsView(AuthoringController ac, CreatedObjects cb) {
		createdobjects = cb;
		map = ac.getMap();
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
	
	private SelectionImageView extractImage(AuthoringObject obj) {
		SelectionImageView imgview = new SelectionImageView(obj, map);
		imgview.setFitWidth(THUMBNAIL_WIDTH);
		imgview.setFitHeight(THUMBNAIL_HEIGHT);
//		this.addDropOutHandling(obj, imgview);
		return imgview;
	}
	
	private String extractName(AuthoringObject obj) {
		return obj.getName();
	}

	@Override
	public void update() {
		setupBox();
		
	}
	
}
