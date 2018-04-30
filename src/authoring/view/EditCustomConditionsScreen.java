package authoring.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import authoring.backend.Extractor;
import conditions.Condition;
import conditions.ConditionManager;
import conditions.CustomCondition;
import game_object.PropertyNotFoundException;
import gui_elements.factories.ButtonFactory;
import gui_elements.factories.ComboBoxFactory;
import gui_elements.factories.TextFieldFactory;
import interactions.CustomComponentParameterFormat;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditCustomConditionsScreen implements AuthoringView {
	private static final String PROPERTIES_PATH = "/data/CustomConditions.properties";
	private ConditionManager cm;
	private Stage stage;
	private VBox root;
	private Condition condition;
	
	public EditCustomConditionsScreen(ConditionManager cm, Condition condition) {
		this.cm = cm;
		this.condition = condition;
		initializeScene();
		initializeButtons();
		loadSaved();
		addLine();
		
	}
	
	public EditCustomConditionsScreen(ConditionManager cm, String attribute, int comparator, String value) {
		this.cm = cm;
		initializeScene();
		initializeCondition(attribute, comparator, value);
		initializeButtons();
		addLine();

	}
	
	private void initializeScene() {
		root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		Scene scene = new Scene (root, PANEL_WIDTH, PANEL_HEIGHT/2, DEFAULT_BACKGROUND);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Edit Custom Conditions");
		stage.setResizable(false);
		stage.show();
	}
	
	private void loadSaved() {
		for (CustomCondition c: condition.getCustomConditions()) {
			System.out.print(c.toString());
			loadLine(c);
		}
	}
	
	private void initializeCondition(String attribute, int comparator, String value) {
		int id = cm.createCondition(comparator, attribute, value);
		condition = cm.getCondition(id);
	}
	
	private void initializeButtons() {
//		HBox box = new HBox();
		root.getChildren().add(ButtonFactory.makeButton("Add Custom Function", e -> addLine()));
		root.getChildren().add(ButtonFactory.makeButton("Save", e -> saveAll()));
//		root.getChildren().add(box);
//		startRow += 1;
//		currRow += 1;
	}
	
	private void loadLine(CustomCondition c) {
		HBox line = new HBox();
		String conditionName = c.toString().split(".")[1].split("@")[0];
		line.getChildren().add(customConditionSelect(line, conditionName));
		CustomComponentParameterFormat format = c.getParameterFormat();
		List<String> parameters = format.getParameterList();
		for (String p: parameters) {
			String value;
			try {
				value = format.getParameterValue(p);
			} catch (PropertyNotFoundException e) {
				value = "";
			}
			line.getChildren().add(TextFieldFactory.makeTextField(value, p));
		}
		root.getChildren().add(line);
	}
	private void addLine() {
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
		clearConditionParameters(line);
		String conditionName = (String) box.getValue();
		Condition c = new Condition(0, 0, "", "");
		CustomCondition customCondition = c.addCustomCondition(conditionName);
		CustomComponentParameterFormat format = customCondition.getParameterFormat();
		List<String> parameters = format.getParameterList();
		for (int i=0; i<parameters.size(); i++) {
			line.getChildren().add(addParameterField(parameters.get(i)));
		}
	}
	
	private void clearConditionParameters(HBox line) {
		for (Node node: line.getChildren()) {
			if (node instanceof TextField) {
				line.getChildren().remove(node);
			}
		}
	}
	
	private TextField addParameterField(String text) {
		return TextFieldFactory.makeTextFieldPrompt(text);
	}

		
//	private List<String> availableCustomConditions() {
//		Properties properties = new Properties();
//		InputStream input = this.getClass().getResourceAsStream(PROPERTIES_PATH);
//		try {
//			properties.load(input);
//			List<String> list = new ArrayList<>();
//			for (Object c: properties.values()) {
//				list.add((String) c);
//			}
//			return list;
//		} catch (IOException e) {
//			System.err.print("Invalid properties file");
//		}
//		return null;
//	}
	
	private List<String> availableCustomConditions() {
		List<String> list = new ArrayList<>();
		list.add("Death");
		list.add("Upgrade");
		return list;
	}
	
	private void saveAll() {
//		cm.clearManager();
		for (Node node: root.getChildren()) {
			if (node instanceof HBox) {
				extractInfo((HBox) node);
			}
		}
		stage.close();
	}
	
	private void extractInfo(HBox box) {
		Node nodeOne = box.getChildren().get(0);
		String conditionName = "";
		if (nodeOne instanceof ComboBox) {
			conditionName = (String) ((ComboBox) nodeOne).getValue();
		}
		CustomCondition customCondition = condition.addCustomCondition(conditionName);
		CustomComponentParameterFormat format = customCondition.getParameterFormat();
		List<String> parameters = format.getParameterList();
		for (int i=0; i<parameters.size(); i++) {
			format.setFieldValue(parameters.get(i), Extractor.extractTextField(box.getChildren().get(i+1)));
		}
	}
	
	
	
}
