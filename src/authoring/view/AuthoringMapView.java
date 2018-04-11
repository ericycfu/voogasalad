package authoring.view;

import authoring.backend.DraggableScrollPane;
import javafx.scene.control.ScrollPane;

public class AuthoringMapView extends ScrollPane implements AuthoringView {
	public AuthoringMapView() {
		this.setPrefSize(500, 800);
	}
}
