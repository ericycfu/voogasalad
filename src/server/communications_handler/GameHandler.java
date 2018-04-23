package server.communications_handler;

import java.io.IOException;
import java.net.Socket;

import game_engine.GameInstance;
import server.GameCommandInterpreter;
import server.RTSServer;

public class GameHandler extends CommunicationsHandler {
	private GameCommandInterpreter myInterpreter;
	private GameInstance runningGame;
	private int ID;
	public GameHandler(Socket input, RTSServer server, GameInstance run, int team_ID) {
		super(input, server);
		runningGame = run;
		ID = team_ID;
		myInterpreter = new GameCommandInterpreter(run);
	}

	public static final String CLASS_REF = "GAME";
	@Override
	public String updateServer() {
		try {
			String input;
			while((input = (String)getInputStream().readObject()) != null) {
				myInterpreter.executeCommand(input);
			}
			if(Math.random() < 0.5) //TODO is Game complete
				return CLASS_REF;
			return MainPageHandler.CLASS_REF;
		}
		catch(Exception e) {return CLASS_REF;}
	}

	@Override
	public void updateClient() {
		try {
			getOutputStream().writeObject(runningGame.getGameObjects());
			getOutputStream().writeObject(runningGame.getTeamManager().get(ID));
			getOutputStream().writeDouble(runningGame.getGameTime());
		} catch (IOException e) {
		}

	}

}
