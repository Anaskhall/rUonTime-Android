/**
 * 
 */
package se.kth.mobdev.ruontime.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
@Entity
@Table(name = "groups")
public class Group implements IEntity{

	private static final long serialVersionUID = -2341167406720386641L;

	@Id
	@Column(name = "group_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name ="groupname")
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "supervisors", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "group_id", 
					nullable = false, updatable = false) })
	private List<User> supervisors;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "group_user", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "group_id", 
					nullable = false, updatable = false) })
	private List<User> participants;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_group", joinColumns = { 
			@JoinColumn(name = "group_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "meeting_id", 
					nullable = false, updatable = false) })
	private List<Meeting> meetings;
	
	public Group() {};
	
	public Group(String name, User supervisor) {
		this.name = name;
		
		this.supervisors = new ArrayList<User>();
		this.supervisors.add(supervisor);
		
		this.participants = new ArrayList<User>();
		
		this.meetings = new ArrayList<Meeting>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getSupervisors() {
		return supervisors;
	}

	public void addSupervisor(User supervisor) {
		this.supervisors.add(supervisor);
	}
	
	public void removeSupervisor(User supervisor) {
		this.supervisors.remove(supervisor);
	}

	public List<User> getParticipants() {
		return participants;
	}

	
	public void addParticipant(User participant) {
		this.participants.add(participant);
	}
	
	public void removeParticipant(User participant) {
		this.participants.remove(participant);
	}

	public List<Meeting> getMeetings() {
		return meetings;
	}

	public void addMeeting(Meeting meeting) {
		this.meetings.add(meeting);
	}
	
	public void removeMeeting(Meeting meeting) {
		this.meetings.remove(meeting);
	}
	
	public String toString(){
		return this.name;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setParticipants(List<User> participants) {
		this.participants = participants;
	}
	
}
