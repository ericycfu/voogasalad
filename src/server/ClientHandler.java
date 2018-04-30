package server;
/**
 * This class runs a thread handling all communciations on the server side with a particular client
 * @author andrew
 */
import java.net.Socket;
import java.net.SocketException;

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
			}
		}).start();
	}

}
