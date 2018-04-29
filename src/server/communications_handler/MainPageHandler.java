package server.communications_handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import game_engine.GameInstance;
import server.RTSServer;

public class MainPageHandler extends CommunicationsHandler {
	public static final String CLASS_REF = "MainPage";
	public MainPageHandler(Socket input, RTSServer server) {
		super(input, server);
	}

	@SuppressWarnings("unused")
	@Override
	public String updateServer() {
		try {
			Integer input;
			ObjectInputStream in = getInputStream();
			if(in == null)
				throw new SocketException("Client disconnected");
			if((input = in.readInt()) == null)
				return CLASS_REF;
			if(input == -1)
				getServer().addLobby(getSocket(), (GameInstance)in.readObject());
			else getServer().addToLobby(input, getSocket());
			System.out.println("Message received");
			return LobbyHandler.CLASS_REF;
		}
		catch(Exception e) {
			System.out.println("Oops");
			return CLASS_REF;}
	}

	@Override
	public void updateClient() throws SocketException {
		System.out.println(System.currentTimeMillis());
		try {
			ObjectOutputStream out = getOutputStream();
			if(out == null)
				throw new SocketException("Client disconnected");
			out.writeObject(
					getServer()
					.getLobbyManager());
			out.flush();
			System.out.println("Write success!");
		} catch (Exception e) {
		}
	}

}
