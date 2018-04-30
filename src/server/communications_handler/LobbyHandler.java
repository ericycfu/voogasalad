package server.communications_handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
	public LobbyHandler(Socket input, RTSServer server) {
		super(input, server);
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
				return MainPageHandler.CLASS_REF;
				case CHANGE_OPTION:
					currentLobby.changeTeam(in.readInt(), getSocket());
					return CLASS_REF;
				case START_GAME:
					if(getSocket().equals(currentLobby.getHost())) {
						currentLobby.setIsRunning(true);
					}
					return CLASS_REF;
				case ENTER_GAME:
					getOutputStream().writeObject(currentLobby.getCurrentGameInstance());
					return GameHandler.CLASS_REF;
				default: return CLASS_REF;
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
