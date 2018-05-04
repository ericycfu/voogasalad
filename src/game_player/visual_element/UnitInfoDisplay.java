package game_player.visual_element;

import java.util.List;

import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import game_player.GamePlayer;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class UnitInfoDisplay implements VisualUpdate {
	private static final Paint STROKE_COLOR = Color.BLACK; 
	private static final Image DEFAULT_IMAGE = new Image("default_unit.jpg");
	private static final String NAME = "Name";
	private static final String TEAM = "Team";
	private static final String MOVE_SPEED = "Move Speed";
	private static final String DEFAULT_Property_Status = "Name: N/A\nTeam: N/A";
	private static final String DEFAULT_STATUS = "Damage: N/A\nArmor: N/A";
	private static final double PROPERTY_RATIO = 0.25;
	private static final double STATUS_RATIO = 0.75;
	private static final double PROFILE_RATIO = 0.33333;
	private double myHeight; 
	private double myWidth; 
	private GridPane myUnitInfoDisplay;
	private ImageView myCurrentUnitImageView;
	private Rectangle myDisplayFrame;
	private TextArea myPropertyInfo;
	private TextArea myStatusInfo; 
	
	public UnitInfoDisplay(double width, double height) {
		myUnitInfoDisplay = new GridPane();
		myUnitInfoDisplay.setStyle(UnitDisplay.WHITE_BACKGROUND_CSS);
		myWidth = width;
		myHeight = height;
		initializeDisplayStructure();
	}
	
	private void initializeDisplayStructure() {
		myDisplayFrame = new Rectangle(myWidth, myHeight);
		myDisplayFrame.setStroke(STROKE_COLOR);
		myStatusInfo = new TextArea();
		myPropertyInfo = new TextArea();
		initializeProfilePic();
		initializeDisplayComponent(myPropertyInfo, myHeight, myWidth, PROPERTY_RATIO, STATUS_RATIO);
		initializeDisplayComponent(myStatusInfo, myHeight, myWidth, STATUS_RATIO, PROPERTY_RATIO);
		myUnitInfoDisplay.add(myPropertyInfo, 1, 0, 1, 2);
		myUnitInfoDisplay.add(myStatusInfo, 2, 0, 1, 2);
		updateStatusInfo(null);
	}
	
	private void initializeProfilePic() {
		myCurrentUnitImageView = new ImageView(DEFAULT_IMAGE);
		myCurrentUnitImageView.setFitHeight(myHeight);
		myCurrentUnitImageView.setFitWidth(myWidth*PROFILE_RATIO);
		myCurrentUnitImageView.setX(myWidth*PROPERTY_RATIO - myCurrentUnitImageView.getBoundsInLocal().getWidth());
		myCurrentUnitImageView.setY(myHeight*PROPERTY_RATIO - myCurrentUnitImageView.getBoundsInLocal().getWidth());
		myUnitInfoDisplay.add(myCurrentUnitImageView, 0, 0, 1, 1);
	}
	
	private void initializeDisplayComponent(TextArea comp, double height, double width, double xcoorRatio, double ycoorRatio) {
		comp.setPrefHeight(height);
		comp.setPrefWidth(width*PROFILE_RATIO);
		comp.setLayoutX(width*xcoorRatio - comp.getWidth());
		comp.setLayoutY(width*ycoorRatio - comp.getHeight());
		comp.setEditable(false);
	}
	
	private void updateProfilePic(Image profile) {
		myCurrentUnitImageView.setImage(profile);
	}
	
	private void updatePropertyInfo(GameObject currentUnit) {
		if (currentUnit==null) {
			myPropertyInfo.setText(DEFAULT_Property_Status);
		}
		else {
			myPropertyInfo.setText(GamePlayer.EMPTY);
			myPropertyInfo.setText(myPropertyInfo.getText() + NAME + GamePlayer.COLON + currentUnit.getName() + GamePlayer.LINEBREAK);
			myPropertyInfo.setText(myPropertyInfo.getText() + TEAM + GamePlayer.COLON + currentUnit.getOwner().getID() + GamePlayer.LINEBREAK);
			myPropertyInfo.setText(myPropertyInfo.getText() + MOVE_SPEED + currentUnit.getMovementSpeed());
		}
	}
	
	private void updateStatusInfo(GameObject currentUnit) {
		if (currentUnit==null) {
			myStatusInfo.setText(DEFAULT_STATUS);
		}
		else {
			myStatusInfo.setText(GamePlayer.EMPTY);
			try {
				for (String attri : currentUnit.accessLogic().accessAttributes().getAttributeNames()) {
					myStatusInfo.setText(myStatusInfo.getText() + attri + GamePlayer.COLON + currentUnit.accessLogic().accessAttributes().getAttribute(attri) + GamePlayer.LINEBREAK);
				}
			} catch (PropertyNotFoundException | UnmodifiableGameObjectException | IndexOutOfBoundsException e) {
				//DO NOTHING
			}
		}
	}
	
	@Override
	public void update(List<GameObject> gameObjects) {
		if (gameObjects.isEmpty()) {
			updateProfilePic(DEFAULT_IMAGE);
			updatePropertyInfo(null);
			updateStatusInfo(null);
			return;
		}
		updateProfilePic(gameObjects.get(0).getRenderer().getDisp().getImage());
		updatePropertyInfo(gameObjects.get(0));
		updateStatusInfo(gameObjects.get(0));
	}

	@Override
	public Node getNodes() {
		return this.myUnitInfoDisplay;
	}
	
}
