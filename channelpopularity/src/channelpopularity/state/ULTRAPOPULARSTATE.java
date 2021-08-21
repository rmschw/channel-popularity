package channelpopularity.state;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import channelpopularity.customExceptions.videoExistException;
import channelpopularity.customExceptions.adRequestException;
import channelpopularity.customExceptions.decinDisikeException;
import channelpopularity.customExceptions.decinLikeException;
import channelpopularity.customExceptions.removeVideoException;
import channelpopularity.operation.Operation;
import channelpopularity.state.factory.SimpleStateFactory;

/**
 * ULTRAPOPULARSTATE For a channel to be in this state, its popularity score
 * should be in the range (100000, INT_MAX]
 * 
 * @author Ramesh Chowdarapally
 *
 */

public class ULTRAPOPULARSTATE extends AbstractState implements StateI {
	SimpleStateFactory simplestateFactory;
	Map map = new HashMap();

	/**
	 * addVideo add the video to channel
	 * 
	 * @param video name of the video
	 */
	@Override
	public String addVideo(String video) throws videoExistException {

		super.addVideo(video);
		return StateName.ULTRA_POPULAR + "__" + Operation.VIDEO_ADDED + "::" + video;
	}

	/**
	 * removeVideo removes the video from channel
	 * 
	 * @param video name of the video
	 */
	@Override
	public String removeVideo(String video) throws removeVideoException {
		super.removeVideo(video);
		return StateName.ULTRA_POPULAR + "__" + Operation.VIDEO_REMOVED + "::" + video;
	}

	/**
	 * metricsVideo metrics of the video
	 * 
	 * @param video name of the video
	 * @param views number of views
	 * @param likes number of likes
	 * @param dislikes number of dislikes
	 */
	@Override
	public String metricsVideo(String video, int views, int likes, int dislikes) throws decinLikeException, decinDisikeException{
		super.metricsVideo(video, views, likes, dislikes);
		return StateName.ULTRA_POPULAR + "__" + Operation.POPULARITY_SCORE_UPDATE + "::" + super.iAvgPS;
	}

	/**
	 * 
	 * adRequest advertisement request method to get approved or rejected status
	 * 
	 * @param video    name of the video
	 * @param adLength advertisement length
	 * 
	 */
	public String adRequest(String video, int adLength) throws adRequestException{
		String strAcceptence = "";
		if (adLength > 1 && adLength <= 40) {
			strAcceptence = "APPROVED";
		} else {
			strAcceptence = "REJECTED";
		}
		return StateName.ULTRA_POPULAR + "__" + Operation.AD_REQUEST + "::" + strAcceptence;
	}
}
