package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class UnitInfoDisplay implements VisualUpdate {
	private static final Paint StrokeColor = Color.BLACK; 
	private static final Image DefaultImage = new Image("default_unit.jpg");
	private static final String DefaultHealthMana = "0/0\n0/0";
	private static final String DefaultStatus = "Damage: N/A\nArmor: N/A";
	private double myHeight; 
	private double myWidth; 
	private GridPane myUnitInfoDisplay;
	private ImageView myCurrentUnitImageView;
	private Rectangle myDisplayFrame;
	private TextArea myHealthManaInfo;
	private TextArea myStatusInfo; 
	
	public UnitInfoDisplay(double width, double height) {
		myUnitInfoDisplay = new GridPane();
		myUnitInfoDisplay.setStyle("-fx-background-color: #FFFFFF;");
		myWidth = width;
		myHeight = height;
		initializeDisplayStructure();
	}
	
	private void initializeDisplayStructure() {
		myDisplayFrame = new Rectangle(myWidth, myHeight);
		myDisplayFrame.setStroke(StrokeColor);
		initializeProfilePic();
		initializeHealthManaInfo();
		initializeStatusInfo();
		updateStatusInfo(null);
	}
	
	private void initializeProfilePic() {
		myCurrentUnitImageView = new ImageView(DefaultImage);
		myCurrentUnitImageView.setFitHeight(myHeight);
		myCurrentUnitImageView.setFitWidth(myWidth/3);
		myCurrentUnitImageView.setX(myWidth/4 - myCurrentUnitImageView.getBoundsInLocal().getWidth());
		myCurrentUnitImageView.setY(myHeight/4 - myCurrentUnitImageView.getBoundsInLocal().getWidth());
		myUnitInfoDisplay.add(myCurrentUnitImageView, 0, 0, 1, 1);
	}
	
	private void initializeHealthManaInfo() {
		myHealthManaInfo = new TextArea();
		myHealthManaInfo.setPrefHeight(myHeight);
		myHealthManaInfo.setPrefWidth(myWidth/3);
		myHealthManaInfo.setLayoutX(myWidth/4 - myHealthManaInfo.getWidth());
		myHealthManaInfo.setLayoutY(myHeight*(3/4) - myHealthManaInfo.getHeight());
		myHealthManaInfo.setEditable(false);
		myUnitInfoDisplay.add(myHealthManaInfo, 1, 0, 1, 2);
	}
	
	private void initializeStatusInfo() {
		myStatusInfo = new TextArea();
		myStatusInfo.setPrefHeight(myHeight);
		myStatusInfo.setPrefWidth(myWidth/3);
		myStatusInfo.setLayoutX(myWidth*(3/4) - myStatusInfo.getWidth());
		myStatusInfo.setLayoutY(myWidth/4 - myStatusInfo.getHeight());
		myStatusInfo.setEditable(false);
		myUnitInfoDisplay.add(myStatusInfo, 2, 0, 1, 2);
	}
	
	private void updateProfilePic(Image profile) {
		myCurrentUnitImageView.setImage(profile);
	}
	
	private void updateHealthManaInfo(GameObject currentUnit) {
		if (currentUnit==null) {
			myHealthManaInfo.setText(DefaultHealthMana);
		}
		else {
			try {
				String temp = currentUnit.accessLogic().accessAttributes().getAttributeNames().get(0);
				String temp2 = currentUnit.accessLogic().accessAttributes().getAttributeNames().get(1);
				myHealthManaInfo.setText(temp + ": " + currentUnit.accessLogic().accessAttributes().getAttribute(temp)
						+ "\n" + temp2 + ": " + currentUnit.accessLogic().accessAttributes().getAttribute(temp2));
			} catch (PropertyNotFoundException | UnmodifiableGameObjectException | IndexOutOfBoundsException e) {
				//DO NOTHING
			}
		}
		
	}
	
	private void updateStatusInfo(GameObject currentUnit) {
		if (currentUnit==null) {
			myStatusInfo.setText(DefaultStatus);
		}
		else {
			try {
				String temp = currentUnit.accessLogic().accessAttributes().getAttributeNames().get(2);
				String temp2 = currentUnit.accessLogic().accessAttributes().getAttributeNames().get(3);
				myStatusInfo.setText(temp + ": " + currentUnit.accessLogic().accessAttributes().getAttribute(temp)
						+ "\n" + temp2 + ": " + currentUnit.accessLogic().accessAttributes().getAttribute(temp2));
			} catch (PropertyNotFoundException | UnmodifiableGameObjectException | IndexOutOfBoundsException e) {
				//DO NOTHING
			}
		}
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		if (gameObjects.isEmpty()) {
			updateProfilePic(DefaultImage);
			updateHealthManaInfo(null);
			updateStatusInfo(null);
			return;
		}
		updateProfilePic(gameObjects.get(0).getRenderer().getDisp().getImage());
		updateHealthManaInfo(gameObjects.get(0));
		updateStatusInfo(gameObjects.get(0));
	}

	@Override
	public Node getNodes() {
		return this.myUnitInfoDisplay;
	}
	
}
