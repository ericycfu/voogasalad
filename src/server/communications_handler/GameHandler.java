package server.communications_handler;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import game_engine.GameInstance;
import server.GameCommandInterpreter;
import server.GameLobby;
import server.RTSServer;

public class GameHandler extends CommunicationsHandler {
	private GameCommandInterpreter myInterpreter;
	private GameInstance runningGame;
	private GameLobby runningGameLobby;
	private int player_ID;
	private int team_ID;
	public GameHandler(Socket input, RTSServer server) {
		super(input, server);
		runningGameLobby = server.findPlayer(input);
		runningGame = runningGameLobby.getCurrentGameInstance();
		player_ID = runningGameLobby.getPlayerID(input);
		myInterpreter = new GameCommandInterpreter(runningGame);
	}

	public static final String CLASS_REF = "GAME";
	@Override
	public String updateServer() {
		try {
			String input;
			while((input = (String)getInputStream().readObject()) != null) {
				if(!input.split("\\s+")[0].equals("Leave")) {
					runningGameLobby.remove(getSocket());
					return MainPageHandler.CLASS_REF;
				}
				if(input.equals("Save")) {
					if(!runningGame.getIsRunning())
						getOutputStream().writeObject(runningGame);
					return CLASS_REF;
				}
				myInterpreter.executeCommand(input);
			}
			// if(runningGameLobby.isTeamEmpty(runningGameLobby.getTeamID(getSocket()))
			// update the game loss condition for that team
			// TODO if the game has ended, return MainPageHandler.CLASS_REF;
			return CLASS_REF;
		}
		catch(Exception e) {return CLASS_REF;}
	}

	@Override
	public void updateClient() {
		if(runningGame.getIsRunning()) {
		try {
			getOutputStream().writeObject(runningGame.getGameObjects());
			getOutputStream().writeObject(runningGame.getTeamManager().get(team_ID));
			getOutputStream().writeDouble(runningGame.getGameTime());
			getOutputStream().writeObject(runningGame.getChat());
		} catch (IOException e) {
		}
		}
	}

}
