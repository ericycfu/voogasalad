package main;

import authoring.StartScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class Launch extends Application {

	@Override
	public void start(Stage primaryStage) {
//		new Main(primaryStage);
		new StartScreen(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
