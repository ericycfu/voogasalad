package server.commands;

import java.util.ArrayList;
import java.util.List;

import game_engine.GameInstance;

/**
 * This class encodes a command that can be executed on the server
 * @author andrew
 *
 */
public abstract class Command {
	private List<String> args;
	private int numArgs;
	private GameInstance game; 
	public Command(int numVars, GameInstance g) {
		numArgs = numVars;
		args = new ArrayList<>();
		game  = g;
	}
	/**
	 * This method executes an action specified by subclasses of this object
	 */
	public abstract void act();
	/**
	 * Adds an argument to the command
	 * @param newArg
	 */
	public void addArg(String newArg) {
		args.add(newArg);
	}
	/**
	 * Return the number of arguments the command takes
	 * @return
	 */
	public int howManyArguments() {
		return numArgs;
	}
	/**
	 * Gets the argument at the given index directly
	 * @param index index of the argument
	 * @return the String argument
	 */
	protected String getArgString(int index){
		return args.get(index);
	}
	/**
	 * Gets the integer value of a command 
	 * @param index index of the argument
	 * @return int the int value of an argument
	 */
	protected int getArgValue(int index) {
		String s = getArgString(index);
		try {
			return (int)Double.parseDouble(s);
		}
		catch(NumberFormatException e) {
			throw new CommandException("Argument is not an integer");
		}
	}
	protected GameInstance getGameInstance() {
		return game;
	}
	
	
}
