package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PlayerClient extends Stage {
	public static final int RETRY_CONNECTION_DELAY = 5000;
	public void initialize() {
		connectToServer();
	}

	private void connectToServer() {
		Socket connection = null;
		while(connection == null) {
			try {
				connection = new Socket(RTSServer.SERVER_IP, RTSServer.PORT_NUMBER);
			}
			catch(IOException e) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("");
				alert.setContentText("Unable to connect to server. Retrying.");
				alert.show();
				try {
					Thread.sleep(RETRY_CONNECTION_DELAY);
				} catch (InterruptedException e1) {}
			}
		}
	}
}
