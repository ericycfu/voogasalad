package authoring.backend;

import java.util.HashMap;
import java.util.Map;

import authoring.view.AuthoringView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;

public class MapSettings implements AuthoringView {
	private String mapName;
	private int numPlayers;
	private String lossCondition;
	private Map<String, Integer> resources;
	private int mapwidth;
	private int mapheight;
	private String imagePath;
	public MapSettings() {
		initializeAll();
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
		map.setPrefSize(mapwidth, mapheight);
	}
	
	public void setMapByImage(MapEntity map) {
		Image image = new Image(getClass().getResourceAsStream(imagePath));
		map.getChildren().add(new ImageView(image));
		
	}
	
	public String getImagePath() {
		return imagePath;
	}
	
	public String getName() {
		return mapName;
	}
	
}
