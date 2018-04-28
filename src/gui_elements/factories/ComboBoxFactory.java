package gui_elements.factories;

import java.awt.event.ActionListener;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.event.EventHandler;

public class ComboBoxFactory {
	public static ComboBox makeComboBox(List<String> list) {
		ComboBox<String> box = new ComboBox();
		ObservableList<String> observableList = FXCollections.observableArrayList(list);
		box.setItems(observableList);
		return box;
	}
	
	public static ComboBox makeComboBox(List<String> list, EventHandler<ActionEvent> e) {
		ComboBox<String> box = makeComboBox(list);
		box.setOnAction(e);
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
	
	public static ComboBox makeComboBox(ComboBox box, EventHandler<ActionEvent> e) {
		box.setOnAction(e);
		return box;
	}
	
	public static void updateComboBox(ComboBox box, List<String> list) {
		ObservableList<String> observableList = FXCollections.observableArrayList(list);
		box.setItems(observableList);
	}

	
}
