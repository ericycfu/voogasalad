package server.communications_handler;

import java.net.Socket;

import server.RTSServer;

public class GameHandler extends CommunicationsHandler {
	public GameHandler(Socket input, RTSServer server) {
		super(input, server);
		// TODO Auto-generated constructor stub  
	}

	public static final String CLASS_REF = "GAME";
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
