package game_data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Writer {
	/**
	 * writes data to location
	 * @param location
	 * @param data
	 * @throws IOException 
	 */
	public void write(String location, List<Object> data) throws IOException {
		XStream xstream = new XStream();
		File file = new File(location);
		@SuppressWarnings("resource")
		FileWriter writer= new FileWriter(file);
		for(Object datum: data) {
			String xml = xstream.toXML(datum);
			writer.write(xml);
		}
	}
}
