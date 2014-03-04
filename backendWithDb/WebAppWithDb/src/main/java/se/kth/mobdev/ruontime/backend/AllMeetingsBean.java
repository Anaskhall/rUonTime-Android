/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.User;


/**
 * @author Jasper
 *
 */

@ManagedBean(name = "statisticsBean")
public class AllMeetingsBean {
	
	private List<User> allUsers;
	
	private List<CheckIn> checkIns;
	
	public void selectUSer(){
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
		this.allUsers =  new ArrayList<User>();
		allUsers.add(new User("user1", "firstName", "lastName", "aaa"));
		allUsers.add(new User("user45", "aa", "ss", "13"));
		allUsers.add(new User("aaa", "cc", "ee", "31"));
	}
	
	
	
}

