package game_player.visual_element;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
    The class that creates a generic move button
    @author Eddie Yang
*/
public class MoveButton extends Button {

	public MoveButton(String direction, BooleanProperty hovered, double x, double y) {
		setGraphic(new ImageView(new Image("arrow_" + direction + ".png")));
		setOnMouseEntered(e -> hovered.setValue(true));
		setOnMouseExited(e -> hovered.setValue(false));
		setLayoutX(x);
		setLayoutY(y);
	}
}
