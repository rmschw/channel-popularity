package channelpopularity.customExceptions;

/**
 * @throws removeVideoException Video being asked to remove does not exist
 * 
 * @param s is a string
 */

public class removeVideoException extends Exception {

	public removeVideoException(String s) {

		super(s);
	}
}
