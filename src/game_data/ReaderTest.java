package game_data;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;



/**
 * JUnit test for the reader
 * @author shichengrao
 *
 */
public class ReaderTest {
	private Logger logger = Logger.getLogger(WriterTest.class.getName());
	public static final int SAMPLE_DATA = 3;
	Writer myWriter = new Writer();
	Reader myReader = new Reader();

	@Test
	public void testReadingFile() {
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(SAMPLE_DATA);
		try {
			myWriter.write("src/game_data/test", stuff);
		} catch (IOException e) {
			logger.log(Level.WARNING, "IOException for reader");
			fail("we fucked up");
		}
		List<Object> recovery = new ArrayList<>();
		try {
			recovery = myReader.read("src/game_data/test");
		} catch (ClassNotFoundException | IOException e) {
			logger.log(Level.WARNING, "IOException or ClassNotFoundException for reader");
			fail("we fucked up");
		} 
		for(int i = 0; i < stuff.size();i++) {
			if(!stuff.get(i).equals(recovery.get(i))) {
				fail("elements differ");
			}
		}
		if(stuff.size() != recovery.size()) {
			fail("we fucked up");
		}
		return;
	}
	@Test
	public void testReadSpecific() {
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(SAMPLE_DATA);
		try {
			myWriter.write("src/game_data/test", stuff);
		} catch (IOException e) {
			logger.log(Level.WARNING, "IOException for reader");
			fail("we fucked up");
		}
		List<Object> recovery = new ArrayList<>();
		try {
			recovery = myReader.read("src/game_data/test","java.lang.String");
		} catch (ClassNotFoundException | IOException e) {
			logger.log(Level.WARNING, "IOException or ClassNotFound for reader");
			fail("we fucked up");
		}
		if(recovery.size() > 1) {
			fail("we fucked up");
		}
		assertEquals(recovery.get(0),"hi");
	}
	@Test
	public void testInvalidFileName() {
		try {
			myReader.read("\\\\/:*AAAAA?\\\"<>|3*7.pdf");
			fail("we fucked up");
		} catch (ClassNotFoundException e) {
			logger.log(Level.WARNING, "ClassNotFoundException for reader");
			fail("we fucked up");
		} catch (IOException e) {
			logger.log(Level.WARNING, "IOException for reader");
			return;
		}
	}
	@Test
	public void testNoFileFound() {
		try {
			myReader.read("hi");
			fail("we fucked up");
		} catch (FileNotFoundException e) {
			logger.log(Level.WARNING, "FileNotFoundException for reader");
			return;
		} catch(IOException | ClassNotFoundException e) {
			logger.log(Level.WARNING, "IOException or ClassNotFoundException for reader");
			fail("we fucked up");
		}
	}
}
