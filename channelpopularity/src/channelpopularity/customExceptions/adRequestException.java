package channelpopularity.customExceptions;

/**
 * adRequestException Video associated with an advertisement request does not
 * exist
 * 
 * @author Ramesh Chowdarapally
 * @param s is a string
 * 
 */

public class adRequestException extends Exception {

	public adRequestException(String s) {

		super(s);
	}
}
