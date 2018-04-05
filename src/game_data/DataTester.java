package game_data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataTester {
	public static void main(String [] args) throws ClassNotFoundException, IOException {
		Reader myReader = new Reader();
		Writer myWriter = new Writer();
		List<Object> stuff = new ArrayList<>();
		stuff.add("hi");
		stuff.add(3);
		try {
			myWriter.write("&$@*#%(", stuff);
		} catch (IOException e) {
			System.out.println("hello");
		}
		List<Object> recovery = new ArrayList<>();
		recovery = myReader.read("src/game_data/test");
		for(Object obj: recovery) {
			System.out.println(obj);
		}
		List<Object> selectiveRecovery = new ArrayList<>();
		selectiveRecovery = myReader.read("src/game_data/test","java.lang.String");
		for(Object obj: selectiveRecovery) {
			System.out.println(obj);
		}
	}
}
