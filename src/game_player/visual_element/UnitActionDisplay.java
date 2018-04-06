package game_player.visual_element;

import javafx.scene.image.ImageView;

public class UnitActionDisplay {
	
	public static final int ACTION_GRID_LENGTH = 4;
	public static final int ACTION_GRID_WIDTH = 3;
	ImageView[][] myActionsGrid;
	
	public UnitActionDisplay(GameObject unit) {
		
	}
	
	private void initialize() {
		myActionsGrid = new ImageView[ACTION_GRID_LENGTH][ACTION_GRID_WIDTH];
		for (int i = 0; i < myActionsGrid.length; i++) {
			for (int j = 0; j < myActionsGrid[0].length; j++) {
				myActionsGrid[i][j] = new ImageView();
			}
		}
	}
	
	public void update(GameObject unit) {
		
	}
	
}
