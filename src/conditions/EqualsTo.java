package conditions;

/**
 * 
 * @author Rayan
 *
 */
public class EqualsTo implements Comparator {

	private String sign = "==";
	private int id = 3;
	
	public EqualsTo(){}
	
	@Override
	public boolean compare(double val1, double val2) {
		// TODO Auto-generated method stub
		return (val1 == val2);
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
