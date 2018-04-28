package gui_elements.factories;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ComboBoxFactory {
	public static ComboBox makeComboBox(List<String> list) {
		ComboBox box = new ComboBox();
		ObservableList<String> observableList = FXCollections.observableArrayList(list);
		box.setItems(observableList);
		return box;
	}
	
	public static ComboBox makeComboBox(List<String> list, String text) {
		ComboBox box = makeComboBox(list);
		box.setValue(text);
		return box;
	}
	
	public static ComboBox makeComboBox(ComboBox box, String text) {
		box.setValue(text);
		return box;
	}
}
