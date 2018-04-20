package server;

import java.net.Socket;

import server.communications_handler.CommunicationsHandler;
import server.communications_handler.CommunicationsHandlerFactory;
import server.communications_handler.MainPageHandler;

public class ClientHandler implements Runnable {
	private CommunicationsHandlerFactory myCHFactory;
	private CommunicationsHandler myCommunicationsHandler;
	public ClientHandler(RTSServer server, Socket socket) {
		myCHFactory = new CommunicationsHandlerFactory(server,socket);
		myCommunicationsHandler = myCHFactory.get(MainPageHandler.CLASS_REF);
	}
	@Override
	public void run() {
		String newHandler =  myCommunicationsHandler.updateServer();
		myCommunicationsHandler.updateClient();
		myCommunicationsHandler = myCHFactory.get(newHandler);
		
	}

}
