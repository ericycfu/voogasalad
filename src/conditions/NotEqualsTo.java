package conditions;
/**
 * 
 * @author Rayan
 *
 */
public class NotEqualsTo implements Comparator {

	private String sign = "!=";
	public static final int id = 2;
	public NotEqualsTo(){}
	
	@Override
	public boolean compare(double val1, double val2) {
		// TODO Auto-generated method stub
		return (val1 != val2);
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
