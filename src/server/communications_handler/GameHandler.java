package server.communications_handler;
/**
 * This class handles communications while the player is in game 
 * @author andrew
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Logger;

import game_engine.GameInstance;
import server.GameCommandInterpreter;
import server.GameLobby;
import server.RTSServer;
import server.RTSServerException;

public class GameHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "Game";
	private GameCommandInterpreter myInterpreter;
	private GameInstance runningGame;
	private GameLobby runningGameLobby;
	private int player_ID;
	private int team_ID;
	public GameHandler(Socket input, RTSServer server, Logger logger) {
		super(input, server, logger);
		runningGameLobby = server.findPlayer(input);
		runningGame = runningGameLobby.getCurrentGameInstance();
		player_ID = runningGameLobby.getPlayerID(input);
		myInterpreter = new GameCommandInterpreter(runningGame,player_ID);
		team_ID = runningGameLobby.getTeamID(input);
		runningGame.play();
	}
	@Override
	public String updateServer() {
		try {
			String input;
			ObjectInputStream in = getInputStream();
			if((input = (String)in.readObject()) != null) {
				System.out.println(input);
				if(input.split("\\s+")[0].equals("Leave")) {
					runningGameLobby.remove(getSocket());
					return MainPageHandler.CLASS_REF;
				}
				myInterpreter.executeCommand(input);

			}
			return CLASS_REF;
		}
		catch(SocketException e1) {
			throw new RTSServerException(CommunicationsHandler.DISCONNECT_MESSAGE);
		}
		catch(IOException | ClassCastException | ClassNotFoundException e) {
			return CLASS_REF;}
	}

	@Override
	public void updateClient() {
		if(runningGame.getIsRunning()) {
		try {
			ObjectOutputStream out = getOutputStream();
			out.writeObject(runningGame);
			out.writeInt(team_ID);
			out.flush();
		} catch (SocketException e) {
			throw new RTSServerException(CommunicationsHandler.DISCONNECT_MESSAGE);
		} catch (IOException e) {
			return;
		}
		}
	}

}
