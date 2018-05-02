package authoring.edit_object;

import java.util.ArrayList;
import java.util.List;
import authoring.support.Extractor;
import authoring.view.AuthoringView;
import conditions.Condition;
import conditions.ConditionManager;
import conditions.CustomCondition;
import gui_elements.factories.ComboBoxFactory;
import interactions.CustomComponentParameterFormat;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class EditCustomConditionsScreen extends ParameterSelect implements AuthoringView {
//	private static final String PROPERTIES_PATH = "/data/CustomConditions.properties";
	private ConditionManager cm;
	private Condition condition;
	
	public EditCustomConditionsScreen(ConditionManager cm, Condition condition) {
		super();
		this.cm = cm;
		this.condition = condition;
		loadSaved();
		addLine();
	}
	
	public EditCustomConditionsScreen(ConditionManager cm, String attribute, int comparator, String value) {
		super();
		this.cm = cm;
		initializeCondition(attribute, comparator, value);
		addLine();
	}
	
	@Override
	protected void customize() {
		stage.setTitle("Edit Custom Conditions");
	}
	
	@Override
	protected void loadSaved() {
		for (CustomCondition c: condition.getCustomConditions()) {
			loadLine(c);
		}
	}
	
	private void initializeCondition(String attribute, int comparator, String value) {
		int id = cm.createCondition(comparator, attribute, value);
		condition = cm.getCondition(id);
	}
	
	private void loadLine(CustomCondition c) {
		HBox line = new HBox();
		String conditionName = Extractor.extractConditionName(c);
		line.getChildren().add(customConditionSelect(line, conditionName));
		CustomComponentParameterFormat format = c.getParameterFormat();
		super.loadParameters(line, format);
		root.getChildren().add(line);
	}
	
	@Override
	protected void addLine() {
		HBox line = new HBox();
		line.getChildren().add(customConditionSelect(line));
		root.getChildren().add(line);
	}
	
	private ComboBox customConditionSelect(HBox line) {
		ComboBox box = ComboBoxFactory.makeComboBox(availableCustomConditions());
		box.setPrefSize(120, 20);
		return ComboBoxFactory.makeComboBox(box, e -> newConditionParameters(box, line));
	}
	
	private ComboBox customConditionSelect(HBox line, String text) {
		ComboBox box = ComboBoxFactory.makeComboBox(availableCustomConditions(), text);
		box.setPrefSize(120, 20);
		return ComboBoxFactory.makeComboBox(box, e -> newConditionParameters(box, line));
	}
	
	private void newConditionParameters(ComboBox box, HBox line) {
		super.clearConditionParameters(line);
		String conditionName = (String) box.getValue();
		Condition c = new Condition(0, 0, "", "");
		CustomCondition customCondition = c.generateCustomCondition(conditionName);
		CustomComponentParameterFormat format = customCondition.getParameterFormat();
		super.setParameterFields(line, format);
	}
	
	private List<String> availableCustomConditions() {
		List<String> list = new ArrayList<>();
		list.add("Death");
		list.add("Upgrade");
		return list;
	}
	
	@Override
	protected void clear() {
		condition.getCustomConditions().clear();
	}
	
	@Override
	protected void save(HBox line, ComboBox comboBox) {
		String conditionName = (String) (comboBox.getValue());
		if (conditionName.length() > 0) {
			CustomCondition customCondition = condition.generateCustomCondition(conditionName);
			condition.addCustomCondition(customCondition);
			CustomComponentParameterFormat format = customCondition.getParameterFormat();
			List<String> parameters = format.getParameterList();
			for (int i=0; i<parameters.size(); i++) {
				format.setFieldValue(parameters.get(i), Extractor.extractTextField(line.getChildren().get(i+1)));
			}
		}
	}
}

