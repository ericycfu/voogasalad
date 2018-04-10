package conditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ComparatorManager {
	
	private Map<Integer, Comparator> comparatorMap;
	
	public ComparatorManager()
	{
		initializeMap();
	}
	
	private void initializeMap()
	{
		Comparator equalsTo = new EqualsTo();
		Comparator greaterThan = new GreaterThan();
		Comparator lessThan = new LessThan();
		Comparator notEqualsTo = new NotEqualsTo();
		
		comparatorMap.put(equalsTo.getID(), equalsTo);
		comparatorMap.put(greaterThan.getID(), greaterThan);
		comparatorMap.put(lessThan.getID(), lessThan);
		comparatorMap.put(notEqualsTo.getID(), notEqualsTo);
	}
	
	public boolean getComparatorResult(int comparatorID, double val1, double val2)
	{
		return comparatorMap.get(comparatorID).compare(val1, val2);
	}
	
	public Comparator getCompartor(int id) throws ComparatorNotFoundException
	{
		if(comparatorMap.containsKey(id))
			return comparatorMap.get(id);
		
		throw new ComparatorNotFoundException("Invalid id given to manager");
	}
	
	public List<Comparator> getComparator()
	{
		List<Comparator> comparatorList = new ArrayList<>();
		for(Map.Entry<Integer, Comparator> entry: comparatorMap.entrySet())
		{
			comparatorList.add(entry.getValue());
		}
		return comparatorList;
	}
}