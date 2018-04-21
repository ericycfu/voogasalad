package gui_elements.buttons;


public class PlayGameButton extends ImageButton {	
	public PlayGameButton() {
		setupText();
	}
	
//	public PlayGameButton(Stage stage) {
//		setupText();
//		this.setOnAction(e -> new MakeGameSelect(stage));
//	}
	
	private void setupText() {
		this.getStyleClass().add("make_game_button");
		this.setText("Play Game");
	}
	
}
