package game_player;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestMain extends Application {


	public static final String TITLE = "Gameplayer";
	public static final int INIT_X_SIZE = 800;
	public static final int INIT_Y_SIZE = 600;
	
	@Override
	public void start(Stage gpStage) throws Exception {
		
		GamePlayer gp = new GamePlayer();
		
        Scene scene = gp.setScene(gpStage, INIT_X_SIZE, INIT_Y_SIZE);
        
        gpStage.setScene(scene);

        gpStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
