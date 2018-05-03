package server.communications_handler;
/**
 * This class handles server-side communications for a player on the main page
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import game_engine.GameInstance;
import server.RTSServer;
import server.RTSServerException;

public class MainPageHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "MainPage";
	public static final String LOG_LOBBY_CREATION = "created a new lobby from map ";
	public static final String LOG_JOIN_LOBBY = "joined lobby ";
	public MainPageHandler(Socket input, RTSServer server, Logger logger) {
		super(input, server, logger);
	}

	@SuppressWarnings("unused")
	@Override
	public String updateServer() throws RTSServerException {
		try {
			Integer input;
			ObjectInputStream in = getInputStream();
			if((input = in.readInt()) == null)
				return CLASS_REF;
			if(input == -1) {
				getServer().addLobby(getSocket(), (GameInstance)in.readObject());
				log(LOG_LOBBY_CREATION);
			}
			else {
				getServer().addToLobby(input, getSocket());
				log(LOG_JOIN_LOBBY + input);
			}
			return LobbyHandler.CLASS_REF;
		}
		catch(IOException | ClassNotFoundException e) {
			return CLASS_REF;}
	}

	@Override
	public void updateClient() throws RTSServerException {
		getServer().cleanLobbyManager();
		try {
			ObjectOutputStream out = getOutputStream();
			out.writeObject(
					getServer()
					.getLobbyManager());
			out.flush();
		} 
		catch(SocketException e) {
			throw new RTSServerException(CommunicationsHandler.DISCONNECT_MESSAGE);
		}
		catch (IOException e) {
			return;
		}
	}

}
