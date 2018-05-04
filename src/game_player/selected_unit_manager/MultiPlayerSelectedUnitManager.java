package game_player.selected_unit_manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import pathfinding.GridMap;
import transform_library.Vector2;

public class MultiPlayerSelectedUnitManager extends SelectedUnitManager {
	
	private Socket mySocket;
	
	public MultiPlayerSelectedUnitManager(Team team, Socket socket) {
		super(team);
		mySocket = socket;
	}

	@Override
	public void move(Vector2 target, GameObjectManager gom, GridMap gridmap) {
		selectedUnits.stream()
			.filter(go -> !go.getTransform().getPosition().matches(target))
			.forEach(go -> {
				ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
				String msg = "Move " + go.getID() + GamePlayer.SPACE + target.getX() + GamePlayer.SPACE + target.getY();
				try {
					outstream.writeObject(msg);
					outstream.flush();
					System.out.println("Done");
				} catch (IOException e) {
					e.printStackTrace();
					// do nothing
				}
			});
	}

	@Override
	public void interact(GameObject source, GameObject target, int interactionID, GameObjectManager gom,
			GridMap gridmap, Vector2 position) {
		ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
		String msg = "Interact " + source.getID() + GamePlayer.SPACE + target.getID() + GamePlayer.SPACE + interactionID + GamePlayer.SPACE + position.getX() + GamePlayer.SPACE + position.getY();
		try {
			outstream.writeObject(msg);
			outstream.flush();
		} catch (IOException e) {
			// do nothing
		}
	}

	@Override
	public void build(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap,
			Vector2 position) {
		ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
		String msg = "Build " + source.getID() + GamePlayer.SPACE + target.getName() + GamePlayer.SPACE + interactionID + GamePlayer.SPACE + position.getX() + GamePlayer.SPACE + position.getY();
		try {
			outstream.writeObject(msg);
			outstream.flush();
		} catch (IOException e) {
			// do nothing
		}
	}
	

}
