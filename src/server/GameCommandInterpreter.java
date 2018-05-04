package server;

import game_engine.GameInstance;
import server.commands.Command;
import server.commands.CommandFactory;

/**
 * This class is responsible for converting commands from a player to actions in the game
 * @author andrew
 *
 */
public class GameCommandInterpreter {
	public static final String CHAT_OPTION = "Chat";
	private CommandFactory myCommandFactory;
	private GameInstance myGameInstance;
	private int playerID;
	public GameCommandInterpreter(GameInstance g, int player_ID) {
		myGameInstance = g;
		playerID = player_ID;
		myCommandFactory = new CommandFactory();
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
			c.addArg(Integer.toString(playerID));
			c.addArg(s.substring(4).substring(s.indexOf(" ") + 1));	
		}
		c.act();
	}
}
