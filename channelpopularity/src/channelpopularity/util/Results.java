package channelpopularity.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import channelpopularity.state.StateI;
import channelpopularity.state.UNPOPULARSTATE;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.customExceptions.EmptyFileException;
import channelpopularity.customExceptions.adRequestException;
import channelpopularity.customExceptions.decinDisikeException;
import channelpopularity.customExceptions.decinLikeException;
import channelpopularity.customExceptions.invalidInputException;
import channelpopularity.customExceptions.removeVideoException;
import channelpopularity.customExceptions.videoExistException;

/**
 * Results is a utility to be used to show the results of the input file.
 * 
 * @author Ramesh Chowdarapally
 *
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	String strInFileName;
	String sOutputFile;
	List<String> outList = new ArrayList<String>();

	/**
	 * 
	 * @param strInFileName file name of the input string
	 * @param sOutputFile   output file which gives results
	 */
	public Results(String strInFileName, String sOutputFile) {
		this.strInFileName = strInFileName;
		this.sOutputFile = sOutputFile;
	}

	/**
	 * 
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
	public void processLine()
			throws InvalidPathException, SecurityException, FileNotFoundException, IOException, invalidInputException,
			videoExistException, decinLikeException, removeVideoException, decinDisikeException, adRequestException {
		FileProcessor fp = new FileProcessor(strInFileName);
		String strInstruction;
		SimpleStateFactory simplestatefactory = new SimpleStateFactory();
		StateI state = simplestatefactory.getState(0);

		while ((strInstruction = fp.poll()) != null) {

			boolean isValid = false;
			if ((strInstruction.contains("ADD_VIDEO") || strInstruction.contains("REMOVE_VIDEO")
					|| strInstruction.contains("METRICS") || strInstruction.contains("AD_REQUEST"))
					&& (strInstruction.contains("::"))) {
				isValid = true;
			}
			if (!isValid) {
				throw new invalidInputException("Invalid input received");
			}

			if (strInstruction.contains("ADD_VIDEO")) {
				String strVideo = strInstruction.split("::")[1].trim();

				outList.add(state.addVideo(strVideo));
			} else if (strInstruction.contains("METRICS")) {

				String strVideo = strInstruction.substring(strInstruction.indexOf("__") + 2,
						strInstruction.indexOf(":"));
				String metrics = strInstruction.split("::")[1];
				String strMetrics[] = metrics.split("[=,\\]]");

				int mArr[] = new int[3], j = 0;
				for (int i = 0; i < strMetrics.length; i++) {

					if (isNumber(strMetrics[i])) {
						mArr[j] = Integer.parseInt(strMetrics[i]);
						j++;
					}
				}
				outList.add(state.metricsVideo(strVideo, mArr[0], mArr[1], mArr[2]));

			} else if (strInstruction.contains("REMOVE")) {
				String strVideo = strInstruction.split("::")[1].trim();
				outList.add(state.removeVideo(strVideo));
			} else if (strInstruction.contains("AD_REQUEST")) {
				String strVideo = strInstruction.substring(strInstruction.indexOf("__") + 2,
						strInstruction.indexOf(":"));
				String strAdReq = strInstruction.split("::")[1];
				String strAdLen = strAdReq.split("=")[1];

				int iAdLen = 0;
				if (isNumber(strAdLen)) {
					iAdLen = Integer.parseInt(strAdLen);
				}

				/*
				 * try { if (new File(strAdReq)== null) { throw new
				 * adRequestException("Video associated with an AD_REQUEST does not exist"); } }
				 * catch (adRequestException e) { e.printStackTrace(); }
				 */

				outList.add(state.adRequest(strVideo, iAdLen));
			}
			state = simplestatefactory.getState(Utility.iAveragePopScore);

		}
		printMethod();
		filePrint();
	}

	@Override
	public String toString() {
		return "Results [FileName=" + strInFileName + "]";
	}

	/**
	 * isNumber method is to find the numbers
	 * 
	 * @param s
	 * @return
	 */
	static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}

	/**
	 * printMethod used to print the result to stdOut
	 *
	 */
	void printMethod() {
		for (String strOut : outList) {
			System.out.println(strOut);
		}
	}

	/**
	 * filePrint method used to write the result into file
	 * @throws IOException
	 */
	void filePrint() throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(sOutputFile, false));

		for (String strOut : outList) {
			bw.write(strOut);
			bw.newLine();
		}

		bw.close();
	}
}
