package authoring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import gui_elements.buttons.MakeGameButton;
import gui_elements.buttons.PlayGameButton;
import gui_elements.texts.StartScreenText;
import gui_elements.factories.ButtonFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartScreen {
	public static final String STYLE_PATH = "gui_elements/css/AuthoringView.css";

	public static final String TITLE = "Rap Tilt Swagger";
	public static final Color INITIAL_COLOR = Color.WHITE;
	public static final int INITIAL_SCENE_WIDTH = 1200;
	public static final int INITIAL_SCENE_HEIGHT = 700;
	private Stage myStage;
	private 	StackPane myPane;
	private Scene myScene; 

	public StartScreen(Stage primaryStage) {
		myStage = primaryStage;
		setupScreen();
		setupContent();
		setupStage();
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(INITIAL_COLOR, null, null)));
		myPane.setId("start_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupContent() {
		VBox box = new VBox();
		box.getChildren().addAll(
				new StartScreenText(),
				ButtonFactory.makeButton("Make Game", e -> new MakeGameSelect(myStage), "image_button"),
				//new MakeGameButton(myStage), 
				ButtonFactory.makeButton("Load Game", e -> {FileChooser myFC = new FileChooser();
															File myFile = myFC.showOpenDialog(new Stage());
															try {
																new MakeGameScreen(myStage, myFile);
															} catch (ClassNotFoundException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															} catch (IOException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
															
				}, "load_game_button"),
				new PlayGameButton(myStage)
				);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(10);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle(TITLE);
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
//		myStage.setResizable(false);
		myStage.show();
	}	
}