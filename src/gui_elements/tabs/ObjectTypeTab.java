package gui_elements.tabs;

import javafx.scene.control.Tab;
import javafx.scene.Node;

public class ObjectTypeTab extends Tab {
	public ObjectTypeTab(String s) {
		this.setText(s);
		this.getStyleClass().add("tab_title");
	}
	
	public ObjectTypeTab(String s, Node content) {
		this(s);
		this.setContent(content);
	}
}
