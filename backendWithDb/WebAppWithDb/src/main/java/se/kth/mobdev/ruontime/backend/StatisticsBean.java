/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import se.kth.mobdev.ruontime.model.Statistics;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.User;
import se.kth.mobdev.ruontime.service.ServiceFactory;

/**
 * @author Jasper
 *
 */

@ManagedBean(name = "statisticsBean")
public class StatisticsBean {

	private List<User> allUsers;
	
	private List<CheckIn> checkIns;
	
	private User selectedUser;
	
	private Statistics stats;
	
	public void selectUser(){
		//load statistics for selected user
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public List<CheckIn> getCheckIns() {
		return checkIns;
	}

	public void setCheckIns(List<CheckIn> checkIns) {
		this.checkIns = checkIns;
	}

	@PostConstruct
	private void init(){
		//gather user data from DB
		this.allUsers =  PersistenceFactory.getUserDao().getAll();
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
		loadStatistics();
	}

	private void loadStatistics() {
		this.stats = ServiceFactory.getStatisticsService().getUserStatistics(selectedUser);
	}

	public Statistics getStats() {
		return stats;
	}

	public void setStats(Statistics stats) {
		this.stats = stats;
	}
	
	
	
}

