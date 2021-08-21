package channelpopularity.customExceptions;

/**
 * @throws decinDisikeException Decrease in dislikes is more than the total
 *                              number of dislikes
 * 
 * @param s is a string
 */

public class decinDisikeException extends Exception {

	public decinDisikeException(String s) {

		super(s);
	}
}