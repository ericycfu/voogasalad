package authoring.edit_object;

import javafx.geometry.Insets;
import java.util.List;
import authoring.view.AuthoringView;
import conditions.Comparator;
import conditions.ComparatorManager;
import conditions.Condition;
import conditions.ConditionManager;
import gui_elements.factories.ButtonFactory;
import gui_elements.factories.ComboBoxFactory;
import gui_elements.factories.TextFieldFactory;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ComponentAddConditionsScreen implements AuthoringView {
//	private final String PROPERTY_FILENAME = "data/component_add_conditions_screen.properties";
	private static final String TITLE = "Create Component Attributes";
	private static final int[] COLUMNS = {0, 2, 4, 10};
	private static final Insets PANE_INSETS = new Insets(100, 50, 100, 50);
	private static final int SPACING_SMALL = 5;
	private Stage stage;
	private ConditionManager conditionManager;
	private ComparatorManager comparatorManager;
	private Group root;
	private GridPane grid;
	private int currentRow;

	public ComponentAddConditionsScreen(ConditionManager cm) {
		conditionManager = cm;
		comparatorManager = new ComparatorManager();
		currentRow = 0;
//		getProperties();
		initializeScreen();
		newPane();
		showExistingConditions();
		setupButtons();
	}
	
//	private void getProperties() {
//		myResources = ResourceBundle.getBundle(PROPERTY_FILENAME);
//	}
	
	private void showExistingConditions() {
		for (Condition c: conditionManager.getElements()) {
			List<String> info = c.getInfo();
			setLine(info.get(0), info.get(1), info.get(2), c);
		}
		newLine();
	}
	
	private void initializeScreen() {
		root = new Group();
		Scene scene = new Scene(root, PANEL_WIDTH, PANEL_HEIGHT, DEFAULT_BACKGROUND);
		scene.getStylesheets().add(STYLE_PATH);
		stage = new Stage();
		stage.setScene(scene);
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.show();
	}
	
	private void newPane() {
		grid = new GridPane();
		grid.setHgap(SPACING_SMALL / 2);
		grid.setVgap(SPACING_SMALL);
		grid.setPadding(PANE_INSETS);
		root.getChildren().add(grid);
		setTitles();
	}
		
	private void setTitles() {
		addLine(new Label("Attribute"), new Label("Comparator"), new Label("Value"), new Label("Condition Triggered"));
	}
	
	private void setLine(String attribute, String comparator, String value, Condition condition) {
		addLine(TextFieldFactory.makeTextField(attribute),
				ComboBoxFactory.makeComboBox(comparatorSelect(), comparator),
				TextFieldFactory.makeTextField(value),
				ButtonFactory.makeButton(
						"Add Custom Condition",
						e -> new EditCustomConditionsScreen(conditionManager, condition)));
	}
	
	private void newLine() {
		TextField attribute = new TextField();
		@SuppressWarnings("rawtypes")
		ComboBox comparator = comparatorSelect();
		TextField value = new TextField();
		addLine(attribute, comparator, value, ButtonFactory.makeButton(
				"Add Custom Condition",
				e -> new EditCustomConditionsScreen(
						conditionManager, 
						attribute.getText(), 
						extractComparatorId(comparator), 
						value.getText())));
	}
	
	private void addLine(Node a, Node b, Node c, Node d) {
		Node[] array = {a, b, c, d};
		for (int i=0; i<array.length; i++) {
			grid.add(array[i], COLUMNS[i], currentRow);
		}
//		grid.add(a, COLUMNS[0], currentRow);
//		grid.add(b, COLUMNS[1], currentRow);
//		grid.add(c, COLUMNS[2], currentRow);
//		grid.add(d, COLUMNS[3], currentRow);
		currentRow += 1;
	}
	
		
	@SuppressWarnings("rawtypes")
	private ComboBox comparatorSelect() {
		return ComboBoxFactory.makeComboBox(comparatorManager.getComparatorSigns());
	}
	
//	@SuppressWarnings({ "rawtypes", "unchecked"})
//	private ComboBox customConditionSelect() {
//		ComboBox box = ComboBoxFactory.makeComboBox(conditionManager.availableCustomConditions());
//		return ComboBoxFactory.makeComboBox(box, e -> newConditionParameters(box));
//	}
	
//	private void newConditionParameters(ComboBox box) {
//		new EditCustomConditionsScreen((String)box.getValue());
//	}
	
	private void setupButtons() {
		HBox box = new HBox();
//		Button saveButton = ButtonFactory.makeButton("Save", e -> saveConditions()); 
		box.getChildren().addAll(
				ButtonFactory.makeButton("Add Line", e -> newLine()),
				ButtonFactory.makeButton("Save", e -> stage.close()));
		box.setPadding(LINE_INSETS);
		box.setSpacing(SPACING_SMALL);
		root.getChildren().add(box);
		box.toFront();
	}
	
//	private void saveConditions() {
//		conditionManager.clearManager();
//		for (int i=1; i<currentRow; i++) {
//			String attribute = Extractor.extractTextField(findNode(i,COLUMNS[0]));
//			int comparatorId = extractComparatorId(findNode(i,COLUMNS[1]));
//			String value = Extractor.extractTextField(findNode(i,COLUMNS[2]));
//			String conditionString = Extractor.extractComboBox(findNode(i,COLUMNS[3]));
//			int conditionId = conditionManager.createCondition(comparatorId, attribute, value);
//			conditionManager.getCondition(conditionId).addCustomCondition(conditionString);
//		}
//	}
	
	private int extractComparatorId(Node n) {
		if (n instanceof ComboBox) {
			@SuppressWarnings("rawtypes")
			String symbol = (String) ((ComboBox) n).getValue();
			return matchComparatorId(symbol);
		}
		return 0;
	}
	
	private int matchComparatorId(String symbol) {
		for (Comparator c: comparatorManager.getComparators()) {
			if (c.getSign().equals(symbol)) {
				return c.getID();
			}
		}
		return -1;
	}
	
//	@SuppressWarnings("unused")
//	private Node findNode(int row, int col) {
//		Node result = null;
//		for (Node node : grid.getChildren()) {
//			if (node instanceof TextField || node instanceof ComboBox) {
//				if(GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
//		            result = node;
//		            break;
//		        }
//			}
//	    }
//	    return result;
//	}	
}