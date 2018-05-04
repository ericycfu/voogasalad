package gui_elements.tabs;

import gui_elements.buttons.AddResourceButton;
import gui_elements.buttons.ResourceApplyButton;
import gui_elements.combo_boxes.ChooseAmountComboBox;
import gui_elements.combo_boxes.ChooseAmountComboBox1;
import gui_elements.combo_boxes.ChooseResourceComboBox;
import gui_elements.combo_boxes.ChooseResourceComboBox1;
import gui_elements.combo_boxes.LossConditionComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.combo_boxes.NumPlayerComboBox;
import gui_elements.labels.ChooseResourceLabel;
import gui_elements.labels.ChooseResourceLabel1;
import gui_elements.labels.CreateResourceLabel;
import gui_elements.labels.GameSettingsLabel;
import gui_elements.labels.LossConditionLabel;
import gui_elements.labels.NumPlayersLabel;
import gui_elements.labels.StartingAmountLabel;
import gui_elements.labels.StartingAmountLabel1;
import gui_elements.panes.MainPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class GameSettingsTab extends Tab {

	private Group design_root;
	private MainPane resource_name_pane, resource_amount_pane;
//	private ObjectAttributes objAttr;
//	private Stage stage;

	public GameSettingsTab() {
		//		ObjectAttributes objAttr) {
		//		this.objAttr = objAttr;
		initialize();
	}

	private void initialize() {
		setText();
		setGroup();
		setPane();
		setLabel();
		setComboBoxes();
	}

	private void setText(){
		this.setText("Game Setting");
	}

	private void setGroup() {
		design_root = new Group();
		this.setContent(design_root);

	}

	private void setPane() {
		GridPane setting_pane = new GridPane();

		//		resource_name_pane = new ResourceNamePane(objAttr);
		//		resource_amount_pane = new ResourceAmountPane(objAttr);
		design_root.getChildren().addAll(setting_pane);
		//				resource_name_pane.getPane(), resource_amount_pane.getPane());
	}

	private void setLabel() {

		design_root.getChildren().addAll(new GameSettingsLabel().getLabel(),
										 new NumPlayersLabel().getLabel(),
										 new LossConditionLabel().getLabel(),
										 new CreateResourceLabel().getLabel(),
										 new ChooseResourceLabel().getLabel(),
										 new StartingAmountLabel().getLabel(),
										 new ChooseResourceLabel1().getLabel(),
										 new StartingAmountLabel1().getLabel());
	}

	private void setComboBoxes() {
		ObservableList<String> num_players_options = 
				FXCollections.observableArrayList(
						"1",
						"2",
						"3"
						);
		MainComboBox num_players_cb = new NumPlayerComboBox();
		num_players_cb.getItems().addAll(num_players_options);
		//		num_paleyrs_cb = new ComboBox(num_player_options);
		num_players_cb.setValue("1");

		ObservableList<String> loss_condition_options = 
				FXCollections.observableArrayList(
						"1",
						"2",
						"3"
						);
		MainComboBox loss_condition_cb = new LossConditionComboBox();
		loss_condition_cb.setValue("3");


		MainComboBox choose_resource_cb = new ChooseResourceComboBox();
		choose_resource_cb.setValue("gold");
		ObservableList<String> resource_options = 
				FXCollections.observableArrayList(
						"gold",
						"silver",
						"crystal"
						);
		choose_resource_cb.getItems().addAll(resource_options);


		MainComboBox choose_amount_cb = new ChooseAmountComboBox();
		choose_amount_cb.setValue("10");
		ObservableList<String> amount_options = 
				FXCollections.observableArrayList(
						"10", "20", "30", "40", "50",
						"60", "70", "80", "90", "100"
						);
		choose_amount_cb.getItems().addAll(amount_options);

		MainComboBox choose_resource_cb1 = new ChooseResourceComboBox1();
		choose_resource_cb1.setValue("silver");
		choose_resource_cb1.getItems().addAll(resource_options);


		MainComboBox choose_amount_cb1 = new ChooseAmountComboBox1();
		choose_amount_cb1.setValue("10");
		choose_amount_cb1.getItems().addAll(amount_options);

		design_root.getChildren().addAll(num_players_cb.getComboBox(), 
				loss_condition_cb.getComboBox(), choose_resource_cb.getComboBox(),
				choose_amount_cb.getComboBox(), choose_resource_cb1.getComboBox(),
				choose_amount_cb1.getComboBox());

	}

	private void setButtons() {
		design_root.getChildren().addAll(new AddResourceButton(resource_name_pane.getPane(), resource_amount_pane.getPane()));
		new ResourceApplyButton().getButton();
		//							resource_name_pane.getPane(),
		//	  						 resource_amount_pane.getPane(),
		//	  						 stage,
		//	  						objAttr));
		//		resource_name_pane.getPane(), 
		//			resource_amount_pane.getPane()).getButton());


	}




}
