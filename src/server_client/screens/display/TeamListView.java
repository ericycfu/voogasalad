package server_client.screens.display;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListView;

public class TeamListView extends ListView<TeamDisplay> {
	public static final int PREF_HEIGHT = 400;
	public static final int PREF_WIDTH = 800;
	public TeamListView() {
		this.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
		this.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TeamDisplay>() {
			@Override
			public void changed(ObservableValue<? extends TeamDisplay> arg0, TeamDisplay arg1, TeamDisplay arg2) {
			}
		});
	}
}
