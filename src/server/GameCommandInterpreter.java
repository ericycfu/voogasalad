package server;

import game_engine.GameInstance;
import server.commands.Command;
import server.commands.CommandFactory;

/**
 * This class is responsible for converting commands from a player to the 
 * @author andrew
 *
 */
public class GameCommandInterpreter {
	private CommandFactory myCommandFactory;
	private GameInstance myGameInstance;
	public GameCommandInterpreter(GameInstance g) {
		myGameInstance = g;
	}
	/**
	 * executes the input command on the Game
	 * @param s
	 */
	public void executeCommand(String s) {
		String[] array = s.split("\\s+");
		Command c = myCommandFactory.getCommand(array[0], myGameInstance);
		for(int x = 1; x <= c.howManyArguments(); x++) {
			c.addArg(array[x]);
		}
		if(array[0].equals("Chat")) {
			c.addArg(s.substring(4).substring(s.indexOf(" ") + 1));			
		}
		c.act();
	}
}
