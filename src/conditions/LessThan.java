package conditions;

/**
 * 
 * @author Rayan
 * Returns true if value less than
 */

public class LessThan implements Comparator{

	private String sign = "<";
	private int id = 0;
	public LessThan(){}
	
	@Override
	/**
	 * Returns true if val1 < val2
	 */
	public boolean compare(double val1, double val2) {
		// TODO Auto-generated method stub
		return (val1 < val2);
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}
	
	@Override
	public String getSign() {
		// TODO Auto-generated method stub
		return sign;
	}

}
