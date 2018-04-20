package game_data;

import java.util.LinkedHashMap;
import java.util.Map;

import javafx.scene.image.Image;

public class ImageCache {

	private Map<String, Image> myCache;
	private final int MAX_SIZE = 10;
	public ImageCache() {
		myCache = new LinkedHashMap<String, Image>(MAX_SIZE){
			@Override
			protected boolean removeEldestEntry(final Map.Entry eldest) {
				return size()> MAX_SIZE;
			}
		};
	}
	
	
	public Image getImage(String path) {
		return myCache.get(path);
	}
	
	public void addImage(String path, Image myImage) {
		myCache.put(path, myImage);
	}

}
