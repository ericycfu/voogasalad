package server_client.screens;
/**
 * This class denotes a screen in the client that communicates with the server
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.stage.Stage;

public abstract class ClientScreen {
	private Socket connection;
	private Stage myStage;
	public ClientScreen(Stage primaryStage, Socket clientSocket) {
		connection = clientSocket;
		myStage = primaryStage;
		setUp();
	}
	/**
	 * This method is responsible for setting up all graphical parts of the screen
	 */
	protected abstract void setUp();
	/**
	 * This method is responsible for updating the screen based on information from the server.
	 * This method returns a String representing what screen should be displayed next by its CLASS_REF constant
	 * @return the CLASS_REF referring to the next screen to be displayed
	 */
	public abstract String updateSelf();
	
	protected Socket getSocket() {
		return connection;
	}
	/**
	 * Returns a new ObjectOutputStream from the connection socket
	 * @return
	 */
	protected ObjectOutputStream getOutputStream() {
		try {
			return new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * Returns a new ObjectInputStream from the connection socket
	 * @return
	 */
	protected ObjectInputStream getInputStream() {
		try {
			return new ObjectInputStream(new BufferedInputStream(connection.getInputStream()));
		} catch (IOException e) {
			return null;
		}
	}
	protected Stage getStage() {
		return myStage;
	}
}
