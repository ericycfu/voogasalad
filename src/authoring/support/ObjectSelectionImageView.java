package authoring.support;

import authoring.backend.AuthoringController;
import authoring.backend.AuthoringObject;
import javafx.scene.image.ImageView;

public class ObjectSelectionImageView extends ImageView {
	
	public ObjectSelectionImageView(AuthoringObject obj, AuthoringController ac) {
		this.setImage(obj.getImage());
		actionImgToScrollPane(obj, ac);
	}
	
	public void actionImgToScrollPane(AuthoringObject objBase, AuthoringController ac) {
		this.setOnMouseClicked(e -> {
			if (e.getClickCount() == 2) {
				AuthoringObject newobj = objBase.duplicateObj(objBase, ac.getCurrentMap());
				ac.getCurrentMap().addToMap(objBase, newobj);
			}
			ac.updateObject(objBase);
		});
	}
}


