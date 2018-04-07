package gui_elements.tabs;

import java.util.ArrayList;

import gui_elements.labels.ComponentHealthLabel;
import gui_elements.labels.ComponentImageChooserLabel;
import gui_elements.labels.ComponentNameLabel;
import gui_elements.labels.ComponentProductionCostLabel;
import gui_elements.labels.ComponentTagLabel;
import gui_elements.labels.ComponentVisionRangeLabel;
import gui_elements.labels.InteractionAutomaticLabel;
import gui_elements.labels.InteractionComponentPropertyLabel;
import gui_elements.labels.InteractionComponentTagLabel;
import gui_elements.labels.InteractionQuantityLabel;
import gui_elements.labels.InteractionSelectedLabel;
import gui_elements.labels.InteractionSelectionsLabel;
import gui_elements.labels.InteractionTypeLabel;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class DesignTab extends Tab {

	private final String TAB_TEXT = "Design";
	private VBox interaction_selections_pane;
	private FlowPane interaction_selected_pane;
	private Group design_root;
	
	public DesignTab() {
		System.out.println("Hello");
		initialize();
	}
	
	private void initialize() {
		setGroup();
		setPanes();
		setLabels();
		setComboBoxes();
		setText();
	}
	
	private void setGroup() {
		design_root = new Group();
		this.setContent(design_root);
	}
	
	private void setPanes() {
		interaction_selections_pane = new VBox();
		interaction_selected_pane = new FlowPane();
	}
	
	private void setText() {
		this.setText(TAB_TEXT);
	}
	
	private void setLabels() {
		design_root.getChildren().addAll(new ComponentNameLabel().getLabel(),
										 new ComponentHealthLabel().getLabel(),
										 new ComponentVisionRangeLabel().getLabel(),
										 new ComponentImageChooserLabel().getLabel(),
										 new ComponentProductionCostLabel().getLabel(),
										 new ComponentTagLabel().getLabel(),
										 new InteractionTypeLabel().getLabel(),
										 new InteractionComponentPropertyLabel().getLabel(),
										 new InteractionAutomaticLabel().getLabel(),
										 new InteractionComponentTagLabel().getLabel(),
										 new InteractionQuantityLabel().getLabel(),
										 new InteractionSelectionsLabel().getLabel(),
										 new InteractionSelectedLabel().getLabel());
	}
	
	private void setComboBoxes() {
		
	}
}