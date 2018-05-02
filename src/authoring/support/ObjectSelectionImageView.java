package authoring.support;

import authoring.backend.AuthoringController;
import authoring.backend.AuthoringObject;
import authoring.edit_map.ObjectTeamSelectionScreen;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ObjectSelectionImageView extends ImageView {
	
	public ObjectSelectionImageView(AuthoringObject obj, AuthoringController ac) {
		this.setImage(obj.getImage());
		actionImgToScrollPane(obj, ac);
	}
	
	public void actionImgToScrollPane(AuthoringObject objBase, AuthoringController ac) {
		this.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
//				DraggableImageView dragimgview = obj.duplicateImgView();
				AuthoringObject newobj = objBase.duplicateObj();
				ac.getCurrentMap().addToMap(objBase, newobj);
			}
			ac.updateObject(objBase);
		});
	}
}


