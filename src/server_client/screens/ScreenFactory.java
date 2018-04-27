package server_client.screens;

import java.net.Socket;

import javafx.stage.Stage;
import server.RTSServerException;

public class ScreenFactory {
	public static final String PACKAGE_NAME = "server_client.screens.";
	private Socket dedicatedConnection;
	private Stage createdStage;
	public ScreenFactory(Socket connection, Stage primaryStage) {
		dedicatedConnection = connection;
		createdStage = primaryStage;
	}
	public ClientScreen get(String className) {
		try {
			Class<?> clazz = Class.forName(PACKAGE_NAME + className + "Screen");
			return (ClientScreen) clazz.getConstructor(Stage.class,Socket.class).newInstance(createdStage,dedicatedConnection);
		} catch (Exception e) {
			throw new RTSServerException("Unable to correctly handle input");
		}
	}
}
