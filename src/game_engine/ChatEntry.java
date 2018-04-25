package game_engine;

import java.io.Serializable;

public class ChatEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double time;
	private int source_ID;
	private String message;
	public ChatEntry(double timestamp, int ID, String messageText) {
		time = timestamp;
		source_ID = ID;
		message = messageText;
	}
	public String convertToString() {
		return "Player " + source_ID + ": " + message;
	}
	public double getTime() {
		return time;
	}
	public double getPlayerID() {
		return source_ID;
	}
	public String getMessage() {
		return message;
	}
	
}
