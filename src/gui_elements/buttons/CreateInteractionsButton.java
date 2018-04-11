package gui_elements.buttons;

public class CreateInteractionsButton extends MainButton {

	private static final String FILENAME = "create_interactions_button.properties";

	public CreateInteractionsButton() {
		super(FILENAME);
	}

	@Override
	protected void setAction() {
		getButton().setOnAction(value -> {
		});
	}
}