/**
 * 
 */
package se.kth.mobdev.ruontime.persistence.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
@Entity
@Table(name="meetings")
public class Meeting implements IEntity{

	private static final long serialVersionUID = -4163119070210143402L;

	@Id
	@Column(name = "meeting_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_group", joinColumns = { 
			@JoinColumn(name = "group_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "meeting_id", 
					nullable = false, updatable = false) })
	private List<Group> associatedGroups;
	
	private String title;
	
	private Calendar startsAt;
	
	private Calendar endsAt;
	 
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "meeting_user", joinColumns = { 
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "meeting_id", 
					nullable = false, updatable = false) })
	private List<User> shouldAttend;
	
	public Meeting() {
		this.associatedGroups = new ArrayList<Group>();
	}
	
	/**
	 * Essential constructor with minimum amount of necessary information
	 * 
	 * @param title
	 * @param associatedGroup
	 * @param startsAt
	 */
	public void Meeting(String title, Group associatedGroup, Calendar startsAt){
		this.title = title;
		
		this.associatedGroups = new ArrayList<Group>();
		this.associatedGroups.add(associatedGroup);

		this.startsAt = startsAt;
		
		this.shouldAttend = new ArrayList<User>();
		
	}
	
	/*
	 * Some overloaded constructors for convenience
	 */
	public void Meeting(String title, Group associatedGroup, Calendar startsAt, Calendar endsAt){
		this.endsAt = endsAt;
		Meeting(title, associatedGroup, startsAt);
	}
	
	public void Meeting(String title, Group associatedGroup, Calendar startsAt, List<User> shouldAttend){
		Meeting(title, associatedGroup, startsAt);
		this.shouldAttend.addAll(shouldAttend);
	}
	
	public void Meeting(String title, Group associatedGroup, Calendar startsAt, Calendar endsAt, List<User> shouldAttend){
		Meeting(title, associatedGroup, startsAt);
		this.endsAt = endsAt;
		this.shouldAttend.addAll(shouldAttend);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Calendar getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(Calendar startsAt) {
		this.startsAt = startsAt;
	}

	public Calendar getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(Calendar endsAt) {
		this.endsAt = endsAt;
	}

	@Override
	public Integer getId() {
		return this.id;
	}
	

	public List<Group> getAssociatedGroups() {
		return associatedGroups;
	}

	public void setAssociatedGroups(List<Group> associatedGroups) {
		this.associatedGroups = associatedGroups;
	}

	public List<User> getShouldAttend() {
		return shouldAttend;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setShouldAttend(List<User> shouldAttend) {
		this.shouldAttend = shouldAttend;
	}

	public void addAssociatedGroup(Group group) {
		this.associatedGroups.add(group);
	}

}
