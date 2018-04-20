package server;

import java.net.Socket;

import server.communications_handler.CommunicationsHandler;
import server.communications_handler.CommunicationsHandlerFactory;

public class ClientHandler implements Runnable {
	private CommunicationsHandlerFactory myCHFactory;
	private CommunicationsHandler myCommunicationsHandler;
	public ClientHandler(RTSServer server, Socket socket) {
		myCHFactory = new CommunicationsHandlerFactory(server);
		myCommunicationsHandler = myCHFactory.get("Main", socket);
	}
	@Override
	public void run() {
		myCommunicationsHandler.updateServer();
		myCommunicationsHandler.updateClient();
		
	}

}
