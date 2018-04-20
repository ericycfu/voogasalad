package server.communications_handler;

import java.net.Socket;

import server.RTSServer;

public abstract class CommunicationsHandler {
	private Socket communicationSocket;
	private RTSServer host;
	public CommunicationsHandler(Socket input, RTSServer server) {
		communicationSocket = input;
		host = server;
	}
	protected Socket getSocket() {
		return communicationSocket;
	}
	protected RTSServer getServer() {
		return host;
	}
	public abstract String updateServer();
	public abstract void updateClient();

}
