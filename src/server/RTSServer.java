package server;
/**
 * This class is the Server. The Server doesn't really have a front-end other than 1 alert but it does manage all the lobbies and all client connections
 * Server logging will come at a future date
 * @author andrew
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import game_engine.GameInstance;

public class RTSServer {
	public static final String DEFAULT_SERVER_IP = "10.197.116.66";
	public static final int PORT_NUMBER = 9098;
	public static final int RETRY_CONNECTION_DELAY = 10000;
	public static final String LOG_PATH = "log/";
	public static final String LOG_DATE_FORMAT = "YYYY-MM-dd_HH-mm-ss";
	public static final String CREATE_MSG_1 = "Server created at IP ";
	public static final String CREATE_MSG_2 = " on port ";
	
	public static final String ERROR_TITLE = "ERROR!";
	public static final String ERROR_BODY = "Unable to construct Logger";
	private ServerSocket myServerSocket;
	private LobbyManager myLobbyManager;
	private Logger myLogger;
	public RTSServer() {
		initialize();
	}
	/**
	 * Initializes the server and starts server threads
	 */
	public void initialize() {
		createLogger();
		createServer();
		listenForConnections();	 
	}
	/**
	 * Sets up the logger to write information to a new file
	 */
	private void createLogger() {
		myLogger = Logger.getLogger(getClass().getSimpleName());
		FileHandler fh;
		
		try {
			Format dateformatter = new SimpleDateFormat(LOG_DATE_FORMAT);
			fh = new FileHandler(LOG_PATH + dateformatter.format(new Date()));
			myLogger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter(); 
		    fh.setFormatter(formatter);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Initializes the ServerSocket and LobbyManager
	 */
	private void createServer() {
		myServerSocket = null;
		while(myServerSocket == null) {
			try {
				myServerSocket = new ServerSocket(PORT_NUMBER);
			}
			catch(IOException e) {
				try {
					Thread.sleep(RETRY_CONNECTION_DELAY);
				} catch (InterruptedException e1) {}
			}
		}
		try {
			myLogger.info(CREATE_MSG_1 + InetAddress.getLocalHost() + CREATE_MSG_2 + PORT_NUMBER);
		} catch (UnknownHostException e) {
		}
		
		myLobbyManager = new LobbyManager();
	}
	/**
	 * Starts and runs the thread that listens for clients and starts the ClientHandler
	 */
	private void listenForConnections() {
		new Thread(() -> {
            while (true) {
                try {
                    Socket socket = myServerSocket.accept();
                    myLogger.info("Connected to client IP " + socket.getInetAddress());
                    ClientHandler task = new ClientHandler(this, socket, myLogger);
                    Thread newThread = new Thread(task);
                    newThread.start();
                } catch (IOException e) {
                }
            }
       }).start();
	}
	/**
	 * Clears LobbyManager of empty lobbies
	 */
	public void cleanLobbyManager() {
		for(GameLobby g: myLobbyManager.getElements())
			if(g.getCurrentSize() == 0 || g.isRunning())
				myLobbyManager.removeElement(g);
	}
	/**
	 * Adds a new Lobby with the given host and the loaded GameInstance
	 * @param initial Player who creates the Lobby
	 * @param gi game to create lobby from
	 */
	public void addLobby(Socket initial, GameInstance gi) {
		myLobbyManager.addElementToManager(new GameLobby(initial, gi));
	}
	/**
	 * Adds the player to the Lobby with the given ID
	 * @param ID ID of lobby to add to
	 * @param s player to add
	 */
	public void addToLobby(int ID, Socket s) {
		myLobbyManager.get(ID).addPlayer(s);
	}
	/**
	 * Find the player in the LobbyManager
	 * @param s player to find
	 * @return Lobby containing the player
	 */
	public GameLobby findPlayer(Socket s) {
		return myLobbyManager.find(s);
	}
	public LobbyManager getLobbyManager() {
		return myLobbyManager;
	}
}
