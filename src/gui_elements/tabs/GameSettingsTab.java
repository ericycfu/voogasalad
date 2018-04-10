package gui_elements.tabs;


import gui_elements.buttons.AddResourceButton;
import gui_elements.combo_boxes.ChooseAmountComboBox;
import gui_elements.combo_boxes.ChooseResourceComboBox;
import gui_elements.combo_boxes.LossConditionComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.combo_boxes.NumPlayerComboBox;
import gui_elements.labels.ChooseResourceLabel;
import gui_elements.labels.CreateResourceLabel;
import gui_elements.labels.GameSettingsLabel;
import gui_elements.labels.LossConditionLabel;
import gui_elements.labels.NumPlayersLabel;
import gui_elements.labels.StartingAmountLabel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class GameSettingsTab extends Tab {

	private Group design_root;
	private Label game_settings_label, num_players_label, loss_condition_label, 
	create_resource_label, choose_resource_label, starting_amount_label;
	private GridPane setting_pane;
	private MainComboBox num_players_cb, loss_condition_cb, create_resource_cb,
				choose_resource_cb, choose_amount_cb;

	public GameSettingsTab() {
		initialize();
	}

	private void initialize() {
		setText();
		setGroup();
		setPane();
		setLabel();
		setComboBoxes();
		setButtons();
	}
	
	private void setText(){
		this.setText("GAME SETTING");
	}
	
	private void setGroup() {
		design_root = new Group();
		this.setContent(design_root);
		
	}
	
	private void setPane() {
		setting_pane = new GridPane();
		design_root.getChildren().add(setting_pane);
	}
	
	private void setLabel() {
		game_settings_label = new GameSettingsLabel().getLabel();
		num_players_label = new NumPlayersLabel().getLabel();
		loss_condition_label = new LossConditionLabel().getLabel() ;
		create_resource_label = new CreateResourceLabel().getLabel();
		choose_resource_label = new ChooseResourceLabel().getLabel();
		starting_amount_label = new StartingAmountLabel().getLabel();
		
		design_root.getChildren().addAll(game_settings_label, num_players_label,loss_condition_label,
									create_resource_label, choose_resource_label, starting_amount_label);
	}

	private void setComboBoxes() {
		ObservableList<String> num_players_options = 
				FXCollections.observableArrayList(
						"1",
						"2",
						"3"
						);
		num_players_cb = new NumPlayerComboBox();
		num_players_cb.getItems().addAll(num_players_options);
//		num_paleyrs_cb = new ComboBox(num_player_options);
		num_players_cb.setValue("1");
		
		ObservableList<String> loss_condition_options = 
				FXCollections.observableArrayList(
						"1",
						"2",
						"3"
						);
		loss_condition_cb = new LossConditionComboBox();
		loss_condition_cb.setValue("3");
		
		
		choose_resource_cb = new ChooseResourceComboBox();
		choose_resource_cb.setValue("gold");
		ObservableList<String> resource_options = 
				FXCollections.observableArrayList(
						"gold",
						"silver",
						"crystal"
						);
		choose_resource_cb.getItems().addAll(resource_options);
		
		
		choose_amount_cb = new ChooseAmountComboBox();
		choose_amount_cb.setValue("10");
		ObservableList<String> amount_options = 
				FXCollections.observableArrayList(
						"10", "20", "30", "40", "50",
						"60", "70", "80", "90", "100"
						);
		choose_amount_cb.getItems().addAll(amount_options);
		
		design_root.getChildren().addAll(num_players_cb.getComboBox(), 
				loss_condition_cb.getComboBox(), choose_resource_cb.getComboBox(),
				choose_amount_cb.getComboBox());
		


	}
	
	private void setButtons() {
		design_root.getChildren().addAll(new AddResourceButton().getButton());
	}


}
