package game_player.visual_element;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import game_data.Reader;
import game_data.Writer;
import game_engine.ResourceManager;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import game_player.alert.AlertMaker;
import gui_elements.factories.ButtonFactory;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * the interface that all top panel UI elements implement
 * @author Eddie
 */
public abstract class TopPanel {
	
	public static final String START = "Start";
	public static final String PLAY = "Play";
	public static final String PAUSE = "Pause";
	public static final String SAVE = "Save";
	public static final String LOAD = "Load";
	public static final String TIME = "Time";
	public static final String RESOURCE = "Resources";
	public static final String FILEPATH = "data/";
	public static final String SAVETEXT = "Save Game";
	public static final String LOADTEXT = "Load Game";
	public static final String DEFAULTBGSTYLE = "-fx-background-color: #FFFFFF;";
	public static final String CLASSALERTHEAD = "ClassNotFoundException";
	public static final String CLASSALERTBODY = "Incorrect class type!";
	public static final String IOALERTHEAD = "FileNotFound";
	public static final String IOALERTBODY = "Location invalid! No file found!";
	public static final String NPALERTHEAD = "No File Selected";
	public static final String NPALERTBODY = "Please choose a file to save to!";
	public static final double SBWIDTH = 0.125;
	public static final double BUTTONSWIDTH = 0.5;
	public static final double TIMEWIDTH = 0.25;
	public static final double RESOURCEWIDTH = 0.25;
	public static final int GOMINDEX = 0;
	public static final int PUINDEX = 1;
	public static final int MSINDEX = 2;
	
	private GridPane myPane;
	private TextArea timeTA;
	private int count;
	private ComboBox<String> resourceBoard;
	private int menuSpan;
	private int myTeamID;
	private DoubleProperty myTime;
	private ResourceManager myResourceManager;
	private boolean isLoaded;
	private List<Button> myButtons;
	private Writer myWriter = new Writer();
	private Reader myReader = new Reader();
	
	public TopPanel(int teamID, GameObjectManager gom, Set<GameObject> possibleUnits, double xsize, double ysize) {
		myPane = new GridPane();
		myPane.setStyle(DEFAULTBGSTYLE);
		menuSpan = 0;
		myTeamID = teamID;
		count = 0;
		//myTime = timeValue;
		
		setupButtons(gom, possibleUnits, xsize, ysize);
		setupTime(xsize, ysize);
		setupResources(xsize, ysize);
		addToPane(timeTA, resourceBoard);
	}
	
	private void setupButtons(GameObjectManager gom, Set<GameObject> possibleUnits, double xsize, double ysize) {
		Button[] buttonArray = {
				ButtonFactory.makeButton(START),
				ButtonFactory.makeButton(PAUSE),
				ButtonFactory.makeButton(SAVE, e -> save(gom, possibleUnits)), 
				ButtonFactory.makeButton(LOAD, e -> load(gom, possibleUnits))
		};
		myButtons = new ArrayList<>(Arrays.asList(buttonArray));
		myButtons.forEach(button -> {
			button.setMinHeight(ysize);
			button.setMinWidth(xsize * BUTTONSWIDTH / myButtons.size());
			addToPane(button);
		});
	}
	
	private void setupTime(double xsize, double ysize) {
		timeTA = new TextArea(TIME + GamePlayer.COLON + 0);
		timeTA.setEditable(false);
		timeTA.setPrefWidth(xsize * TIMEWIDTH);
		timeTA.setMaxHeight(ysize);
	}
	
	private void setupResources(double xsize, double ysize) {
		resourceBoard = new ComboBox<>();
		resourceBoard.setPromptText(RESOURCE);
		resourceBoard.setMaxHeight(ysize);
		resourceBoard.setPrefWidth(xsize * RESOURCEWIDTH);
	}
	
	private void addToPane(Node ... nodes) {
		for(Node n: nodes) {
			myPane.add(n, menuSpan, 0);
			menuSpan++;			
		}
	}
	
	private void save(GameObjectManager gom, Set<GameObject> possibleUnits) {
		FileChooser fc = new FileChooser();
		Stage stage = new Stage();
		fc.setInitialDirectory(new File(FILEPATH));
		fc.setTitle(SAVETEXT);
		File file = fc.showSaveDialog(stage);
		List<Object> listRepresentation = new ArrayList<>();
		listRepresentation.add(gom);
		listRepresentation.add(possibleUnits);
		try {
			myWriter.write(file.getCanonicalPath(), listRepresentation);
		} catch (IOException e) {
			new AlertMaker(IOALERTHEAD, IOALERTBODY);
		} catch (NullPointerException e) {
			new AlertMaker(NPALERTHEAD, NPALERTBODY);
		}
	}
	
	private void load(GameObjectManager gom, Set<GameObject> possibleUnits) {
		FileChooser fc = new FileChooser();
		Stage stage = new Stage();
		fc.setInitialDirectory(new File(FILEPATH));
		fc.setTitle(LOADTEXT);
		File file = fc.showOpenDialog(stage);
		try {
			isLoaded = true;
			List<Object> gameObjects = myReader.read(file.getCanonicalPath());
			gom.clearManager();
			gom.transferGameObjects((GameObjectManager) gameObjects.get(GOMINDEX));
			possibleUnits.clear();
			possibleUnits.addAll((Set<GameObject>) gameObjects.get(PUINDEX));
			myResourceManager = gom.getElements().stream().filter(go -> go.getOwner().getID() == myTeamID).collect(Collectors.toList()).get(0).getOwner().getResourceManager();
			resize(gom.getElements());
			resize(possibleUnits);
			resetTime();
		} catch (ClassNotFoundException e) {
			new AlertMaker(CLASSALERTHEAD, CLASSALERTBODY);
		} catch (IOException e) {
			new AlertMaker(IOALERTHEAD, IOALERTBODY);
		}
	}
	
	private void resize(Collection<GameObject> gameobject) {
		gameobject.stream().filter(go -> go.isBuilding()).forEach(go -> setGameObjectRenderer(go, GamePlayer.BUILDING_WIDTH, GamePlayer.BUILDING_HEIGHT));
		gameobject.stream().filter(go -> !go.isBuilding()).forEach(go -> setGameObjectRenderer(go, GamePlayer.UNIT_WIDTH, GamePlayer.UNIT_HEIGHT));
	}
	
	private void setGameObjectRenderer(GameObject go, int x, int y) {
		ImageView imgv = new ImageView(new Image(go.getRenderer().getImagePath()));
		go.getRenderer().setDisp(imgv);
		go.getRenderer().resize(x, y);
	}
	
	public abstract void resetTime();
	
	public abstract void setTimeTA(int time);
	
	public boolean getIsLoaded() {
		return isLoaded;
	}
	
	public void setIsLoaded(boolean val) {
		isLoaded = val;
	}
	
	public void update() {
		count++;
		if(myResourceManager != null) {
			resourceBoard.getItems().clear();
			myResourceManager.getResourceEntries().forEach(entry -> resourceBoard.getItems().add(entry.getKey() + GamePlayer.COLON + entry.getValue()));
		}
		setTimeTA(count);
	}
	
	public List<Button> getButtons() {
		return myButtons;
	}
	
	public TextArea getTimeTA() {
		return timeTA;
	}
	
	public Node getNodes() {
		return myPane;
	}
}
