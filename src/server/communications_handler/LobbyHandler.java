package server.communications_handler;
/**
 * This class handles server side communications for a player in a lobby
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import server.GameLobby;
import server.RTSServer;
import server.RTSServerException;

public class LobbyHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "Lobby";
	public static final String REMOVE_OPTION = "Leave";
	public static final String START_GAME = "Start";
	public static final String ENTER_GAME = "Play";
	public static final String CHANGE_OPTION = "Change";
	private GameLobby currentLobby;
	public LobbyHandler(Socket input, RTSServer server, Logger logger) {
		super(input, server, logger);
		currentLobby = server.findPlayer(input);
	}

	@Override
	public String updateServer() {
		try {
			String input;
			ObjectInputStream in = getInputStream();
			if(in == null)
				throw new RTSServerException("Client DCed");
			if((input = (String)in.readObject()) != null) {
				switch(input) {
				case REMOVE_OPTION: currentLobby.remove(getSocket());
				ObjectOutputStream out = getOutputStream();
				out.writeObject("Left");
				out.flush();
				log(" left their lobby");
				return MainPageHandler.CLASS_REF;
				case CHANGE_OPTION:
					currentLobby.changeTeam(in.readInt(), getSocket());
					log(" changed their team");
					return CLASS_REF;
				case START_GAME:
					if(getSocket().equals(currentLobby.getHost())) {
						currentLobby.setIsRunning(true);
					}
					return CLASS_REF;

				default: if(currentLobby.isRunning())
				{
					ObjectOutputStream transition = getOutputStream();
					transition.writeObject(currentLobby.getCurrentGameInstance());
					transition.flush();
					return GameHandler.CLASS_REF;
				}
				return CLASS_REF;
				}
			}
			else return CLASS_REF;
		}
		catch(IOException | ClassNotFoundException e) {
			return CLASS_REF;}
	}

	@Override
	public void updateClient() {
		try {
			ObjectOutputStream out = getOutputStream();
			if(out == null)
				throw new RTSServerException("Disconnected");
			if(currentLobby.isRunning())
				out.writeObject(START_GAME);
			else { out.writeObject(currentLobby);
			out.writeInt(currentLobby.getTeamID(getSocket()));
			out.writeInt(currentLobby.getPlayerID(getSocket()));
			}
			out.flush();
		} catch (Exception e) {
			return;
		}
	}

}
