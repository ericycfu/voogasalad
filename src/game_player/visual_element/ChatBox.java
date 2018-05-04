package game_player.visual_element;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.net.Socket;

import game_player.GamePlayer;
import game_player.alert.AlertMaker;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

/**
 * This class allows the in-game conversation implementation for the Player. Multi-player mode requires the server to transmit the strings sent from each player to all the currently in-game players. 
 * 
 * @author Eddie Yang
 *
 */
public class ChatBox {
	
	private static final String PROMPT = "Enter here to chat: ";
	private static final String CHAT = "Chat";
	private static final double CHATBOXOPACITY = 0.7;
	private static final double CHATHISTORYRATIO = 0.8;
	private static final double INPUTBOXRATIO = 0.2;
	
	private Group myGroup;
	private TextArea myInputBox;
	private TextArea myChatHistory;
	
	/**
	 * The constructor of the chat box takes the socket for communication purposes and its size parameters. 
	 * 
	 * @param socket
	 * @param width
	 * @param height
	 */
	public ChatBox(Socket socket, double width, double height) {
		myGroup = new Group();
		initMyChatHistory(width, height);
		initMyInputBox(socket, width, height);
	}

	private void initMyChatHistory(double width, double height) {
		myChatHistory = new TextArea();
		myChatHistory.setVisible(false);
		myChatHistory.setPrefWidth(width);
		myChatHistory.setPrefHeight(height * CHATHISTORYRATIO);
		myChatHistory.setOpacity(CHATBOXOPACITY);
		myGroup.getChildren().add(myChatHistory);
	}
	
	private void initMyInputBox(Socket socket, double width, double height) {
		myInputBox = new TextArea();
		myInputBox.setPromptText(PROMPT);
		myInputBox.setOnMouseEntered(e -> myChatHistory.setVisible(true));
		myInputBox.setOnMouseExited(e -> myChatHistory.setVisible(false));
		myInputBox.setOnKeyPressed(event -> handleKeyInput(socket, event));
		myInputBox.setLayoutY(myChatHistory.getPrefHeight());
		myInputBox.setPrefWidth(width);
		myInputBox.setPrefHeight(height * INPUTBOXRATIO);
		myInputBox.setOpacity(CHATBOXOPACITY);
		myGroup.getChildren().add(myInputBox);
	}
	
	private void handleKeyInput(Socket socket, KeyEvent event) {
		KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
		if(keyComb1.match(event)) {
			ObjectOutputStream outstream = GamePlayer.getObjectOutputStream(socket);
			String msg = CHAT + GamePlayer.SPACE + myInputBox.getText();
			try {
				outstream.writeObject(msg);
				outstream.flush();
			} catch (IOException e) { 
				AlertMaker.makeAlert(GamePlayer.SERVERALERTHEAD, GamePlayer.SERVERALERTBODY);
			}
			myInputBox.clear();
    	}
	}
	
	/**
	 * This is called by the GamePlayer class so that the string is constantly set by the user. 
	 * 
	 * @param text
	 */
	public void displayText(String text) {
		myChatHistory.appendText(text + GamePlayer.LINEBREAK);
	}
	
	/**
	 * This returns the group to higher-hierarchy classes. 
	 * 
	 * @return
	 */
	public Node getNodes() {
		return myGroup;
	}
}
