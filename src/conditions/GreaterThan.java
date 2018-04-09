package conditions;

/**
 * 
 * @author Rayan
 * Returns if a value is greater or not
 */

public class GreaterThan implements Comparator {

	private String sign = ">";
	private int id = 1;
	public GreaterThan(){}
	
	@Override
	/**
	 * returns true if val1 > val2
	 */
	public boolean compare(double val1, double val2) {
		return (val1 > val2);
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
