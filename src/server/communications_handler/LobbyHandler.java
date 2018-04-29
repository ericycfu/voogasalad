package server.communications_handler;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import server.GameLobby;
import server.RTSServer;

public class LobbyHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "Lobby";
	public static final String REMOVE_OPTION = "Leave";
	public static final String START_GAME = "Start";
	public static final String ENTER_GAME = "Play";
	private GameLobby currentLobby;
	public LobbyHandler(Socket input, RTSServer server) {
		super(input, server);
		currentLobby = server.findPlayer(input);
	}

	@Override
	public String updateServer() {
		try {
			String input;
			if((input = (String)getInputStream().readObject()) != null) {
				switch(input) {
					case REMOVE_OPTION: currentLobby.removePlayer(getSocket());
									ObjectOutputStream out = getOutputStream();
									out.writeObject("Left");
									out.flush();
									return MainPageHandler.CLASS_REF;
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
		catch(Exception e) {return CLASS_REF;}
	}

	@Override
	public void updateClient() throws SocketException{
		try {
			ObjectOutputStream out = getOutputStream();
			if(out == null)
				throw new SocketException("Disconnected");
			System.out.println("Writing lobby");
			if(currentLobby.isRunning())
				out.writeObject(START_GAME);
			else { out.writeObject(currentLobby);
				out.writeInt(currentLobby.getTeamID(getSocket()));
				out.writeInt(currentLobby.getPlayerID(getSocket()));
			}
			out.flush();
			System.out.println("Lobby writing finished");
		} catch (Exception e) {
			return;
		}
	}

}
