package channelpopularity.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Utility class used to get average popular score 
 * and to calculate channel popularity
 *
 */
public class Utility {
	public static int getiAveragePopScore() {
		return iAveragePopScore;
	}

	/**
	 * setiAveragePopScore method pass iAveragePopScore argument
	 * @param iAveragePopScore average popular score as integer
	 */
	public void setiAveragePopScore(int iAveragePopScore) {
		this.iAveragePopScore = iAveragePopScore;
	}

	/**
	 * iAveragePopScore is Average Popularity Score
	 */
	static int iAveragePopScore;

	/**
	 * updateAvgPS used to update Average Popular Score
	 * @param map used to take pair of values and keys
	 * @return iAvgPS average popular score as integer
	 */
	public int updateAvgPS(Map map) {
		int iAvgPS = 0;
		int sumofChannelPopScore = 0;
		Set<String> s = map.keySet();

		for (String key : s) {
			sumofChannelPopScore = sumofChannelPopScore + calculatePopScore(key, map);
		}
		if (s.size() == 0) {
			return 0;
		}

		iAvgPS = sumofChannelPopScore / s.size();
		setiAveragePopScore(iAvgPS);
		return iAvgPS;
	}

	/**
	 * calculatePopScore method used to calculate the channel popularity score
	 * @param video name of the video
	 * @param map used to collect values and keys
	 * @return
	 */
	public int calculatePopScore(String video, Map map) {
		int Channelpopscore = 0;
		if ((Map) map.get(video) != null) {
			Map MapMetrics = (Map) map.get(video);
			Channelpopscore = (int) ((Map) map.get(video)).get("views")
					+ 2 * ((int) MapMetrics.get("likes") - (int) MapMetrics.get("dislikes"));
		}
		return Channelpopscore;
	}
}
