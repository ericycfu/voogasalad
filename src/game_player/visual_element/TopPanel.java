package game_player.visual_element;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import game_data.Reader;
import game_data.Writer;
import game_engine.ResourceManager;
import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.alert.AlertMaker;
import gui_elements.factories.ButtonFactory;
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
public class TopPanel {
	
	public static final String START = "Start";
	public static final String PAUSE = "Pause";
	public static final String SAVE = "Save";
	public static final String LOAD = "Load";
	public static final String TIME = "Time";
	public static final String RESOURCE = "Resources";
	public static final String COLON = ": ";
	public static final String FILEPATH = "data/";
	public static final String SAVETEXT = "Save Game";
	public static final String LOADTEXT = "Load Game";
	public static final String DEFAULTBGSTYLE = "-fx-background-color: #FFFFFF);";
	public static final String CLASSALERTHEAD = "ClassNotFoundException";
	public static final String CLASSALERTBODY = "Incorrect class type!";
	public static final String IOALERTHEAD = "FileNotFound";
	public static final String IOALERTBODY = "Location invalid! No file found!";
	public static final String NPALERTHEAD = "No File Selected";
	public static final String NPALERTBODY = "Please choose a file to save to!";
	public static final double SBWIDTH = 0.125;
	public static final double TAWIDTH = 0.25;
	
	private GridPane myPane;
	private TextArea time;
	private ComboBox<String> resourceBoard;
	private int menuSpan;
	private int myTeamID;
	private ResourceManager myResourceManager;
	private boolean isLoaded;
	private Writer myWriter = new Writer();
	private Reader myReader = new Reader();
	
	public TopPanel(Socket socket, int teamID, GameObjectManager gom, Set<GameObject> possibleUnits, double xsize, double ysize) {
		myPane = new GridPane();
		myPane.setStyle(DEFAULTBGSTYLE);
		menuSpan = 0;
		myTeamID = teamID;
		
		setupButtons(socket, gom, possibleUnits, xsize, ysize);
		setupTime(xsize, ysize);
		setupResources(xsize, ysize);
		addToPane(time, resourceBoard);
	}
	
	private void setupButtons(Socket socket, GameObjectManager gom, Set<GameObject> possibleUnits, double xsize, double ysize) {
		Button[] buttonArray = {
				ButtonFactory.makeButton(START, e -> {
					
				}), 
				ButtonFactory.makeButton(PAUSE, e -> {
					
				}), 
				ButtonFactory.makeButton(SAVE, e -> save(gom, possibleUnits)), 
				ButtonFactory.makeButton(LOAD, e -> load(gom, possibleUnits))
		};
		List<Button> buttons = new ArrayList<>(Arrays.asList(buttonArray));
		buttons.forEach(button -> {
			button.setMinHeight(ysize);
			button.setMinWidth(xsize / 2 / buttons.size());
			addToPane(button);
		});
	}
	
	private void setupTime(double xsize, double ysize) {
		time = new TextArea(TIME + COLON + 0);
		time.setEditable(false);
		time.setPrefWidth(xsize / 4);
		time.setMaxHeight(ysize);
	}
	
	private void setupResources(double xsize, double ysize) {
		resourceBoard = new ComboBox<>();
		resourceBoard.setPromptText(RESOURCE);
		resourceBoard.setMaxHeight(ysize);
		resourceBoard.setPrefWidth(xsize / 4);
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
			gom.transferGameObjects((GameObjectManager)gameObjects.get(0)); // TODO: don't create new
			possibleUnits.clear();
			System.out.println(gameObjects.get(1));
			possibleUnits.addAll((Set<GameObject>) gameObjects.get(1));
			int index = 0;
			while(gom.getElements().get(index).getOwner().getID() != myTeamID) {
				index++;
			}
			myResourceManager = gom.getElements().get(index).getOwner().getResourceManager();
			for (GameObject go : possibleUnits) {
				go.getRenderer().setDisp(new ImageView(new Image(go.getRenderer().getImagePath())));
			}
			
		} catch (ClassNotFoundException e) {
			new AlertMaker(CLASSALERTHEAD, CLASSALERTBODY);
		} catch (IOException e) {
			new AlertMaker(IOALERTHEAD, IOALERTBODY);
		}
	}
	
	private void setResources() {
		resourceBoard.getItems().clear();
		List<Entry<String, Double>> entryList = myResourceManager.getResourceEntries();
		String[] resources = new String[entryList.size()];
		for(int i = 0; i < entryList.size(); i++) {
			resources[i] = entryList.get(i).getKey() + COLON + entryList.get(i).getValue();
		}
		resourceBoard.getItems().addAll(resources);
	}
	
	private void setTime(double timeValue) {
		time.setText(TIME + COLON + timeValue);
	}
	
	public boolean getIsLoaded() {
		return isLoaded;
	}
	
	public void setIsLoaded(boolean val) {
		isLoaded = val;
	}
	
	public void update() {
		if (isLoaded) {
			setResources();
			setTime(0); // TODO: set time
		}
	}
	
	public Node getNodes() {
		return myPane;
	}
}
