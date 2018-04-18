package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * 
 * @author Siyuan Chen, Frank Yin
 *
 */
public class UnitDisplay implements VisualUpdate {
	private UnitInfoDisplay myInfoDisp;
	private UnitActionDisplay myActionDisp;
	private Map<String, List<String>> myUnitSkillsMap;
	private Map<String, Image> mySkillImagesMap;
	private Group myUnitDisplay;
	
	public UnitDisplay(double infoDispWidth, double infoDispHeight, double actionDispWidth, double actionDispHeight, Map<String, List<String>> unitSkills, Map<String, Image> skillImages) {
		initializeUnitDisplayComponents(infoDispWidth, infoDispHeight, actionDispWidth, actionDispHeight, unitSkills, skillImages);
	}
	
	private void initializeUnitDisplayComponents(double infoDispWidth, double infoDispHeight, double actionDispWidth, double actionDispHeight, Map<String, List<String>> unitSkills, Map<String, Image> skillImages) {
		myUnitDisplay = new Group();
		myUnitSkillsMap = unitSkills;
		mySkillImagesMap = skillImages;
		myInfoDisp = new UnitInfoDisplay(infoDispWidth, infoDispHeight);
		myActionDisp = new UnitActionDisplay(actionDispWidth, actionDispHeight, myUnitSkillsMap, mySkillImagesMap);
		myUnitDisplay.getChildren().add(myInfoDisp.getNodes());
		Node actionDisp = myActionDisp.getNodes();
		actionDisp.setLayoutX(infoDispWidth);
		System.out.println(actionDisp);
		myUnitDisplay.getChildren().add(actionDisp);
	}
	
	@Override
	public void update(List<GameObject> selectedGameObjects) {
		myInfoDisp.update(selectedGameObjects);
		myActionDisp.update(selectedGameObjects);
	}

	@Override
	public Node getNodes() {
		return myUnitDisplay;
	}
	
	public UnitActionDisplay getUnitActionDisp() {
		return myActionDisp;
	}
	
	
}
