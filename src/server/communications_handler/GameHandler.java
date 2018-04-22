package server.communications_handler;

import java.io.IOException;
import java.net.Socket;

import game_engine.GameInstance;
import server.RTSServer;

public class GameHandler extends CommunicationsHandler {
	private GameInstance runningGame;
	public GameHandler(Socket input, RTSServer server) {
		super(input, server);
		runningGame = server.findPlayer(input).getCurrentGameInstance();
	}

	public static final String CLASS_REF = "GAME";
	@Override
	public String updateServer() {
		try {
			String input;
			while((input = (String)getInputStream().readObject()) != null) {
				// TODO runningGame.executeCommand(source_id, target_id, i);
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
			getOutputStream().writeObject(runningGame);
		} catch (IOException e) {
		}

	}

}
