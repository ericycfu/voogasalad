package conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_engine.EngineObject;
import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;


/**
 * 
 * @author Rayan
 * A condition is created by the designer to trigger functions when a variable in the gameobject reaches 
 * a certain level. Each condition can have a list of custom conditions that will be triggered when the condition returns true.
 * Conditions only act upon the object they are assigned to. A condition object must be constructed by giving it the gameobject 
 * to act upon, the comparator to use, and the values to compare.
 */

public class Condition implements EngineObject<ConditionManager> {

	private int id;
	private ComparatorManager comparatorManager;
	private GameObject host;
	
	private String var1;
	private String var2;
	private int comparatorID;
	
	private List<CustomCondition> customConditions;

	
	public Condition(GameObject object, ConditionManager manager, int comparatorID, String var1, String var2)
	{
		comparatorManager = new ComparatorManager();
		host = object;
		addToManager(manager);
		this.var1 = var1;
		this.var2 = var2;
		this.comparatorID = comparatorID;
	}

	
	public CustomCondition addCustomCondition(String type)
	{
		CustomConditionFactory factory = new CustomConditionFactory();
		CustomCondition cc = factory.getCustomCondition(type);
		customConditions.add(cc);
		return cc;
	}

	@Override
	public int getID() {
		return id;
	}


	public void setID(int id)
	{
		this.id = id;
	}
	
	@Override
	public void addToManager(ConditionManager manager) {
		
		setID(manager.addElementToManager(this));
		
	}
	
	public void execute()
	{
		try 
		{
			if(comparatorManager.getComparatorResult(comparatorID, getVariableVal(var1), getVariableVal(var2)))
			{
				for(CustomCondition c : customConditions)
				{
					c.Execute(host);
				}
			}
		} 
		catch (PropertyNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Variable does not exist in the object");
		} 
		catch (UnmodifiableGameObjectException e) {
		
			e.printStackTrace();
			System.out.println("variable cannot be modified");
		}
	}
	
	private double getVariableVal(String var) throws PropertyNotFoundException, UnmodifiableGameObjectException
	{
		if (var.matches("([0-9]*)\\.([0-9]*)"))
		{
			return Double.parseDouble(var);
		}
		
		return host.accessLogic().accessAttributes().getAttribute(var);
	}
	
}
