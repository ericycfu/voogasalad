package server.communications_handler;

import java.net.Socket;
import server.RTSServer;
import server.RTSServerException;

public class CommunicationsHandlerFactory {
	public static final String PACKAGE_NAME = "server.communications_handler.";
	private RTSServer hostServer;
	private Socket connectionSocket;
	public CommunicationsHandlerFactory(RTSServer server, Socket connection) {
		hostServer = server;
		connectionSocket = connection;
	}
	public CommunicationsHandler get(String className) {
		
		try {
			Class<?> clazz = Class.forName(PACKAGE_NAME + className + "Handler");
			return (CommunicationsHandler) clazz.getConstructor(Socket.class,RTSServer.class).newInstance(connectionSocket,hostServer);
		} catch (Exception e) {
			throw new RTSServerException("Unable to correctly handle input");
		}
	}
}
