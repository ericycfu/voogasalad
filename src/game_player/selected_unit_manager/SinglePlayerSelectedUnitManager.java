package game_player.selected_unit_manager;

import game_engine.Team;
import game_object.GameObject;
import game_object.GameObjectManager;
import pathfinding.GridMap;
import transform_library.Vector2;

public class SinglePlayerSelectedUnitManager extends SelectedUnitManager {

	public SinglePlayerSelectedUnitManager(Team team) {
		super(team);
	}

	@Override
	public void move(Vector2 target, GameObjectManager gom, GridMap gridmap) {
		mySelectedUnits.stream()
		.filter(o -> !o.getTransform().getPosition().matches(target))
		.forEach(o -> o.queueMovement(target, gom, gridmap));
	}

	@Override
	public void interact(GameObject source, GameObject target, int interactionID, GameObjectManager gom,
			GridMap gridmap, Vector2 location) {
		source.queueInteraction(target, interactionID, gom, gridmap, location);
	}

	@Override
	public void build(GameObject source, GameObject target, int interactionID, GameObjectManager gom, GridMap gridmap,
			Vector2 location) {
		source.queueInteraction(target, interactionID, gom, gridmap, location);	
	}
	
}
