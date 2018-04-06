package game_data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class WriterTest {

	@Test
	public void testCorrectWriting() {
		Writer w = new Writer();
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(3);
		try {
			w.write("src/game_data/test", stuff);
		} catch (IOException e) {
			fail("we fucked up");
		}
		File file = new File("src/game_data/test");
		assertEquals(true,file.exists());
	}
	@Test
	public void testFileNotExist() {
		Writer w = new Writer();
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(3);
		try {
			w.write("\\\\/:*AAAAA?\\\"<>|3*7.pdf", stuff);
			fail("we fucked up");
		} catch (IOException e) {
			return;
		}
		fail("we fucked up");
	}

}
