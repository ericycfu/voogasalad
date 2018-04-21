package authoring.backend;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

public class ConditionStatement {
	private GridPane grid;
	private TextField attributeEnter;
	private ComboBox comparatorEnter;
	private TextField valueEnter;
	private ComboBox conditionEnter;
	public ConditionStatement(GridPane grid, TextField attributeEnter, ComboBox comparatorEnter, 
			TextField valueEnter, ComboBox conditionEnter) {
		this.grid = grid;
		this.attributeEnter = attributeEnter;
		this.comparatorEnter = comparatorEnter;
		this.valueEnter = valueEnter;
		this.conditionEnter = conditionEnter;
	}
	

}
