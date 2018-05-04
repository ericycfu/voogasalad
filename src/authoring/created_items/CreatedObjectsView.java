package authoring.created_items;

import authoring.backend.AuthoringController;
import authoring.backend.AuthoringObject;
import authoring.backend.CreatedObjects;
import authoring.support.ObjectSelectionImageView;
import authoring.view.AuthoringView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import observables.Listener;

public class CreatedObjectsView extends ScrollPane implements AuthoringView, Listener {
	public static final int THUMBNAIL_WIDTH = 150;
	public static final int THUMBNAIL_HEIGHT = 150;
	private static final String STYLE_PATH = "gui_elements/css/object_team_image.css";
	private AuthoringController authorcontroller;
	private CreatedObjects createdobjects;
	
	public CreatedObjectsView(AuthoringController ac, CreatedObjects cb) {
		authorcontroller = ac;
		createdobjects = cb;
		cb.addListener(this);
		setupBox();
		this.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupBox() {
		VBox box = new VBox();
		int size = createdobjects.getSize();
		for (int i=0; i<size; i++) {
			box.getChildren().add(setupIndivBox(createdobjects.getObjectByIndex(i)));
		}
		if (size != 0) {
			this.setContent(box);
		}
		return;
	}
	
	private VBox setupIndivBox(AuthoringObject obj) {
		VBox box = new VBox();
		box.getChildren().add(extractImage(obj));
		box.getChildren().add(new Text(extractName(obj)));
		box.setPadding(CreatedMapsView.CREATED_VIEW_INSETS);
		return box;
	}
	
	private ObjectSelectionImageView extractImage(AuthoringObject obj) {
		ObjectSelectionImageView imgview = new ObjectSelectionImageView(obj, authorcontroller);
		imgview.setFitWidth(THUMBNAIL_WIDTH);
		imgview.setFitHeight(THUMBNAIL_HEIGHT);
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