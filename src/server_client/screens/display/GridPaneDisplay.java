package server_client.screens.display;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import server.GameLobby;

public abstract class GridPaneDisplay extends GridPane {
	public static final int DEFAULT_HEIGHT = 100;
	public static final int DEFAULT_WIDTH = 700;
	public static final int INSET_SIZE = 10;
	public GridPaneDisplay() {
		setPrefHeight(DEFAULT_HEIGHT);
		setPrefWidth(DEFAULT_WIDTH);
		setHgap(0);
	    setVgap(0);
	    setPadding(new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE));
	}
	public abstract void update(GameLobby toDisplay);
}
