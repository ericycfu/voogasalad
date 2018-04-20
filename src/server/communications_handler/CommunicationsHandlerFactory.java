package server.communications_handler;

import java.net.Socket;
import server.RTSServer;
import server.RTSServerException;

public class CommunicationsHandlerFactory {
	private RTSServer hostServer;
	public CommunicationsHandlerFactory(RTSServer server) {
		hostServer = server;
	}
	public CommunicationsHandler get(String className, Socket connection) {
		
		try {
			Class<?> clazz = Class.forName("server.communications_handler" + className);
			return (CommunicationsHandler) clazz.getConstructor(Socket.class,RTSServer.class).newInstance(connection,hostServer);
		} catch (Exception e) {
			throw new RTSServerException("Unable to correctly handle input");
		}
	}
}
