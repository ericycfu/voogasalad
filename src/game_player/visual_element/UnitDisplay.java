package game_player.visual_element;

import java.util.List;
import java.util.Map;

import game_object.GameObject;
import game_player.Element;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

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
	private double myInfoDispXcoor;
	private double myInfoDispYcoor;
	private double myActionDispXcoor;
	private double myActionDispYcoor;
	
	public UnitDisplay(double infoDispXcoor, double infoDispYcoor, double actionDispXcoor, double actionDispYcoor, Map<String, List<String>> unitSkills, Map<String, Image> skillImages) {
		initializeUnitDisplayVariables(infoDispXcoor, infoDispYcoor, actionDispXcoor, actionDispYcoor, unitSkills, skillImages);
		initializeUnitDisplayComponents();
	}
	
	private void initializeUnitDisplayVariables(double infoDispXcoor, double infoDispYcoor, double actionDispXcoor, double actionDispYcoor, Map<String, List<String>> unitSkills, Map<String, Image> skillImages) {
		myUnitSkillsMap = unitSkills;
		mySkillImagesMap = skillImages;
		myInfoDispXcoor = infoDispXcoor;
		myInfoDispYcoor = infoDispYcoor;
		myActionDispXcoor = actionDispXcoor;
		myActionDispYcoor = actionDispYcoor;
	}
	
	private void initializeUnitDisplayComponents() {
		myInfoDisp = new UnitInfoDisplay(myInfoDispXcoor, myInfoDispYcoor);
		myActionDisp = new UnitActionDisplay(myActionDispXcoor, myActionDispYcoor, myUnitSkillsMap, mySkillImagesMap);
	}
	
	@Override
	public void update(List<GameObject> selectedGameObjects) {
		myInfoDisp.update(selectedGameObjects);
		myInfoDisp.update(selectedGameObjects);
	}

	@Override
	public Node getNodes() {
		Group group = new Group();
		group.getChildren().add(myActionDisp.getNodes());
		group.getChildren().add(myInfoDisp.getNodes());
		return group;
	}
	
	
}