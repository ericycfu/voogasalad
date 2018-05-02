package interactions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import game_engine.EngineObject;
import game_object.GameObject;
import game_object.GameObjectManager;
import game_object.PropertyNotFoundException;
import javafx.scene.image.Image;

/**
 * Codes for an action that occurs in the game. Different actions might simulate a GameObject acting on itself or acting on another object
 * @author andrew, Rayan
 *
 */
public class Interaction implements EngineObject, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum InteractionTargetTeam
	{
		OWN, OTHER, ALL;
	}
	
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
	
	private InteractionTargetTeam InteractionTargetTeam;
	private Map<String, InteractionTargetTeam> targetTeamEnumMap;

	//store functions by id
	private List<CustomFunction> customFunctions;
	private double range;
	
	private double rate;

	public Interaction(int id)
	{
		customFunctions = new ArrayList<>();
		targetTags = new ArrayList<>();
		this.id = id;
		createTargetTeamEnumMap();
	}
	
	
	public Interaction(Interaction other)
	{
		this.isBuild = other.isBuild;
		this.isInstantaneous = other.isInstantaneous;
		this.InteractionTargetTeam = other.InteractionTargetTeam;
		this.customFunctions = other.customFunctions;
		this.range = other.range;
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
	
	
	public void addAllCustomFunctions(List<String> types)
	{
		for(String type : types) 
		{
			generateCustomFunction(type);
		}
	}

	/**
	 * Runs all the custom functions in the interactions
	 * Each custom function can affect the other game object
	 */
	public void executeCustomFunctions(GameObject current, GameObject other, GameObjectManager manager)
	{
		
		if(!validatedInteractionTarget(current, other)) 
			return;
		//if(matchesTags(other, targetTags)) 
			//return;
		try 
		{
			for(CustomFunction cFunc : customFunctions)
			{
				cFunc.Execute(current, other, manager);
			}
			current.dequeueInteraction(this.id);
		}
		catch(PropertyNotFoundException p) 
		{
			p.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return
	 * This helper makes sure that the interaction is only executed 
	 */
	private boolean validatedInteractionTarget(GameObject current, GameObject other)
	{
		if(this.InteractionTargetTeam == InteractionTargetTeam.ALL)
		{
			return true;
		}
		else if(this.InteractionTargetTeam == InteractionTargetTeam.OTHER && current.getOwner().getID() != other.getOwner().getID())
		{
			return true;
		}
		else if(this.InteractionTargetTeam == InteractionTargetTeam.OWN && current.getOwner().getID() == other.getOwner().getID())
		{
			return true;
		}
		return false;
		
	}
	
	private void createTargetTeamEnumMap() 
	{
		targetTeamEnumMap = new HashMap<>();
		for(InteractionTargetTeam value : this.InteractionTargetTeam.values()) 
		{
			targetTeamEnumMap.put(value.toString(), value);
		}
	}
	

	private boolean matchesTags(GameObject other, List<String> tags)
	{
		for(String s : other.getTags())
		{
			for(String x : tags)
			{
				if(s.equals(x)) return true;
			}
		}
		return false;
	}

	public void setRate(double rate)
	{
		this.rate = rate;
	}
	
	public double getRate()
	{
		//must change later
		return 1;
	}
	
	public void setRange(double range)
	{
		this.range = range;
	}

	public boolean isRepetitive()
	{
		for(CustomFunction cfunc : this.customFunctions)
		{
			if(!cfunc.isRepetitive())
			{
				return false;
			}
		}
		return true;
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
	
	public void setInteractionTargetTeam(InteractionTargetTeam setting)
	{
		this.InteractionTargetTeam = setting;
	}
	
	public void setInteractionTargetTeam(String setting_string)
	{
		this.InteractionTargetTeam = targetTeamEnumMap.get(setting_string);
	}
	
	public InteractionTargetTeam getInteractionTargetTeam()
	{
		return InteractionTargetTeam;
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

	public String getImagePath() {
		return this.imagePath;
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
