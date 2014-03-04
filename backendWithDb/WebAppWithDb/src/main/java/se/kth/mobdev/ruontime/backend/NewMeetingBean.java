/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import se.kth.mobdev.ruontime.persistence.IGenericDao;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.Meeting;
import se.kth.mobdev.ruontime.util.CalendarHelper;

/**
 * @author Jasper
 *
 */

@ManagedBean(name = "newMeetingBean")
@ViewScoped
public class NewMeetingBean {

	private Meeting newMeeting;
	
	private Date startsAt;
	
	private Date endsAt;
	
	private List<Group> allGroups;

	private Group selectedGroup;
	
	public String create(){
		newMeeting.addAssociatedGroup(selectedGroup);
		newMeeting.setStartsAt(CalendarHelper.DateToCalendar(startsAt));
		newMeeting.setEndsAt(CalendarHelper.DateToCalendar(endsAt));
		IGenericDao<Meeting> meetingDao = PersistenceFactory.getMeetingDao();
		newMeeting = meetingDao.save(newMeeting);
		System.out.println("created new meeting: " + newMeeting + " for group " + selectedGroup);
		return "welcome.xhtml";
	}

	public Meeting getNewMeeting() {
		return newMeeting;
	}

	public void setNewMeeting(Meeting newMeeting) {
		this.newMeeting = newMeeting;
	}

	public List<Group> getAllGroups() {
		return allGroups;
	}

	public void setAllGroups(List<Group> allGroups) {
		this.allGroups = allGroups;
	}
	
	@PostConstruct
	public void init(){
		newMeeting = new Meeting();
		//fetch all Groups from DataBase
		allGroups = PersistenceFactory.getGroupDao().getAll();
		
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public Date getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(Date startsAt) {
		this.startsAt = startsAt;
	}

	public Date getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(Date endsAt) {
		this.endsAt = endsAt;
	}
	
	
}
