package conditions;

import java.io.Serializable;

/**
 * 
 * @author Rayan
 * Comparison helper for the Conditions features. Will allow users to make comparisons between variables 
 * so they can execute functions at appropriate times. 
 *
 */
public interface Comparator extends Serializable {

	public boolean compare(double val1, double val2);
	public int getID();
	public String getSign();
}
