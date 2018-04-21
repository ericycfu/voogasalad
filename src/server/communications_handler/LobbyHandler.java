package server.communications_handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.GameLobby;
import server.RTSServer;

public class LobbyHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "Lobby";
	private BufferedReader in;
	private GameLobby currentLobby;
	public LobbyHandler(Socket input, RTSServer server) {
		super(input, server);
		currentLobby = server.findPlayer(input);
	}

	@Override
	public String updateServer() {
		try {
			String input;
			if((input = in.readLine()) != null) {
				switch(input) {
					case "remove": currentLobby.removePlayer(getSocket());
									return MainPageHandler.CLASS_REF;
					case "change": if(getSocket().equals(currentLobby.getHost())) {
									//todo load new GameInstance from file
									}
									return CLASS_REF;
					case "start_game":
									if(getSocket().equals(currentLobby.getHost())) {
										currentLobby.setIsReady(true);
									}
									return LobbyHandler.CLASS_REF;
					case "enter_game":
									//todo send GameInstance to client
									return GameHandler.CLASS_REF;
					default: return CLASS_REF;
				}
			}
			else return CLASS_REF;
		}
		catch(IOException e) {return CLASS_REF;}
	}

	@Override
	public void updateClient() {
		try {
			ObjectOutputStream objwrite = new ObjectOutputStream(getSocket().getOutputStream());
			if(currentLobby.isReady())
				objwrite.writeObject("START");
			else objwrite.writeObject(currentLobby);
		} catch (IOException e) {
			return;
		}
	}

}
