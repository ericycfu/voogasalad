package authoring.backend;

import authoring.view.ObjectTeamSelectionScreen;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObjectSelectionImageView extends ImageView {
	
	public ObjectSelectionImageView(AuthoringObject obj, AuthoringController ac) {
		this.setImage(obj.getImage());
		actionImgToScrollPane(obj, ac);
	}
	
	public void actionImgToScrollPane(AuthoringObject obj, AuthoringController ac) {
		this.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				DraggableImageView dragimgview = obj.duplicateImgView();
				ac.getCurrentMap().addToMap(obj, dragimgview);
			}
			ac.updateObject(obj);
		});
	}
}


