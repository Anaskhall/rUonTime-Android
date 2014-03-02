/**
 * 
 */
package se.kth.mobdev.ruontime.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.persistence.Id;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
public class Meeting implements IEntity{

	private static final long serialVersionUID = -4163119070210143402L;

	@Id
	private UUID id;


	private Group associatedGroup;
	
	private String title;
	
	private Calendar startsAt;
	
	private Calendar endsAt;
	
	private List<User> shouldAttend;
	
	private List<User> guests;
	
	
	/**
	 * Essential constructor with minimum amount of necessary information
	 * 
	 * @param title
	 * @param associatedGroup
	 * @param startsAt
	 */
	public void Meeting(String title, Group associatedGroup, Calendar startsAt){
		this.title = title;
		
		this.associatedGroup = associatedGroup;
				
		this.startsAt = startsAt;
		
		this.shouldAttend = new ArrayList<User>();
		
		this.guests = new ArrayList<User>();
		
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

	public Group getAssociatedGroup() {
		return associatedGroup;
	}

	public List<User> getShouldAttend() {
		return shouldAttend;
	}

	public List<User> getGuests() {
		return guests;
	}
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public void setAssociatedGroup(Group associatedGroup) {
		this.associatedGroup = associatedGroup;
	}

	public void setShouldAttend(List<User> shouldAttend) {
		this.shouldAttend = shouldAttend;
	}

	public void setGuests(List<User> guests) {
		this.guests = guests;
	}
	
}
