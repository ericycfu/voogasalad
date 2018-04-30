package server.communications_handler;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
/**
 * This class is responsible handling the server side of a specific server/client interaction
 * @author andrew
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.RTSServer;
import server.RTSServerException;

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
	protected ObjectInputStream getInputStream() {
		try {
			return new ObjectInputStream(new BufferedInputStream(getSocket().getInputStream()));
		} catch (IOException e) {
			return null;
		}
	}
	protected ObjectOutputStream getOutputStream() {
		try {
			return new ObjectOutputStream(new BufferedOutputStream(getSocket().getOutputStream()));
		} catch (IOException e) {
			return null;
		}
	}
	public abstract String updateServer() throws RTSServerException;
	public abstract void updateClient() throws RTSServerException;

}
