package server.communications_handler;
import java.io.ObjectOutputStream;
/**
 * This factory creates a new CommunicationsHandler based on the given String
 */
import java.net.Socket;
import java.util.logging.Logger;

import server.RTSServer;
import server.RTSServerException;

public class CommunicationsHandlerFactory {
	public static final String PACKAGE_NAME = "server.communications_handler.";
	private RTSServer hostServer;
	private Socket connectionSocket;
	private Logger logger;
	public CommunicationsHandlerFactory(RTSServer server, Socket connection, Logger logger) {
		hostServer = server;
		connectionSocket = connection;
		this.logger = logger;
	}
	/**
	 * Returns the CommunicationsHandler from the given className
	 * @param className new class name
	 * @return corresponding Handler
	 */
	public CommunicationsHandler get(String className) {
		
		try {
			Class<?> clazz = Class.forName(PACKAGE_NAME + className + "Handler");
			return (CommunicationsHandler) clazz.getConstructor(Socket.class,RTSServer.class,Logger.class).newInstance(connectionSocket,hostServer,logger);
		} catch (Exception e) {
			throw new RTSServerException("Unable to correctly handle input");
		}
	}
}
