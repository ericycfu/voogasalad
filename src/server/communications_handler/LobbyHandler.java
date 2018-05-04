package server.communications_handler;
/**
 * This class handles server side communications for a player in a lobby
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ConcurrentModificationException;
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
	public static final String LEAVE_MESSAGE = "Left";
	private boolean writeHold;
	private GameLobby currentLobby;
	public LobbyHandler(Socket input, RTSServer server, Logger logger) {
		super(input, server, logger);
		currentLobby = server.findPlayer(input);
		writeHold = false;
	}

	@Override
	public String updateServer() {
		try {
			String input;
			ObjectInputStream in = getInputStream();
			if((input = (String)in.readObject()) != null) {
				switch(input) {
				case REMOVE_OPTION: informClientOfLeave();
									log(" left their lobby");
									return MainPageHandler.CLASS_REF;
				case CHANGE_OPTION: currentLobby.changeTeam(in.readInt(), getSocket());
									log(" changed their team");
									return CLASS_REF;
				case START_GAME:
					if(getSocket().equals(currentLobby.getHost())) {
						currentLobby.setIsRunning(true);
						ObjectOutputStream out = getOutputStream();
						out.writeObject(START_GAME);
						out.flush();
					}
					return CLASS_REF;
				case ENTER_GAME:
					if(currentLobby.isRunning()) {
						ObjectOutputStream out = getOutputStream();
						out.writeObject(currentLobby.getCurrentGameInstance());
						out.writeInt(currentLobby.getTeamID(getSocket()));
						out.flush();
						return GameHandler.CLASS_REF;
					}
					return CLASS_REF;
				default: return CLASS_REF;
				}
			}
			else return CLASS_REF;
		}
		catch(IOException | ClassNotFoundException e) {
			return CLASS_REF;}
	}
	private void informClientOfLeave() throws IOException {
		currentLobby.remove(getSocket());
		ObjectOutputStream out = getOutputStream();
		out.writeObject(LEAVE_MESSAGE);
		out.flush();
	}
	@Override
	public void updateClient() {
		if(writeHold)
			return;
		try {
			ObjectOutputStream out = getOutputStream();
			if(!currentLobby.isRunning()) {
				out.writeObject(currentLobby);
				out.writeInt(currentLobby.getTeamID(getSocket()));
				out.writeInt(currentLobby.getPlayerID(getSocket()));
			}
			else {
				out.writeObject(START_GAME);
				writeHold = true;
			}
			out.flush();
			
		} catch (SocketException e) {
			currentLobby.remove(getSocket());
			throw new RTSServerException(CommunicationsHandler.DISCONNECT_MESSAGE);
		}
		catch(IOException | NullPointerException | ConcurrentModificationException e) {
			return;
		}
	}

}
