package game_data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Writer {
	/**
	 * writes data to location
	 * @param location
	 * @param data
	 * @throws IOException 
	 */
	public void write(String location, List<Object> data) throws IOException {
		XStream xstream = new XStream(new StaxDriver());
		System.out.println("hello");
		File file = new File(location);
		FileWriter writer = new FileWriter(file);
		ObjectOutputStream out = xstream.createObjectOutputStream(writer);
		for(Object datum: data) {
			out.writeObject(datum);
		}
		out.close();
	}
}
