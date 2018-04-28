package server_client.screens;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javafx.stage.Stage;
import server.RTSServerException;

public abstract class ClientScreen {
	private Socket connection;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Stage myStage;
	public ClientScreen(Stage primaryStage, Socket clientSocket) {
		connection = clientSocket;
		myStage = primaryStage;
		try {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
		}
		catch(Exception e) {
			throw new RTSServerException("Unable to connect to server");
		}
		setUp();
	}
	protected abstract void setUp();
	public abstract String updateSelf();
	
	protected Socket getSocket() {
		return connection;
	}
	protected ObjectOutputStream getOutputStream() {
		return out;
	}
	protected ObjectInputStream getInputStream() {
		return in;
	}
	protected Stage getStage() {
		return myStage;
	}
}
