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

public class ChatBox {
	
	private static final String PROMPT = "Enter here to chat: ";
	private static final String CHAT = "Chat";
	private static final double CHATBOXOPACITY = 0.7;
	private static final double CHATHISTORYRATIO = 0.8;
	private static final double INPUTBOXRATIO = 0.2;
	
	private Group myGroup;
	private TextArea myInputBox;
	private TextArea myChatHistory;
	
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
			} catch (IOException e) { new AlertMaker(GamePlayer.SERVERALERTHEAD, GamePlayer.SERVERALERTBODY);}
			myInputBox.clear();
    		}
	}

	public void displayText(String text) {
		myChatHistory.appendText(text + GamePlayer.LINEBREAK);
	}
	
	public Node getNodes() {
		return myGroup;
	}
}
