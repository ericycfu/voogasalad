package gui_elements.tabs;

import authoring.view.CreatedObjectsView;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.Node;

public class ObjectTypeTab extends Tab {
	public ObjectTypeTab(String s) {
		this.setText(s);
	}
	
	public ObjectTypeTab(String s, Node content) {
		this(s);
		this.setContent(content);
	}
}
