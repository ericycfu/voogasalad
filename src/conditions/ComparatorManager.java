package conditions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparatorManager implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Map<Integer, Comparator> comparatorMap;
	
	public ComparatorManager()
	{
		initializeMap();
	}
	
	private void initializeMap()
	{
		comparatorMap = new HashMap<>();
		
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
	
	public Comparator getComparator(int id) throws ComparatorNotFoundException
	{
		if(comparatorMap.containsKey(id))
			return comparatorMap.get(id);
		
		throw new ComparatorNotFoundException("Invalid id given to manager");
	}
	
	public List<Comparator> getComparators()
	{
		List<Comparator> comparatorList = new ArrayList<>();
		for(Map.Entry<Integer, Comparator> entry: comparatorMap.entrySet())
		{
			comparatorList.add(entry.getValue());
		}
		return comparatorList;
	}
	
	public List<String> getComparatorSigns() 
	{
		List<String> comparatorSigns = new ArrayList<>();
		for(Map.Entry<Integer, Comparator> entry: comparatorMap.entrySet())
		{
			comparatorSigns.add(entry.getValue().getSign());
		}
		return comparatorSigns;
	}
	
	public String getSymbolById(int id) {
		for(Map.Entry<Integer, Comparator> entry: comparatorMap.entrySet()) {
			if (entry.getValue().getID() == id) {
				return entry.getValue().getSign();
			}
		}
		return "";
	}
}
