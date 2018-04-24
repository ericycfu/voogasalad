package game_player.visual_element;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class ChatBox {
	
	private static final double CHATHISTORYRATIO = 0.8;
	private static final double INPUTBOXRATIO = 0.2;
	private static final String LINEBREAK = "\n";
	
	private Group myGroup;
	private TextArea myInputBox;
	private TextArea myChatHistory;
	
	public ChatBox(double width, double height) {
		myGroup = new Group();
		initMyChatHistory(width, height);
		initMyInputBox(width, height);
	}

	private void initMyChatHistory(double width, double height) {
		myChatHistory = new TextArea();
		myChatHistory.setVisible(false);
		myChatHistory.setPrefWidth(width);
		myChatHistory.setPrefHeight(height * CHATHISTORYRATIO);
		myGroup.getChildren().add(myChatHistory);
	}
	
	private void initMyInputBox(double width, double height) {
		myInputBox = new TextArea();
		myInputBox.setOnMouseEntered(e -> myChatHistory.setVisible(true));
		myInputBox.setOnMouseExited(e -> myChatHistory.setVisible(false));
		myInputBox.setOnKeyPressed(event -> handleKeyInput(event));
		myInputBox.setLayoutY(myChatHistory.getPrefHeight());
		myInputBox.setPrefWidth(width);
		myInputBox.setPrefHeight(height * INPUTBOXRATIO);
		myGroup.getChildren().add(myInputBox);
	}
	
	private void handleKeyInput(KeyEvent event) {
		KeyCombination keyComb1 = new KeyCodeCombination(KeyCode.ENTER, KeyCombination.CONTROL_DOWN);
		
		if(keyComb1.match(event)) {
    		// send to server;
			displayText(myInputBox.getText()); // for testing purpose
			myInputBox.clear();
    	}
	}


	public void displayText(String text) {
		myChatHistory.appendText(text + LINEBREAK);
	}
	
	public Node getGroup() {
		return myGroup;
	}
}
