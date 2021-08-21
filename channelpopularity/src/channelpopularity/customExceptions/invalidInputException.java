package channelpopularity.customExceptions;

/**
 *  invalidInputException to find invalid input
 * @author Ramesh Chowdarapally
 * @param s is a string
 */

public class invalidInputException extends Exception {
	
	public invalidInputException(String s) {
		
		super(s);
	}
}
