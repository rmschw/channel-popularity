package channelpopularity.customExceptions;

/**
 * decinLikeException Decrease in likes is more than the total number of
 * likes
 * 
 * @author Ramesh Chowdarapally
 * @param s is a string
 * 
 */

public class decinLikeException extends Exception {

	public decinLikeException(String s) {

		super(s);
	}
}