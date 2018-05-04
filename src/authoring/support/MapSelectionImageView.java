package authoring.support;

import authoring.backend.AuthoringController;
import authoring.backend.MapEntity;
import authoring.view.DraggableScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapSelectionImageView extends ImageView {
	
	public MapSelectionImageView(MapEntity map, DraggableScrollPane dragscroll, AuthoringController ac) {
		String imagepath = map.getImagePath();
		this.setImage(new Image(getClass().getResourceAsStream(imagepath)));
		actionMapToScrollPane(map, dragscroll, ac);
	}
	
	public void actionMapToScrollPane(MapEntity map, DraggableScrollPane dragscroll, AuthoringController ac) {
		this.setOnMouseClicked(e -> {
//			dragscroll.setContent(map);
			ac.getScroll().setContent(map);
			ac.updateMap(map);
		});
	}
}
