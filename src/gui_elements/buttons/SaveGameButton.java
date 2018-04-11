package gui_elements.buttons;

public class SaveGameButton extends MainButton {

	private static final String FILENAME = "save_game_button.properties";
	private static final boolean EXPLICIT_SET_ACTION = true;

	public SaveGameButton() {
		super(FILENAME, EXPLICIT_SET_ACTION);
	}

	@Override
	protected void setAction() {
		
	}
}
