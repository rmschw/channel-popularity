package channelpopularity.state;

import java.nio.file.InvalidPathException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import channelpopularity.state.factory.SimpleStateFactory;
import channelpopularity.util.Utility;
import channelpopularity.customExceptions.EmptyFileException;
import channelpopularity.customExceptions.decinDisikeException;
import channelpopularity.customExceptions.decinLikeException;
import channelpopularity.customExceptions.removeVideoException;
import channelpopularity.customExceptions.videoExistException;
import channelpopularity.customExceptions.removeVideoException;

/**
 * AbstractState used to implement addvideo, remove video and metircs methods
 * 
 * @author Ramesh Chowdarapally
 * 
 */

public abstract class AbstractState implements StateI {
	SimpleStateFactory simplestateFactory;
	Utility ul = new Utility();
	static int iAvgPS;
	StateI state;
	static Map map = new HashMap();

	/**
	 * addVideo method used to add video from input file
	 * 
	 * @param video is the video to be added to map
	 * @return ""
	 * @throws videoExistException
	 */
	public String addVideo(String video) throws videoExistException {

		try {
			Map mapMetrics = new HashMap<>();
			mapMetrics.put("views", 0);
			mapMetrics.put("likes", 0);
			mapMetrics.put("dislikes", 0);
			if (map.containsKey(video)) {
				throw new videoExistException("Video already exist");
			}
			map.put(video, mapMetrics);
			iAvgPS = ul.updateAvgPS(map);

		} catch (videoExistException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * removeVideo method
	 * 
	 * @param video is the video to be added to map
	 * @return ""
	 * @throws videoExistException
	 */

	public String removeVideo(String video) throws removeVideoException {
		try {
			if (!map.containsKey(video)) {
				throw new removeVideoException("Video to remove does not exist");
			}
			map.remove(video);
			iAvgPS = ul.updateAvgPS(map);
		} catch (removeVideoException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * metricsVideo method
	 * 
	 * @param video     is the video to be added to map
	 * @param views     total views
	 * @param likes     of the video
	 * @param disklikes of the video
	 * @return ""
	 * @throws decinDisikeException used to indicate Decrease in likes more than
	 *                              Total likes
	 * @throws decinLikeException
	 */
	public String metricsVideo(String video, int views, int likes, int dislikes)
			throws decinLikeException, decinDisikeException {
		Map mapMetrics = new HashMap<>();

		if (views < 0) {
			System.out.println("Negative value entered");
		}
		int TotalViews;
		if ((Map) map.get(video) != null) {

			Map MapMetrics = (Map) map.get(video);
			MapMetrics.get("views");
			int Totallikes = (int) MapMetrics.get("likes");

			try {
				if (likes < 0 && Math.abs(likes) > Totallikes) {
					throw new decinLikeException("Decrease in likes more than Total likes");
				}
			} catch (decinLikeException e) {
				e.printStackTrace();
			}

			int Totaldislikes = (int) MapMetrics.get("dislikes");

			try {
				if (dislikes < 0 && Math.abs(dislikes) > Totaldislikes) {
					throw new decinDisikeException("Decrease in dislikes more than Total dislikes");
				}
			} catch (decinDisikeException e) {
				e.printStackTrace();
			}

			views = (int) MapMetrics.get("views") + views;
			likes = Totallikes + likes;
			dislikes = (int) MapMetrics.get("dislikes") + dislikes;
		}

		mapMetrics.put("views", views);
		mapMetrics.put("likes", likes);
		mapMetrics.put("dislikes", dislikes);
		map.put(video, mapMetrics);
		iAvgPS = ul.updateAvgPS(map);

		return "";
	}
}
