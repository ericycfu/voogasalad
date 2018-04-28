package conditions;

import java.util.ArrayList;
import java.util.Arrays;
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

public class Condition implements EngineObject {

	private int id;
	private ComparatorManager comparatorManager;
//	private GameObject host;
	private List<String> tags;
	
	private String var1;
	private String var2;
	private int comparatorID;
	
	private List<CustomCondition> customConditions;

	
//	public Condition(int id, GameObject object, int comparatorID, String var1, String var2)
	public Condition(int id, int comparatorID, String var1, String var2)

	{
		this.id = id;
		comparatorManager = new ComparatorManager();
		customConditions = new ArrayList<>();
//		host = object;
		this.var1 = var1;
		this.var2 = var2;
		this.comparatorID = comparatorID;
	}

	
	public void addCustomCondition(CustomCondition cc)
	{
		customConditions.add(cc);
	}
	
	public CustomCondition generateCustomCondition(String conditionName)
	{
		CustomConditionFactory factory = new CustomConditionFactory();
		return factory.getCustomCondition(conditionName);
	}

	@Override
	public int getID() {
		return id;
	}
	
	public List<String> getInfo() {
		String[] array = {var1, comparatorManager.getSymbolById(comparatorID), var2};
		List<String> info = new ArrayList<>(Arrays.asList(array));
		for (CustomCondition c: customConditions) {
			info.add(c.getClass().getSimpleName());
		}
		return info;
	}

	public void addTag(String t)
	{
		tags.add(t);
	}
	
	public void removeTag(String t)
	{
		tags.remove(t);
	}
	
	public void execute()
	{
		try 
		{
			if(comparatorManager.getComparatorResult(comparatorID, getVariableVal(var1), getVariableVal(var2)))
			{
				for(CustomCondition c : customConditions)
				{
//					c.Execute(host);
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
		
//		return host.accessLogic().accessAttributes().getAttribute(var);
		return 0;
	}
	
}
