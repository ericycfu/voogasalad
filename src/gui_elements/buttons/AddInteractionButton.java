package gui_elements.buttons;

public class AddInteractionButton extends MainButton {

	private static final String FILENAME = "add_interaction_button.properties";

	public AddInteractionButton() {
		super(FILENAME);
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
		});
	}
}