package server.communications_handler;
/**
 * This class handles server-side communications for a player on the main page
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Logger;

import game_engine.GameInstance;
import server.RTSServer;
import server.RTSServerException;

public class MainPageHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "MainPage";
	public MainPageHandler(Socket input, RTSServer server, Logger logger) {
		super(input, server, logger);
	}

	@SuppressWarnings("unused")
	@Override
	public String updateServer() throws RTSServerException {
		try {
			Integer input;
			ObjectInputStream in = getInputStream();
			if(in == null)
				throw new RTSServerException("Client disconnected");
			if((input = in.readInt()) == null)
				return CLASS_REF;
			if(input == -1) {
				getServer().addLobby(getSocket(), (GameInstance)in.readObject());
				log("created a new lobby");
			}
			else {
				getServer().addToLobby(input, getSocket());
				log("joined lobby " + input);
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
			if(out == null)
				throw new RTSServerException("Client disconnected");
			out.writeObject(
					getServer()
					.getLobbyManager());
			out.flush();
		} catch (Exception e) {
		}
	}

}
