package server.communications_handler;

import java.net.Socket;

import server.RTSServer;

public class LobbyHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "Lobby";
	public LobbyHandler(Socket input, RTSServer server) {
		super(input, server);
	}

	@Override
	public String updateServer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateClient() {
		// TODO Auto-generated method stub

	}

}
