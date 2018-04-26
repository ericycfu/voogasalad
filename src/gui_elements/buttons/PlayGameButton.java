package gui_elements.buttons;

public class PlayGameButton extends ImageButton {	
	private static final String RESOURCES_STRING = "AUTHOR_LOCATION_MAP";
	private static final String INITIAL_MAP_STRING = "INITIALIZATION_LOCATION_MAP";
	private static final String INITIAL_LIST_STRING = "INITIALIZATION_LOCATION_LIST";
	public PlayGameButton() {
		setupText();
		
	}
	
	public PlayGameButton(Stage stage) {
		setupText();
	//	this.setOnAction(e -> );
	}
	
	private void setupText() {
		this.getStyleClass().add("make_game_button");
		this.setText("Play Game");
	}
	
	protected void setAction() {
		getButton().setOnAction(value -> {
			myWriter = new Writer();
//			myReader = new Reader();
			try {
				myWriter.write(Resources.getString(RESOURCES_STRING), **MAPENTITY GOES HERE**);
				GameObjectManager myGOM = AuthoringToGameObject.convertMap(**MAPENTITY GOES HERE**);
				myWriter.write(Resources.getString(INITIAL_MAP_STRING));
				List<GameObjects> possibleObjects = AuthoringToGameObject.convertList(CreatedObjects.getAuthoringObjects());
				myWriter.write(Resources.getString(INITIAL_LIST_STRING));
				GamePlayer gamePlayer = new GamePlayer(myGOM);
				System.out.println("Object saved");
			} catch (IOException e) {
				System.err.println("Could not save created authoring objects");
			}
			
		});
	}
	
}
