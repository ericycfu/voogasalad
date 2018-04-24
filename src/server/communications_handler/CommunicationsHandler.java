package server.communications_handler;
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
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public CommunicationsHandler(Socket input, RTSServer server) {
		communicationSocket = input;
		host = server;
		try {
			in = new ObjectInputStream(input.getInputStream());
			out = new ObjectOutputStream(input.getOutputStream());
		} catch (IOException e) {
			throw new RTSServerException("Communication between server and client failed");
		}
	}
	protected Socket getSocket() {
		return communicationSocket;
	}
	protected RTSServer getServer() {
		return host;
	}
	protected ObjectInputStream getInputStream() {
		return in;
	}
	protected ObjectOutputStream getOutputStream() {
		return out;
	}
	public abstract String updateServer();
	public abstract void updateClient();

}
