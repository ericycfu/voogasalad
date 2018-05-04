package authoring.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import authoring.backend.AuthoringController;
import authoring.backend.GameEntity;
import game_data.Reader;
import game_engine.ResourceManager;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 * The screen of the authoring environment that 
 * @author Eric Fu
 *
 */
public class MakeGameScreen implements AuthoringView {
	public static final Color INITIAL_COLOR = Color.LIGHTSKYBLUE;

	private Stage myStage;
//	private Scene myScene;
	private GameEntity myGame;
//	private AuthoringController myAuthoringController;
	private Reader myReader = new Reader();
	public MakeGameScreen (Stage stage) {
		myGame = new GameEntity();
		myStage = stage;
		setupScreen();
	}
	
	@SuppressWarnings("unchecked")
	public MakeGameScreen(Stage stage, File myFile) throws ClassNotFoundException, IOException {
		//get list of items from the 
		List<Object> ao = myReader.read(myFile.getCanonicalPath(), "java.util.ArrayList");
		List<Object>  myAuthoringObjects = (List<Object>) ao.get(0);
		List<Object> myMaps = (List<Object>) ao.get(1);
		List<Object> myMapSettings = (List<Object>) ao.get(2);
//		Map<AuthoringObject, List<AuthoringObject>> myMap = (Map<AuthoringObject, List<AuthoringObject>>) maps.get(0);
//		Map<AuthoringObject, List<Vector2>> myMap = (Map<AuthoringObject, List<Vector2>>) map.get(0);
//		List<Object> mapsettings = myReader.read(myFile.getCanonicalPath(), "authoring.backend.MapSettings");
//		MapSettings myMapSettings = (MapSettings) mapsettings.get(0);
		List<Object> resourcemanager = myReader.read(myFile.getCanonicalPath(), "game_engine.ResourceManager"); //change category later
		ResourceManager myResourceManager = (ResourceManager) resourcemanager.get(0);
		//System.out.println("Resource Manager size: " + myResourceManager.getResourceEntries().size());
		//System.out.println("First entry of reosurce manager: " + myResourceManager.getResourceEntries().get(0));
//		myGame = new GameEntity(myAuthoringObjects, maps, myMapSettings, myResourceManager);
		myGame = new GameEntity(myAuthoringObjects, myMaps, myMapSettings, myResourceManager);
		myStage = stage;
		setupScreen();
		
	}

	private void setupScreen() {
		AuthoringController authoringController = new AuthoringController();
		HBox box = new HBox();
//		box.setId("start_screen");
		VBox inner = new VBox();
		inner.getChildren().addAll(
				//need to populate the entries with the information from myGame
				new DisplayMenu(authoringController, myGame, myStage),
				new CreatedObjectsTabs(authoringController, myGame));
		box.getChildren().addAll(
				new MakeGameTabs(authoringController, myGame),
				inner);

		box.setPadding(LINE_INSETS);
		box.setSpacing(SPACING_SMALL);
		Scene scene = new Scene(box);
		scene.getStylesheets().add(STYLE_PATH);
		myStage.setScene(scene);
		box.setId("make_game_screen");
	}
	
}
