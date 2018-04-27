package server.communications_handler;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import game_engine.GameInstance;
import server.RTSServer;

public class MainPageHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "MainPage";
	public MainPageHandler(Socket input, RTSServer server) {
		super(input, server);
	}

	@Override
	public String updateServer() {
		try {
			int input;
			if((input = getInputStream().readInt()) == -1) {
				if(input == -1)
					getServer().addLobby(getSocket(), (GameInstance)getInputStream().readObject());
				else getServer().addToLobby(input, getSocket());
				return LobbyHandler.CLASS_REF;
			}
			else return CLASS_REF;
		}
		catch(Exception e) {return CLASS_REF;}
	}

	@Override
	public void updateClient() {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(getSocket().getOutputStream());
			out.writeObject(getServer());
		} catch (IOException e) {
			
		}
	}

}
