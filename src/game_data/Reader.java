package game_data;

import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import authoring.backend.AuthoringObject;
import game_object.GameObject;
import game_object.Renderer;
import javafx.scene.image.Image;

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
		try {
			while(true) {
				Object obj = in.readObject();
				setUpNonSerializable(obj);
				result.add(obj);
			}
		}
		catch(EOFException e) {
			//not real error, just signifies end of file
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
		try {
			while(true) {
				Object obj = in.readObject();
				setUpNonSerializable(obj);
				if(obj.getClass().getName().equals(category)) {
					result.add(obj);
				}
				
			}
		}
		catch(EOFException e) {
			//not real error, just signifies end of file
		}
		return result;
	}
	private void setUpNonSerializable(Object obj) {
		System.out.println(obj.getClass().getName());
		try {
			System.out.println("we are pre image");
			((GameObject) obj).setupImages();
			System.out.println("we are post image");
		}
		catch(ClassCastException e) {
			//if it is an authoring object
			//replace with code to get image url
			((AuthoringObject) obj).resetImageAfterLoad();
		}
	}

}
