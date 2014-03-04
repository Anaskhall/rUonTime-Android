/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.Meeting;
import se.kth.mobdev.ruontime.persistence.model.User;


/**
 * @author Jasper
 *
 */

@ManagedBean(name = "allMeetingsBean")
public class AllMeetingsBean {
	
	private List<Meeting> allMeetings;
	
	private List<CheckIn> checkIns;
	
	public void selectUser(){
		//load statistics for selected user
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
		this.allMeetings =  PersistenceFactory.getMeetingDao().getAll();
	}

	public List<Meeting> getAllMeetings() {
		return allMeetings;
	}

	public void setAllMeetings(List<Meeting> allMeetings) {
		this.allMeetings = allMeetings;
	}
	
	
	
}

