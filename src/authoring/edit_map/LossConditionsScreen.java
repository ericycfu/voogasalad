package authoring.edit_map;

import java.util.Arrays;
import java.util.List;

import authoring.edit_object.ParameterSelect;
import authoring.support.Extractor;
import gui_elements.factories.ComboBoxFactory;
import interactions.CustomComponentParameterFormat;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import scenemanager.EndCondition;
import scenemanager.EndConditionFactory;

public class LossConditionsScreen extends ParameterSelect {
	private EndConditionFactory factory;
	private List<EndCondition> endConditions;
	
	public LossConditionsScreen(List<EndCondition> ec) {
		super();
		endConditions = ec;
		factory = new EndConditionFactory();
		loadSaved();
		addLine();
	}
	
	@Override
	protected void customize() {
		stage.setTitle("Loss Conditions");
	}

	@Override
	protected void addLine() {
		HBox line = new HBox();
		line.getChildren().add(customConditionSelect(line));		
		root.getChildren().add(line);
	}

	@Override
	protected void loadSaved() {
		for (EndCondition c: endConditions) {
			loadLine(c);
		}
	}
	
	private void loadLine(EndCondition c) {
		HBox line = new HBox();
		String conditionName = Extractor.extractConditionName(c).replaceAll("([^_])([A-Z])", "$1 $2");
		line.getChildren().add(customConditionSelect(line, conditionName));
		CustomComponentParameterFormat format = c.getParameterFormat();
		super.loadParameters(line, format);
		root.getChildren().add(line);
	}
	
	private ComboBox customConditionSelect(HBox line) {
		ComboBox box = ComboBoxFactory.makeComboBox(availableEndConditions());
		box.setPrefSize(200, 20);
		return ComboBoxFactory.makeComboBox(box, e -> newConditionParameters(box, line));
	}
	
	private ComboBox customConditionSelect(HBox line, String text) {
		ComboBox box = ComboBoxFactory.makeComboBox(availableEndConditions(), text);
		box.setPrefSize(200, 20);
		return ComboBoxFactory.makeComboBox(box, e -> newConditionParameters(box, line));
	}
	
	private void newConditionParameters(ComboBox box, HBox line) {
		super.clearConditionParameters(line);
		String conditionName = (String) box.getValue();
		EndCondition c = factory.getEndCondition(conditionName.replaceAll("\\s+",""));
		CustomComponentParameterFormat format = c.getParameterFormat();
		super.setParameterFields(line, format);
	}
	
	private List<String> availableEndConditions() {
		String[] array = {
			"All Units Dead",
			"Last Man Standing",
			"Resource Victory"
			};
		return Arrays.asList(array);
	}

	@Override
	protected void clear() {
		endConditions.clear();
	}
	
	@Override
	protected void save(HBox line, ComboBox comboBox) {
		String conditionName = ((String) (comboBox).getValue()).replaceAll("\\s+","");
		if (conditionName != "") {
			EndCondition c = factory.getEndCondition(conditionName);
			endConditions.add(c);
			CustomComponentParameterFormat format = c.getParameterFormat();
			List<String> parameters = format.getParameterList();
			for (int i=0; i<parameters.size(); i++) {
				format.setFieldValue(parameters.get(i), Extractor.extractTextField(line.getChildren().get(i+1)));
			}
		}
	}
}

