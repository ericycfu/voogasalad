package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class UnitInfoDisplay implements VisualUpdate {
	private static final Paint StrokeColor = Color.BLACK; 
	private static final Image DefaultImage = new Image("images/default_unit.jpg");
	private static final String DefaultHealthMana = "0/0\n0/0";
	private static final String DefaultStatus = "Damage: N/A\nArmor: N/A";
	private double myUnitInfoDisplayXcoor; 
	private double myUnitInfoDisplayYcoor; 
	private double myLength; 
	private double myWidth; 
	private Group myUnitInfoDisplay;
	private Group myUnitProfilePic;
	private Group myUnitStatus;
	private ImageView myCurrentUnitImageView;
	private Rectangle myDisplayFrame;
	private TextArea myHealthManaInfo;
	private TextArea myStatusInfo; 
	private Map<String, Image> UnitProfileMap;
	
	public UnitInfoDisplay(double xcoor, double ycoor, double length, double width) {
		myUnitInfoDisplay = new Group();
		myUnitProfilePic = new Group();
		myUnitStatus = new Group();
		myUnitInfoDisplay.getChildren().add(myUnitProfilePic);
		myUnitInfoDisplay.getChildren().add(myUnitStatus);
		myLength = length;
		myWidth = width;
		initializeDisplayStructure();
	}
	
	private void initializeDisplayStructure() {
		myDisplayFrame = new Rectangle(myLength, myWidth);
		myDisplayFrame.setStroke(StrokeColor);
		initializeProfilePic();
		initializeHealthManaInfo();
		initializeStatusInfo();
		updateProfilePic(DefaultImage);
		updateHealthManaInfo(null);
		updateStatusInfo(null);
	}
	
	private void initializeProfilePic() {
		myCurrentUnitImageView = new ImageView(DefaultImage);
		myCurrentUnitImageView.resize(myWidth/3, myWidth/3);
		myCurrentUnitImageView.setX(myUnitInfoDisplayXcoor + myLength/4 - myCurrentUnitImageView.getBoundsInLocal().getWidth());
		myCurrentUnitImageView.setY(myUnitInfoDisplayYcoor + myWidth/4 - myCurrentUnitImageView.getBoundsInLocal().getWidth());
		myUnitProfilePic.getChildren().add(myCurrentUnitImageView);
	}
	
	private void initializeHealthManaInfo() {
		myHealthManaInfo = new TextArea();
		myHealthManaInfo.setPrefHeight(myWidth/8);
		myHealthManaInfo.setPrefWidth(myLength/4);
		myHealthManaInfo.setLayoutX(myUnitInfoDisplayXcoor + myLength/4 - myHealthManaInfo.getWidth());
		myHealthManaInfo.setLayoutY(myUnitInfoDisplayYcoor + myWidth*0.75 - myHealthManaInfo.getHeight());
		myHealthManaInfo.setEditable(false);
		myUnitInfoDisplay.getChildren().add(myHealthManaInfo);
	}
	
	private void initializeStatusInfo() {
		myStatusInfo = new TextArea();
		myStatusInfo.setPrefHeight(myWidth/2);
		myStatusInfo.setPrefWidth(myLength/3);
		myStatusInfo.setLayoutX(myUnitInfoDisplayXcoor + myLength*0.75 - myStatusInfo.getWidth());
		myStatusInfo.setLayoutY(myUnitInfoDisplayYcoor + myWidth*0.25 - myStatusInfo.getHeight());
		myStatusInfo.setEditable(false);
		myUnitInfoDisplay.getChildren().add(myStatusInfo);
	}
	
	private void updateProfilePic(Image profile) {
		myCurrentUnitImageView.setImage(profile);
	}
	
	private void updateHealthManaInfo(GameObject currentUnit) {
		if (currentUnit==null) {
			myHealthManaInfo.setText(DefaultHealthMana);
		}
		else {
			//myHealthManaInfo.setText(currentUnit.getHealth() + "\n" + currentUnit.getMana());
		}
	}
	
	private void updateStatusInfo(GameObject currentUnit) {
		if (currentUnit==null) {
			myStatusInfo.setText(DefaultStatus);
		}
		else {
			//myStatusInfo.setText("Damage: " + currentUnit.getDamage() + "\n" + "Armor: " + currentUnit.getArmor());
		}
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		updateProfilePic(UnitProfileMap.get(gameObjects.get(0).getName()));
		updateHealthManaInfo(gameObjects.get(0));
		updateStatusInfo(gameObjects.get(0));
	}

	@Override
	public Node getNodes() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
