package game_data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import authoring.backend.AuthoringObject;
import game_object.GameObject;
import transform_library.Vector2;

public class DemoTester {

	public DemoTester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		List<Object> objects = new ArrayList<>();
		
		Vector2 myVector = new Vector2(1.0, 6.0);
		GameObject myGO = new GameObject(myVector);
		myGO.setName("Soldier");
		myGO.setMovementSpeed(100.0);
		
		AuthoringObject myAO = new AuthoringObject();
		
		
		objects.add(myGO);
		objects.add("hello");
		objects.add(myAO);
		Writer.write("src/game_data/myDemo", objects);
		
		List<Object> objects2 = new ArrayList<>();
		objects2 = Reader.read("src/game_data/myDemo");
		
		System.out.println(objects2.get(0));
		System.out.println(((GameObject) objects2.get(0)).getName());
		System.out.println(((GameObject) objects2.get(0)).getMovementSpeed());
		System.out.println(((GameObject) objects2.get(0)).getClass().getName());
		
		System.out.println(objects2.get(1).getClass().getName());
		
		System.out.println(objects2.get(2).getClass().getName());
		System.out.println("AO NAME: " + ((AuthoringObject)objects2.get(2)).getName());
		
		
		List<Object> selectiveList = new ArrayList<>();
		selectiveList = Reader.read("src/game_data/myDemo", "java.lang.String");
		System.out.println(selectiveList.get(0));
		
		List<Object> selectiveList2 = new ArrayList<>();
		selectiveList2 = Reader.read("src/game_data/myDemo", "game_object.GameObject");
		System.out.println(selectiveList2.get(0));
		}

}
