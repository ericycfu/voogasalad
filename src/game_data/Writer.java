package game_data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * static class for writing data
 * @author shichengrao
 * @author Eric Fu
 */
public class Writer {
	
	Reader myReader = new Reader();
	/**
	 * writes data to location
	 * @param location
	 * @param data
	 * @throws IOException 
	 */
	public void write(String location, List<? extends Object> data) throws IOException {
		System.out.println(data.size());
		XStream xstream = new XStream(new DomDriver());
		File file = new File(location);
		FileWriter writer = new FileWriter(file);
		ObjectOutputStream out = xstream.createObjectOutputStream(writer);
		for(Object datum: data) {
			out.writeObject(datum);
		}
		out.close();
	}
	
	/**
	 * writes data to location without overwriting previous data
	 * @param location
	 * @param data
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public void writeNoOverwrite(String location, List<Object> data) throws IOException {
		try {
			List<Object> prevData= myReader.read(location);
			data.addAll(prevData);
		}
		catch (ClassNotFoundException e) {
			write(location, data);
		}
	}
}
