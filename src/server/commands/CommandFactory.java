package server.commands;
/**
 * Factory responsible for creating commands from the given String
 */
import game_engine.GameInstance;
import server.RTSServerException;

public class CommandFactory {
	public Command getCommand(String className, GameInstance g) {
		try {
			Class<?> clazz = Class.forName("server.commands." + className);
			return (Command) clazz.getConstructor(GameInstance.class).newInstance(g);
		} catch (Exception e) {
			throw new RTSServerException("Unable to correctly handle server input");
		}
	}
}
