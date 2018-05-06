package game_player.selected_unit_manager;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_player.GamePlayer;
import game_player.alert.AlertMaker;
import pathfinding.GridMap;
import transform_library.Vector2;

public class MultiPlayerSelectedUnitManager extends SelectedUnitManager {
	
	public static final String MOVE = "Move ";
	public static final String INTERACT = "Interact ";
	public static final String BUILD = "Build ";
	public static final String IOEXCEPTION_MSG_HEADER = "Communication failed";
	public static final String IOEXCEPTION_MSG_TEXT = "Communication with current server is interrupted. Please reconnect.";
	private Socket mySocket;
	
	/**
	 * Construct a SelectedUnitManager for a specific team in a multiplayer game.
	 * @param team corresponding team
	 * @param socket a socket to connect to the server
	 */
	public MultiPlayerSelectedUnitManager(Team team, Socket socket) {
		super(team);
		mySocket = socket;
	}

	@Override
	/**
	 * Constructs a move command and communicates with server
	 */
	public void move(Vector2 target, GameObjectManager gom, GridMap gridmap) {
		mySelectedUnits.stream()
			.filter(go -> !go.getTransform().getPosition().matches(target))
			.forEach(go -> {
				String msg = MOVE + go.getID() + GamePlayer.SPACE + target.getX() + GamePlayer.SPACE + target.getY();
				communicate(msg);
			});
	}

	@Override
	/**
	 * Constructs an interact command and communicates with server
	 */
	public void interact(GameObject source, GameObject target, int interactionID, GameObjectManager gom,
			GridMap gridmap, Vector2 location) {
		String msg = INTERACT + source.getID() + GamePlayer.SPACE + target.getID() + GamePlayer.SPACE + interactionID + GamePlayer.SPACE + location.getX() + GamePlayer.SPACE + location.getY();
		communicate(msg);
	}

	@Override
	/**
	 * Constructs a build command and communicates with server
	 */
	public void build(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap,
			Vector2 location) {
		String msg = BUILD + source.getID() + GamePlayer.SPACE + target.getName() + GamePlayer.SPACE + interactionID + GamePlayer.SPACE + location.getX() + GamePlayer.SPACE + location.getY();
		communicate(msg);
	}
	
	// creates an ObjectOutputStream from the socket and communicates a command message to the server
	private void communicate(String msg) {
		ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(mySocket);
		try {
			outstream.writeObject(msg);
			outstream.flush();
		} catch (IOException e) {
			AlertMaker.makeAlert(IOEXCEPTION_MSG_HEADER, IOEXCEPTION_MSG_TEXT);
		}
	}
}
