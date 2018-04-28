package interactions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import authoring.backend.AuthoringObject;
import game_engine.EngineObject;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.ObjectLogic;
import game_object.PropertyNotFoundException;
import javafx.scene.image.Image;
import transform_library.Transform;

/**
 * Codes for an action that occurs in the game. Different actions might simulate a GameObject acting on itself or acting on another object
 * @author andrew, Rayan
 *
 */
public class Interaction implements EngineObject {

	private int id;
	private List<String> targetTags;
	private String name;
	@XStreamOmitField
	private transient Image img;
	private String imagePath;
	private String description;

	//these will be changed by authoring for the interaction
	private boolean isBuild;
	private boolean isInstantaneous;

	//store functions by id
	private List<CustomFunction> customFunctions;
	private double range;

	public Interaction(int id)
	{
		customFunctions = new ArrayList<>();
		targetTags = new ArrayList<>();
		this.id = id;
	}


	/**
	 * 
	 * @param type
	 * @return
	 * Adds a custom function to the interaction.
	 * need to add the functionality that only the variables related to those tags can be changed etc.
	 */
	public CustomFunction generateCustomFunction(String type) {
		
		CustomFunctionFactory factory = new CustomFunctionFactory();		
		CustomFunction function = factory.getCustomFunction(type);
		return function;
	}

	/**
	 * 
	 * @param cFunction
	 * Adds a prepared custom function to the interaction
	 */
	public void addCustomFunction(CustomFunction cFunction)
	{
		customFunctions.add(cFunction);
	}
	
	
	public void addAllCustomFunctions(List<String> types) {
		for(String type : types) {
			generateCustomFunction(type);
		}
	}

	/**
	 * Runs all the custom functions in the interactions
	 * Each custom function can affect the other game object
	 */
	public void executeCustomFunctions(GameObject current, GameObject other, GameObjectManager manager)
	{
		if(matchesTags(other, targetTags)) return;
		try {
			for(CustomFunction cFunc : customFunctions)
			{
				cFunc.Execute(current, other, manager);
			}
		}
		catch(PropertyNotFoundException p) {

		}
	}

	private boolean matchesTags(GameObject other, List<String> tags)
	{
		for(String s : other.getTags())
		{
			if(tags.contains(s)) return true;
		}
		return false;
	}

	public void setRange(double range)
	{
		this.range = range;
	}

	public double getRange() {
		return range;
	}
	
	public boolean isBuild()
	{
		return isBuild;
	}
	
	public void isBuild(boolean val)
	{
		this.isBuild = val;
	}
	
 	public List<String> getTargetTags()
 	{
 		return targetTags;
 	}
 	public void addTag(String newTag) 
 	{
 		if(!targetTags.contains(newTag))
 			targetTags.add(newTag);
 	}
 	public void removeTag(String oldTag) 
 	{
 		targetTags.remove(oldTag);
 	}
 	public CustomFunction getCustomFunction(int x) 
 	{
 		return customFunctions.get(x);
 	}


	@Override
	public int getID() {
		return id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Image getImg() {
		return img;
	}


	public void setImg(String imagePath) {
		this.imagePath = imagePath;
		setImageFromPath();
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	public List<CustomFunction> getCustomFunctions()
	{
		return customFunctions;
	}
	public void setImageFromPath() {
		img = new Image(imagePath);
	}
}
