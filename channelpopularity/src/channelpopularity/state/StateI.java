package channelpopularity.state;

import channelpopularity.customExceptions.adRequestException;
import channelpopularity.customExceptions.decinDisikeException;
import channelpopularity.customExceptions.decinLikeException;
import channelpopularity.customExceptions.removeVideoException;
import channelpopularity.customExceptions.videoExistException;

/**
 * StateI is an interface
 * 
 * @author Ramesh Chowdarapally
 *
 */
public interface StateI {

	/**
	 * 
	 * @param video name of the video take as input
	 * @return
	 * @throws videoExistException
	 */
	public String addVideo(String video) throws videoExistException;

	/**
	 * 
	 * @param vdeo name of the video take as input
	 * @return
	 * @throws removeVideoException
	 */

	public String removeVideo(String vdeo) throws removeVideoException;

	/**
	 * 
	 * @param video    name of the video take as input
	 * @param views    views of the video
	 * @param likes    likes of the video
	 * @param dislikes dislikes of the video
	 * @return
	 * @throws decinLikeException   decrease in likes more than total likes
	 * @throws decinDisikeException decrease in dislikes more than total dislikes
	 */

	public String metricsVideo(String video, int views, int likes, int dislikes)
			throws decinLikeException, decinDisikeException;

	/**
	 * 
	 * @param video    name of the video
	 * @param adLength advertisement length
	 * @return
	 * @throws adRequestException advertisement request exception
	 */

	public String adRequest(String video, int adLength) throws adRequestException;

	int iAvgPS = 0;
}
