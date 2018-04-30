package server_client.screens;

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
	protected abstract void setUp();
	public abstract String updateSelf();
	
	protected Socket getSocket() {
		return connection;
	}
	protected ObjectOutputStream getOutputStream() {
		try {
			return new ObjectOutputStream(new BufferedOutputStream(connection.getOutputStream()));
		} catch (IOException e) {
			return null;
		}
	}
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
