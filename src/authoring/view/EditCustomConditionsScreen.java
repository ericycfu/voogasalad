package authoring.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import conditions.ConditionManager;
import gui_elements.factories.ComboBoxFactory;
import gui_elements.factories.TextFieldFactory;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditCustomConditionsScreen implements AuthoringView {
	private static final String PROPERTIES_PATH = "/data/CustomConditions.properties";
	private VBox root;
	public EditCustomConditionsScreen() {
		initializeScene();
		setContent();
//		addLine();
//		root.getChildren().add(new Button());
	}
	
	private void initializeScene() {
		root = new VBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		Scene scene = new Scene (root, PANEL_WIDTH/2, PANEL_HEIGHT/2, DEFAULT_BACKGROUND);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Edit Custom Conditions");
		stage.setResizable(false);
		stage.show();
	}
	
	private void setContent() {
		root.getChildren().add(new Button());
		addLine();
	}
	
	private void addLine() {
		HBox line = new HBox();
		line.getChildren().add(customConditionSelect(line));
		root.getChildren().add(line);
	}
	
	private ComboBox customConditionSelect(HBox line) {
		ComboBox box = ComboBoxFactory.makeComboBox(availableCustomConditions());
		return ComboBoxFactory.makeComboBox(box, e -> newConditionParameters(box, line));
	}
	
	private void newConditionParameters(ComboBox box, HBox line) {
		String conditionName = (String) box.getValue();
		
		line.getChildren().add();
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
		
	
}
