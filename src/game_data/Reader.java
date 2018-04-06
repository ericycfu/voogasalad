package game_data;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Reader {
	/**
	 * reads all data at target location
	 * @param location
	 * @param category
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public List<?> read(String location) throws IOException, ClassNotFoundException{
		XStream xstream = new XStream();
		FileReader reader = new FileReader(location);
		List<Object> result = new ArrayList<>();
		ObjectInputStream in = xstream.createObjectInputStream(reader);
		try {
			while(true) {
				in.readObject();
			}
		}
		catch(EOFException e) {
			//not real error, just signifies end of file
		}
		return result;
	}
}
