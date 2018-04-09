package conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import game_engine.EngineObject;
import game_object.GameObject;
import game_object.PropertyNotFoundException;
import game_object.UnmodifiableGameObjectException;
import interactions.CustomFunction;
import interactions.CustomFunctionFactory;
import interactions.InteractionManager;

public class Condition implements EngineObject<ConditionManager> {

	private int id;
	private ComparatorManager comparatorManager;
	private GameObject host;
	
	private String var1;
	private String var2;
	private int comparatorID;
	
	public Condition(GameObject object, ConditionManager manager, int comparatorID, String var1, String var2)
	{
		comparatorManager = new ComparatorManager();
		host = object;
		addToManager(manager);
		this.var1 = var1;
		this.var2 = var2;
		this.comparatorID = comparatorID;
	}


	@Override
	public int getID() {
		return id;
	}


	@Override
	public void addToManager(ConditionManager manager) {
		// TODO Auto-generated method stub
		
	}
	
	public void execute()
	{
		try 
		{
			if(comparatorManager.getComparatorResult(comparatorID, getVariableVal(var1), getVariableVal(var2)))
			{
				//execute custom conditionals
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
