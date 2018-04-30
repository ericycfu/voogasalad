package authoring.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFileChooser;

import authoring.backend.AuthoringController;
import authoring.backend.Extractor;
import authoring.backend.GameEntity;
import authoring.backend.MapSettings;
import game_data.Reader;
import game_data.Writer;
import game_engine.ResourceManager;
import gui_elements.buttons.ImageChooserButton;
import gui_elements.factories.ButtonFactory;
import gui_elements.factories.LabelFactory;
import gui_elements.factories.TextFieldFactory;
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
	private MapSettings settings;
	private GameEntity game;
	private AuthoringController authoring_controller;
	private ResourceManager myResourceManager;
	private HBox lossConditionBox;
	private HBox resourcesBox;
	private VBox contentBox;
	private TextField mapName = new TextField();
	private TextField numPlayers = new TextField();
	private ComboBox lossCondition = new ComboBox();
	private ImageChooserButton imageChooserButton = new ImageChooserButton();
	private TextField mapWidth = new TextField();
	private TextField mapHeight = new TextField();
	private Writer myWriter = new Writer();
	private static final int DEFAULT_MAP = 0;
	
	public MapSettingsView(AuthoringController authoring_controller, GameEntity game) {
		this.authoring_controller = authoring_controller;
		authoring_controller.updateMap(game.getCreatedMaps().getCreatedMaps().get(DEFAULT_MAP));
		settings = authoring_controller.getCurrentMap().getMapSettings();
		this.game = game;
		this.getStyleClass().add(STYLE_PATH);
		initializeAll();
		myResourceManager = game.getResourceManager();
		updateResources();
	}
	public MapSettingsView(MapSettings settings) {
		
		
	}
	
	public void setMapSettings(MapSettings settings) {
		this.settings = settings;
		
	}
	
	private void updateResources() {
		
		
	}
	
	private void initializeAll() {
		initializeTitle();
		VBox myVBox = new VBox();
		initializeSettings(myVBox);
		initializeLossConditions(myVBox);
		initializeResources(myVBox);
		this.getChildren().add(myVBox);
		setupButton();
	}
	
	private void initializeSettings(VBox rootBox) {
		HBox box = new HBox();
		initializeLabelBox(box);
		initializeContent(box);
		box.setPadding(new Insets(50, 50, 0, 50));
		rootBox.getChildren().add(box);
	}
	
	private void initializeLossConditions(VBox rootBox) {
		lossConditionBox = new HBox();
		rootBox.getChildren().add(lossConditionBox);
	}
	
	
	private void initializeResources(VBox rootBox) {
		resourcesBox = new HBox();
		initializeResources(resourcesBox);
		resourcesBox.setPadding(new Insets(50, 50, 0, 50));
		rootBox.getChildren().add(resourcesBox);
		
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
				"Map name:",
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
		contentBox = new VBox();
		contentBox.getChildren().addAll(
				mapName,
				numPlayers,
				lossCondition,
				imageChooserButton,
				mapWidth,
				mapHeight);
		mapName.setText(settings.getName());
		numPlayers.setText(Integer.toString(settings.getNumPlayers()));
		//get loss condition
		//get image chooser button?
		mapWidth.setText(Integer.toString(settings.getMapWidth()));
		mapHeight.setText(Integer.toString(settings.getMapHeight()));
		standardBox(contentBox);
		contentBox.setSpacing(32);
		rootBox.getChildren().add(contentBox);
	}
	
	private void setupButton() {
		HBox box = new HBox();
		Button saveButton = ButtonFactory.makeButton("Save", e -> saveConditions());
		box.getChildren().addAll(saveButton);
//		box.setPadding(new Insets(0, 0, 0, 0));
		this.getChildren().add(box);
	}
	
	private void saveConditions() {
		String mapName = Extractor.extractTextField(contentBox.getChildren().get(0));
		int numPlayers = Extractor.extractTextFieldInt(contentBox.getChildren().get(1));
		String imagePath = IMAGE_PATH + Extractor.extractImagePath(contentBox.getChildren().get(3));
		System.out.print(imagePath);
		int mapwidth = Extractor.extractTextFieldInt(contentBox.getChildren().get(4));
		int mapheight = Extractor.extractTextFieldInt(contentBox.getChildren().get(5));
		System.out.println(imagePath);
		settings.updateSettings(mapName, numPlayers, imagePath, mapwidth, mapheight);
	}
	
	private void newLossConditionLine(HBox rootBox) {
		HBox line = new HBox();
		
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
		for (int i = 0; i <= settings.getInitialResources().keySet().size(); i += 1) {
			myVBox.getChildren().add(createResourceEntry());
			System.out.println("am creating resource entry");
		}
		Set<Entry<String, Integer>> myResourceEntries = settings.getInitialResources().entrySet();
		int index = 0;
		for (Entry<String, Integer> entry: myResourceEntries) {
			((TextField) ((HBox) myVBox.getChildren().get(index)).getChildren().get(0)).setText(entry.getKey());
			((TextField) ((HBox) myVBox.getChildren().get(index)).getChildren().get(1)).setText(Integer.toString(entry.getValue()));
		}
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
		VBox myRootBox = (VBox) this.getChildren().get(1);
		saveResources((VBox)((HBox) myRootBox.getChildren().get(1)).getChildren().get(0));
		saveMapConfiguration((VBox)((HBox) myRootBox.getChildren().get(0)).getChildren().get(1));
		try {
			myWriter.write("src/gui_elements/tabs/test", myResourceManager.getResourceEntries());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//this try/catch statement below likely goes into the player so they can get the list of resources?
		/*try {
			System.out.println("creating the list");
			List<Entry<String, Double>> myList = new ArrayList<Entry<String, Double>>();
			List<Object> initialList = new ArrayList<Object>();
			initialList = Reader.read("src/gui_elements/tabs/test");
			for (Object myObj : initialList) {
				myList.add((Entry<String, Double>) myObj);
			}
			//this is the constructor that gameplayer will use
			ResourceManager newManager = new ResourceManager(myList);
			System.out.println(myList.get(0));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
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
		myResourceManager.clearManager();
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
		authoring_controller.updateBuildCost();
	}

}
