package game_data;

import java.io.EOFException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

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
	

}
