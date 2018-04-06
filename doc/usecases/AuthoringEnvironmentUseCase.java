package voogasalad_raptiltswagger;

import java.util.List;
import java.util.Map;

public class AuthoringEnvironmentUseCase {

	private GameObject gameObj;
	private Map<String, ?> interactions = new Map<String, ?>();
	private List<GameObject> interaction_components - new List<GameObject>();
	private double health, x_loc, y_loc, interaction_quantity;
	private String interaction_type;
	
	/*
	 * Constructor for dealing with interactions
	 */
	public AuthoringEnvironmentUseCase(double health, double x_loc, double y_loc, List<GameObject> interaction_components,
			String interaction_type, double interaction_quantity) {
		this.health = health;
		this.x_loc = x_loc;
		this.y_loc = y_loc;
		this.interaction_components = interaction_components;
		this.interaction_type = interaction_type;
		this.interaction_quantity = interaction_quantity;
		initialize();
	}
	
	/*
	 * Calls classes that sets game object attributes and sets interaction components.
	 */
	private void initialize() {
		setGameObjectAttributes();
		setInteractionComponents();
	}
	
	/*
	 * Sets the game object attributes
	 */
	private void setGameObjectAttributes() {
		gameObj.setHealth(health);
		gameObj.setX(x_loc);
		gameObj.setY(y_loc);
		gameObj.setInteractions(interactions);
		gameObj.setInteractionComponents(interaction_components);		
	}
	
	/*
	 * Sets the interaction components the gameObject object interacts with
	 */
	private void setInteractionComponents() {
		interactions.put("type", interaction_type);
		interactions.put("quantity", interaction_quantity);
	}
}