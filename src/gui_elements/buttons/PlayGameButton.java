package gui_elements.buttons;

public class PlayGameButton extends MainButton {
	
	private static final String FILENAME = "play_game_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = true;

	public PlayGameButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
	}

	@Override
	protected void setAction() {
		
	}
}
