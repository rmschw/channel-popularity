package channelpopularity.state.factory;

import channelpopularity.state.HIGHLYPOPULARSTATE;
import channelpopularity.state.MILDLYPOPULARSTATE;
import channelpopularity.state.StateI;
import channelpopularity.state.ULTRAPOPULARSTATE;
import channelpopularity.state.UNPOPULARSTATE;

/**
 * SimpleStateFactory
 * @author Ramesh Chowdarapally
 * UNPOPULAR popularity score range [0, 1000]
 * MILDLYPOPULAR PS  range (1000, 10000]
 * HIGHLYPOPULAR PS range (10000, 100000]
 * ULTRAPOPULAR PS range (100000, INT_MAX]
 * state state of the channel
 * 
 */
public class SimpleStateFactory implements SimpleStateFactoryI {
	StateI UNPOPULAR; //popularity score range [0, 1000]
	StateI MILDLYPOPULAR; // PS  range (1000, 10000]
	StateI HIGHLYPOPULAR; //PS range (10000, 100000]
	StateI ULTRAPOPULAR; //PS range (100000, INT_MAX]
	StateI state; 

	/**
	 * getState method used to get the state of the channel
	 * @param AvgPopScore Average popularity score of the channel
	 * @return null
	 */
	public StateI getState(int AvgPopScore) {
		if (AvgPopScore <= 1000) {
			return new UNPOPULARSTATE();
		} else if (AvgPopScore > 1000 && AvgPopScore <= 10000) {
			return new MILDLYPOPULARSTATE();
		} else if (AvgPopScore > 10000 && AvgPopScore <= 100000) {
			return new HIGHLYPOPULARSTATE();
		} else if (AvgPopScore > 100000) {
			return new ULTRAPOPULARSTATE();
		}
		return null;
	}
}
