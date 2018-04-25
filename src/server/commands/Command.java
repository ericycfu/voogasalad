package server.commands;

import java.util.List;

import game_engine.GameInstance;

/**
 * This class encodes a 
 * @author andrew
 *
 */
public abstract class Command {
	private List<String> args;
	private int numArgs;
	private GameInstance game; 
	public Command(int numVars, GameInstance g) {
		numArgs = numVars;
		game  = g;
	}
	public abstract void act();
	public void addArg(String newArg) {
		args.add(newArg);
	}
	public int howManyArguments() {
		return numArgs;
	}
	protected String getArgString(int index){
		return args.get(index);
	}
	protected int getArgValue(int index) {
		String s = getArgString(index);
		try {
			return Integer.parseInt(s);
		}
		catch(NumberFormatException e) {
			throw new CommandException("Argument is not an integer");
		}
	}
	protected GameInstance getGameInstance() {
		return game;
	}
	
	
}
