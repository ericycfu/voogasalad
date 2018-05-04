package server;
import java.net.InetAddress;
/**
 * This class runs a thread handling all communications on the server side with a particular client
 * @author andrew
 */
import java.net.Socket;
import java.util.logging.Logger;

import server.communications_handler.CommunicationsHandler;
import server.communications_handler.CommunicationsHandlerFactory;
import server.communications_handler.MainPageHandler;

public class ClientHandler implements Runnable {
	private CommunicationsHandlerFactory myCHFactory;
	private CommunicationsHandler myCommunicationsHandler;
	public static final String DISCONNECT_LOG = " has disconnected";
	private InetAddress inetAddress;
	private Logger logger;
	public ClientHandler(RTSServer server, Socket socket, Logger logger) {
		this.logger = logger;
		inetAddress = socket.getInetAddress();
		myCHFactory = new CommunicationsHandlerFactory(server,socket,logger);
		myCommunicationsHandler = myCHFactory.get(MainPageHandler.CLASS_REF);
	}
	@Override
	public void run() {
		new Thread(() -> {
			try {
			while(true) {
					String newHandler =  myCommunicationsHandler.updateServer();
				if(!myCommunicationsHandler.getClass().getSimpleName().startsWith(newHandler))
					myCommunicationsHandler = myCHFactory.get(newHandler);
			}
			}
			catch(RTSServerException e) {
				Thread.currentThread().interrupt();
			}
		}).start();
		new Thread(() -> {
			try {
				while(true) {
					myCommunicationsHandler.updateClient();
				}
			}
			catch(RTSServerException e) {
				Thread.currentThread().interrupt();
				logger.info(inetAddress + DISCONNECT_LOG);
			}
		}).start();
	}

}
