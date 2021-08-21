package channelpopularity.state;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import channelpopularity.customExceptions.videoExistException;
import channelpopularity.customExceptions.decinDisikeException;
import channelpopularity.customExceptions.decinLikeException;
import channelpopularity.customExceptions.removeVideoException;
import channelpopularity.operation.Operation;
import channelpopularity.state.factory.SimpleStateFactory;

/**
 * UNPOPULARSTATE This is the starting state of a channel. For a channel to be
 * in this state, its popularity score should in the range [0, 1000]
 * 
 * @author Ramesh Chowdarapally
 *
 */
public class UNPOPULARSTATE extends AbstractState implements StateI {

	/**
	 * addVideo add the video to channel
	 * 
	 * @param video name of the video
	 */
	@Override
	public String addVideo(String video) throws videoExistException {
		super.addVideo(video);
		return StateName.UNPOPULAR + "__" + Operation.VIDEO_ADDED + "::" + video;
	}

	/**
	 * removeVideo removes the video from channel
	 * 
	 * @param video name of the video
	 */
	@Override
	public String removeVideo(String video) throws removeVideoException {
		super.removeVideo(video);
		return StateName.UNPOPULAR + "__" + Operation.VIDEO_REMOVED + "::" + video;
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
	public String metricsVideo(String video, int views, int likes, int dislikes)
			throws decinLikeException, decinDisikeException {
		super.metricsVideo(video, views, likes, dislikes);
		return StateName.UNPOPULAR + "__" + Operation.POPULARITY_SCORE_UPDATE + "::" + super.iAvgPS;
	}

	/**
	 * 
	 * adRequest advertisement request method to get approved or rejected status
	 * 
	 * @param video    name of the video
	 * @param adLength advertisement length
	 * 
	 */
	public String adRequest(String video, int adLength) {
		String strAcceptence = "";
		if (adLength > 1 && adLength <= 10) {
			strAcceptence = "APPROVED";
		} else {
			strAcceptence = "REJECTED";
		}
		return StateName.UNPOPULAR + "__" + Operation.AD_REQUEST + "::" + strAcceptence;
	}

}
