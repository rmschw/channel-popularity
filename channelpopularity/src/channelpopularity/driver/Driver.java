package channelpopularity.driver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import channelpopularity.operation.Operation;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.FileProcessor;
import channelpopularity.util.Results;
import channelpopularity.customExceptions.EmptyFileException;
import channelpopularity.customExceptions.adRequestException;
import channelpopularity.customExceptions.decinDisikeException;
import channelpopularity.customExceptions.decinLikeException;
import channelpopularity.customExceptions.invalidInputException;
import channelpopularity.customExceptions.videoExistException;
import channelpopularity.customExceptions.removeVideoException;

/**
 * Driver class Retrieving inputs from input file and writes output to output
 * 
 * @author Ramesh Chowdarapally
 *
 */
public class Driver {

	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 2;

	/**
	 * 
	 * @param args to pass from file
	 * @throws Exception             indicates conditions that a reasonable
	 *                               application might want to catch.
	 * @throws EmptyFileException    to find empty file
	 * @throws InvalidPathException  if the path is invalid
	 * @throws SecurityException     to indicate a security violation.
	 * @throws FileNotFoundException when file not found
	 * @throws IOException           Signals that an I/O exception of some sort has
	 *                               occurred.
	 * @throws invalidInputException to find invalid input
	 * @throws videoExistException   when Video added is already present
	 * @throws decinLikeException    Decrease in likes is more than the total number
	 *                               of likes
	 * @throws decinDisikeException  Decrease in dislikes is more than the total
	 *                               number of dislikes
	 * @throws removeVideoException  Video being asked to remove does not exist
	 * @throws adRequestException    Video associated with an advertisement request
	 *                               does not exist
	 */
	public static void main(String[] args) throws Exception, InvalidPathException, SecurityException, FileNotFoundException, IOException, invalidInputException,
	videoExistException, decinLikeException, removeVideoException, decinDisikeException, adRequestException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case
		 * the argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */

		if ((args.length != 2) || (args[0].equals("${input}")) || (args[1].equals("${output}"))) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}

		try {
			System.out.println("Hello World! Lets get started with the assignment");

			String sInputFileName = args[0];
			String sOutputFileName = args[1];

			if (new File(sInputFileName).length() == 0) {
				throw new EmptyFileException("File is empty");
			}

			Results rs = new Results(sInputFileName, sOutputFileName);
			rs.processLine();
		} catch (adRequestException e) {
			e.printStackTrace();
		} catch (videoExistException e) {
			e.printStackTrace();
		} catch (decinLikeException e) {
			e.printStackTrace();
		} catch (decinDisikeException e) {
			e.printStackTrace();
		} catch (removeVideoException e) {
			e.printStackTrace();
		} catch (InvalidPathException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
