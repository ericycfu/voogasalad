package server_client.screens.display;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;
import server.GameLobby;

public class LobbyListView extends ListView<LobbyDisplay>{
	public static final int PREF_HEIGHT = 400;
	public static final int PREF_WIDTH = 800;
	public LobbyListView() {
		super();
		this.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LobbyDisplay>() {
			@Override
			public void changed(ObservableValue<? extends LobbyDisplay> arg0, LobbyDisplay arg1, LobbyDisplay arg2) {
			}
		});
	}
	public void remove() {
		this.getItems().remove(0);
	}
	public void add() {
		this.getItems().add(new LobbyDisplay());
	}
	public void update(int x, GameLobby g) {
		this.getItems().get(x).update(g);
	}
}
