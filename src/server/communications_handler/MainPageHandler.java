package server.communications_handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.RTSServer;
import server.RTSServerException;

public class MainPageHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "MainPage";
	private BufferedReader in;
	public MainPageHandler(Socket input, RTSServer server) {
		super(input, server);
		try {
			in = new BufferedReader(
					new InputStreamReader(getSocket().getInputStream()));
		} catch (IOException e) {
			throw new RTSServerException("Unable to connect to server");
		}
	}

	@Override
	public String updateServer() {
		try {
			String input;
			if((input = in.readLine()) != null) {
				int lobbyID = Integer.parseInt(input);
				if(lobbyID == -1)
					getServer().addLobby(getSocket(), null);
				else getServer().addToLobby(lobbyID, getSocket());
				return LobbyHandler.CLASS_REF;
			}
			else return CLASS_REF;
		}
		catch(IOException e) {return CLASS_REF;}
		
	}

	@Override
	public void updateClient() {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(getSocket().getOutputStream());
			out.writeObject(getServer());
		} catch (IOException e) {
			
		}
	}

}
