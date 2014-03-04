/**
 * 
 */
package se.kth.mobdev.ruontime.model;

import java.util.List;

import se.kth.mobdev.ruontime.persistence.model.CheckIn;

/**
 * @author Jasper
 *
 */
public class Statistics {
	
	private List<CheckIn> allCheckins;
	
	private int aggregatedLate;
	
	private double avgLate;
	
	private int noOfFirst;
	
	private int noOfLast;

	public Statistics(List<CheckIn> checkInsForUser) {
		this.allCheckins = checkInsForUser;
	}

	public List<CheckIn> getAllCheckins() {
		return allCheckins;
	}

	public void setAllCheckins(List<CheckIn> allCheckins) {
		this.allCheckins = allCheckins;
	}

	public int getAggregatedLate() {
		return aggregatedLate;
	}

	public void setAggregatedLate(int aggregatedLate) {
		this.aggregatedLate = aggregatedLate;
	}

	public double getAvgLate() {
		return avgLate;
	}

	public void setAvgLate(double avgLate) {
		this.avgLate = avgLate;
	}

	public int getNoOfFirst() {
		return noOfFirst;
	}

	public void setNoOfFirst(int noOfFirst) {
		this.noOfFirst = noOfFirst;
	}

	public int getNoOfLast() {
		return noOfLast;
	}

	public void setNoOfLast(int noOfLast) {
		this.noOfLast = noOfLast;
	}
	
}
