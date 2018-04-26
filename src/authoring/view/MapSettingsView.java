package authoring.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFileChooser;

import authoring.backend.ButtonFactory;
import authoring.backend.LabelFactory;
import authoring.backend.MapSettings;
import authoring.backend.TextFieldFactory;
import game_data.Reader;
import game_data.Writer;
import game_engine.ResourceManager;
import gui_elements.buttons.ImageChooserButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MapSettingsView extends Pane implements AuthoringView {
	private ResourceManager myResourceManager;
	private HBox resourcesBox;
	private Writer myWriter;
	private Reader myReader;
	
	public MapSettingsView(MapSettings settings) {
		this.getStyleClass().add(STYLE_PATH);
		initializeAll();
		myResourceManager = new ResourceManager();
		myWriter = new Writer();
		myReader = new Reader();
	}
	
	private void initializeAll() {
		initializeTitle();
		VBox myVBox = new VBox();
		HBox box = new HBox();
		resourcesBox = new HBox();
		myVBox.getChildren().add(box);
		myVBox.getChildren().add(resourcesBox);
		initializeLabelBox(box);
		initializeContent(box);
		box.setPadding(new Insets(50, 50, 0, 50));
		this.getChildren().add(box);
		setupButton();
	}
	
	private void initializeTitle() {
		this.getChildren().add(LabelFactory.makeLabel("Game Settings", DEFAULT_TITLE));
//		initializeResources(resourcesBox);
//		box.setPadding(new Insets(100, 50, 0, 50));
//		resourcesBox.setPadding(new Insets(0, 50, 0, 50));
//		this.getChildren().add(myVBox);
	}
	
	private void initializeLabelBox(HBox rootBox) {
		VBox box = new VBox();
		String[] labels = {
				"Number of players:", 
				"Loss condition:", 
				"Image selection:",
				"Map width:",
				"Map height:"
				};
		for (int i=0; i<labels.length; i++) {
			box.getChildren().addAll(newLabel(labels[i]));
		}
		standardBox(box);
		box.setSpacing(40);
		rootBox.getChildren().add(box);

	}
	
	private void initializeContent(HBox rootBox) {
		VBox box = new VBox();
		box.getChildren().addAll(
				new TextField(),
				new ComboBox(),
				new ImageChooserButton(),
				new TextField(),
				new TextField());
		standardBox(box);
		box.setSpacing(32);
		rootBox.getChildren().add(box);
	}
	
	private void setupButton() {
		HBox box = new HBox();
		Button saveButton = ButtonFactory.makeButton("Save", e -> saveConditions());
		box.getChildren().addAll(saveButton);
//		box.setPadding(new Insets(0, 0, 0, 0));
		this.getChildren().add(box);
	}
	
	private void saveConditions() {
	}
	private void initializeResources(HBox rootBox) {
		VBox myVBox = new VBox();
		rootBox.getChildren().add(myVBox);
		HBox myHBox = new HBox();
		myVBox.getChildren().add(myHBox);
		String labels[] = {"Resource Name", "Default Amount"};
		for (int i = 0; i < labels.length; i++) {
			myHBox.getChildren().addAll(newLabel(labels[i]));
			myHBox.setSpacing(75);
		}
		EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myVBox.getChildren().add(createResourceEntry());
			}
		};
		EventHandler<ActionEvent> mySavingHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				saveSettings();
			}
		};
		myHBox.getChildren().add(ButtonFactory.makeButton("Add New Resource Entry", myHandler));
		myVBox.getChildren().add(createResourceEntry());
		((HBox)myVBox.getChildren().get(myVBox.getChildren().size()-1)).getChildren().add(ButtonFactory.makeButton("Save Settings", mySavingHandler));
		standardBox(myVBox);
	}
	
	private HBox createResourceEntry(){
		HBox myReturn = new HBox();
		myReturn.getChildren().addAll(
			new TextField(),
			new TextField()
		);
		return myReturn;
	}
	
	private Label newLabel(String text) {
		return LabelFactory.makeLabel(text, DEFAULT_LABEL);
	}
	
	private void standardBox(VBox box) {
		box.setPadding(new Insets(0, 25, 0, 25));
	}
	private void saveSettings() {
		VBox myRootBox = (VBox) this.getChildren().get(0);
		saveResources((VBox)((HBox) myRootBox.getChildren().get(1)).getChildren().get(0));
		saveMapConfiguration((VBox)((HBox) myRootBox.getChildren().get(0)).getChildren().get(1));
		try {
			myWriter.write("src/gui_elements/tabs/test", myResourceManager.getResourceEntries());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//this try/catch statement below likely goes into the player so they can get the list of resources?
		try {
			System.out.println("creating the list");
			List<Entry<String, Double>> myList = new ArrayList<Entry<String, Double>>();
			List<Object> initialList = new ArrayList<Object>();
			initialList = myReader.read("src/gui_elements/tabs/test");
			for (Object myObj : initialList) {
				myList.add((Entry<String, Double>) myObj);
			}
			System.out.println(myList.get(0));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveMapConfiguration(VBox myBox) {
		for (Node myNode : myBox.getChildren()) {
			  try {
				  ((TextField) myNode).getText();
			  }
			  catch(Exception e) {
				  try {
					 // ((ComboBox) myNode).get
					  //work in progress
				  }
				  catch(Exception e2) {
					  
				  }
			  }
		}
		
	}
	private void saveResources(VBox myBox) {
		for (Node myNode : myBox.getChildren()) {
			try {
				HBox currentHBox = (HBox) myNode;
				String name = ((TextField) currentHBox.getChildren().get(0)).getText();
				String amount = ((TextField) currentHBox.getChildren().get(1)).getText();
				myResourceManager.addResource(name, Double.parseDouble(amount));
			}
			catch(Exception e){
			//nothing really wrong here, just nothign to get because not a textfield, change this later
			}
		}
	}
}
