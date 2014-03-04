/**
 * 
 */
package se.kth.mobdev.ruontime.service;

import java.util.List;

import se.kth.mobdev.ruontime.model.Statistics;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.User;

/**
 * @author Jasper
 *
 */
public class StatisticsService {
	
	public Statistics getUserStatistics(User user) {
		List<CheckIn> checkInsForUser = PersistenceFactory.getCheckinDao().getCheckInsForUser(user.getUserName());
		Statistics statistics  = new Statistics(checkInsForUser);
		
		//aggregate information:
		int aggregatedLate = 0;
		int noOfFirst = 0;
		int noOfLast = 0;
		for(CheckIn check: checkInsForUser) {
			aggregatedLate += check.getMinutesLate();
			if(check.isFirstToShowUp())
				noOfFirst++;
			if(check.isLastToShowUp())
				noOfLast++;
		}
		double avgLate = (aggregatedLate * 1.0)/checkInsForUser.size();
		statistics.setAggregatedLate(aggregatedLate);
		statistics.setAvgLate(avgLate);
		statistics.setNoOfFirst(noOfFirst);
		statistics.setNoOfLast(noOfLast);
		
		return statistics;
	}

}
