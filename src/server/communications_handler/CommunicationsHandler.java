package server.communications_handler;
/**
 * Denotes a server class responsible for communicating and processing information from the client
 * @author andrew
 */
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
import java.util.logging.Logger;

import server.RTSServer;
import server.RTSServerException;

public abstract class CommunicationsHandler {
	private Socket communicationSocket;
	private RTSServer host;
	private Logger myLogger;
	public CommunicationsHandler(Socket input, RTSServer server, Logger logger) {
		communicationSocket = input;
		host = server;
		myLogger = logger;
	}
	protected Socket getSocket() {
		return communicationSocket;
	}
	protected RTSServer getServer() {
		return host;
	}
	/**
	 * Makes a new ObjectInputStream and returns it
	 * @return new ObjectInputStream
	 */
	protected ObjectInputStream getInputStream() {
		try {
			return new ObjectInputStream(new BufferedInputStream(getSocket().getInputStream()));
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * Makes a new ObjectOutputStream and returns it
	 * @return new ObjectOutputStream
	 */
	protected ObjectOutputStream getOutputStream() {
		try {
			return new ObjectOutputStream(new BufferedOutputStream(getSocket().getOutputStream()));
		} catch (IOException e) {
			return null;
		}
	}
	protected Logger getLogger() {
		return myLogger;
	}
	protected void log(String message) {
		myLogger.info(getSocket().getInetAddress() + ": " + message);
	}
	/**
	 * @return This method processes information from the client and updates the server as necessary
	 * @throws RTSServerException if a disconnect occurs
	 */
	public abstract String updateServer() throws RTSServerException;
	/**
	 * This method writes information about the server to the client
	 * @throws RTSServerException if a disconnect occurs
	 */
	public abstract void updateClient() throws RTSServerException;

}
