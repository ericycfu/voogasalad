package game_player.visual_element;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import game_player.alert.AlertMaker;
import javafx.beans.property.DoubleProperty;

public class MultiTopPanel extends TopPanel {

	private DoubleProperty myTime;
	
	public MultiTopPanel(Socket socket, int teamID, GameObjectManager gom, Set<GameObject> possibleUnits, double xsize, double ysize) {
		super(teamID, gom, possibleUnits, ysize, ysize);
		getButtons().get(0).setOnAction(e -> startorpause(socket, PLAY));
		getButtons().get(1).setOnAction(e -> startorpause(socket, PAUSE));
	}

	private void startorpause(Socket socket, String text) {
		ObjectOutputStream outstream  = GamePlayer.getObjectOutputStream(socket);
		try {
			outstream.writeObject(text);
			outstream.flush();
		} catch (IOException exception) {
			AlertMaker.makeAlert(GamePlayer.SERVERALERTHEAD, GamePlayer.SERVERALERTBODY);
		}
	}

	@Override
	public void setTimeTA(int time) {
		
	}

	@Override
	public void startTimeline() {
		
	}
}
