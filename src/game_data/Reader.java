package game_data;

import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.backend.AuthoringObject;
import game_object.GameObject;
import game_object.GameObjectManager;
/**
 * static class for loading data
 * @author shichengrao
 *
 */
public class Reader {
	/**
	 * reads all data at target location
	 * @param location
	 * @param category
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Object> read(String location) throws IOException, ClassNotFoundException{
		XStream xstream = new XStream(new DomDriver());
		FileReader reader = new FileReader(location);
		List<Object> result = new ArrayList<>();
		ObjectInputStream in = xstream.createObjectInputStream(reader);
		while(in.available()>0) {
				Object obj = in.readObject();
				setUpNonSerializable(obj);
				result.add(obj);
		}
		return result;
	}
	/**
	 * reads data of type category from location
	 * @param location
	 * @param category
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<Object> read(String location, String category) throws ClassNotFoundException, IOException{
		XStream xstream = new XStream(new DomDriver());
		FileReader reader = new FileReader(location);
		List<Object> result = new ArrayList<>();
		ObjectInputStream in = xstream.createObjectInputStream(reader);
		
		while(in.available()>0) {
				Object obj = in.readObject();
				//System.out.println(obj.getClass().getName());
				setUpNonSerializable(obj);
				//System.out.println(obj.getClass().getName());
				if(obj.getClass().getName().equals(category)) {
					result.add(obj);
				}
		}
		return result;
	}
	private void setUpNonSerializable(Object obj) {
		if(obj instanceof GameObjectManager) {
			((GameObjectManager) obj).setupImages();
		}
		else if (obj instanceof Map) {
			for (Object myObj:  ((Map) obj).keySet()){
				setUpNonSerializable(myObj);
			}
		}
		else if(obj instanceof Iterable) {
			for(Object myObj: (Iterable) obj) {
				setUpNonSerializable(myObj);
			}
		}
		else if(obj instanceof GameObject) {
			((GameObject) obj).setupImages();
		}
		else if(obj instanceof AuthoringObject) {
			((AuthoringObject) obj).resetImageAfterLoad();
		}
		
	}

}
