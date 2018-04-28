package authoring.backend;

import java.util.HashMap;
import java.util.Map;

import authoring.view.AuthoringView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapSettings implements AuthoringView {
	private String mapName;
	private int numPlayers;
	private String lossCondition;
	private Map<String, Integer> resources;
	private int mapwidth;
	private int mapheight;
	private String imagePath;
	private MapEntity map;
	public MapSettings() {
		initializeAll();
	}
	
	public void updateSettings(String mapName, int numPlayers, String imagePath, int mapwidth, int mapheight) {
		this.mapName = mapName;
		this.numPlayers = numPlayers;
		this.imagePath = imagePath;
		this.mapwidth = mapwidth;
		this.mapheight = mapheight;
		matchToSize(map);
		setMapByImage(map);
	}
	
	private void initializeAll() {
		mapName = "Default map";
		numPlayers = 1;
		lossCondition = "";
		resources = new HashMap<>();
		mapwidth = 1000;
		mapheight = 1000;
		imagePath = "/images/tt.jpg";
	}
	
	public void matchToSize(MapEntity map) {
		if (this.map == null) {
			this.map = map;
		}
		map.setPrefSize(mapwidth, mapheight);
	}
	
	public void setMapByImage(MapEntity map) {
		if (this.map == null) {
			this.map = map;
		}
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
		map.getChildren().add(image);
		image.toBack();
		
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getName() {
		return mapName;
	}
	public int getNumPlayers() {
		return numPlayers;
	}
	public int getMapHeight() {
		return mapheight;
	}
	public int getMapWidth() {
		return mapwidth;
	}
	public Map<String, Integer> getInitialResources(){
		return resources;
	}
	
}
