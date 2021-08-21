package channelpopularity.customExceptions;

/**
* EmptyFileException to find empty file
* @author Ramesh Chowdarapally
 * @param s is a string
*
*/

public class EmptyFileException extends Exception {

	public EmptyFileException(String s) {

		super(s);
	}

}
