package game_data;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReaderTest {

	@Test
	public void testReadingFile() {
		Writer w = new Writer();
		Reader r = new Reader();
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(3);
		try {
			w.write("src/game_data/test", stuff);
		} catch (IOException e) {
			fail("we fucked up");
		}
		List<Object> recovery = new ArrayList<>();
		try {
			recovery = r.read("src/game_data/test");
		} catch (ClassNotFoundException e) {
			fail("we fucked up");
		} catch (IOException e) {
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
		Writer w = new Writer();
		Reader r = new Reader();
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(3);
		try {
			w.write("src/game_data/test", stuff);
		} catch (IOException e) {
			fail("we fucked up");
		}
		List<Object> recovery = new ArrayList<>();
		try {
			recovery = r.read("src/game_data/test","java.lang.String");
		} catch (ClassNotFoundException e) {
			fail("we fucked up");
		} catch (IOException e) {
			fail("we fucked up");
		}
		if(recovery.size() > 1) {
			fail("we fucked up");
		}
		assertEquals(recovery.get(0),"hi");
	}
	@Test
	public void testInvalidFileName() {
		Reader r = new Reader();
		try {
			r.read("\\\\/:*AAAAA?\\\"<>|3*7.pdf");
			fail("we fucked up");
		} catch (ClassNotFoundException e) {
			fail("we fucked up");
		} catch (IOException e) {
			return;
		}
	}
	@Test
	public void testNoFileFound() {
		Reader r = new Reader();
		try {
			r.read("hi");
			fail("we fucked up");
		} catch (ClassNotFoundException e) {
			fail("we fucked up");
		} catch (FileNotFoundException e) {
			return;
		} catch(IOException e) {
			fail("we fucked up");
		}
	}
}
