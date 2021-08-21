package channelpopularity.customExceptions;

/**
 * videoExistException when Video added is already present
 * 
 * @author Ramesh Chowdarapally
 * 
 * @param s is a string
 */

public class videoExistException extends Exception {

	public videoExistException(String s) {

		super(s);
	}
}