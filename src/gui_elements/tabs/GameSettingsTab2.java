package gui_elements.tabs;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import game_engine.ResourceManager;
import game_object.ObjectAttributes;
import gui_elements.buttons.AddResourceButton;
import gui_elements.buttons.ResourceApplyButton;
import gui_elements.combo_boxes.LossConditionComboBox;
import gui_elements.combo_boxes.MainComboBox;
import gui_elements.combo_boxes.NumPlayerComboBox;
import gui_elements.labels.ChooseResourceLabel;
import gui_elements.labels.CreateResourceLabel;
import gui_elements.labels.GameSettingsLabel;
import gui_elements.labels.LossConditionLabel;
import gui_elements.labels.NumPlayersLabel;
import gui_elements.labels.StartingAmountLabel;
import gui_elements.panes.MainPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class GameSettingsTab2 extends Tab {

	private Group design_root;
	private Label game_settings_label, num_players_label, loss_condition_label, 
	create_resource_label, choose_resource_label, starting_amount_label;
	private GridPane setting_pane;
	private MainPane resource_name_pane, resource_amount_pane;
	private ObjectAttributes objAttr;
	private MainComboBox num_players_cb, loss_condition_cb;
	private ResourceManager myResourceManager;

	public GameSettingsTab2() {

		initialize();
	}

	private void initialize() {
		setText();
		setGroup();
		setPane();
		setLabel();
		setComboBoxes();
		setResourceInputs();
		//setButtons();
		myResourceManager = new ResourceManager();
	}

	private void setText(){
		this.setText("Game Setting");
	}

	private void setGroup() {
		design_root = new Group();
		this.setContent(design_root);

	}

	private void setPane() {
		setting_pane = new GridPane();
		design_root.getChildren().addAll(setting_pane);
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
		num_players_cb.setValue("1");

		ObservableList<String> loss_condition_options = 
				FXCollections.observableArrayList(
						"1",
						"2",
						"3"
						);
		loss_condition_cb = new LossConditionComboBox();
		loss_condition_cb.setValue("3");

		
		design_root.getChildren().addAll(num_players_cb.getComboBox(), 
				loss_condition_cb.getComboBox());



	}
	
	private void setResourceInputs() {
		VBox myVBox = new VBox();
		HBox myHBox = new HBox();
		TextField myResources = new TextField();
		TextField myAmount = new TextField();
		myHBox.getChildren().add(myResources);
		myHBox.getChildren().add(myAmount);
		design_root.getChildren().add(myVBox);
		design_root.getStyleClass().add("design_root");
		myVBox.getChildren().add(myHBox);
		myVBox.getStyleClass().add("vboxid");
		
	}

	private void setButtons() {
		design_root.getChildren().addAll(new AddResourceButton(resource_name_pane.getPane(), resource_amount_pane.getPane()));


	}




}
