/**
 * 
 */
package se.kth.mobdev.ruontime.model;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
@Entity
public class CheckIn implements IEntity{

	@Id
	private UUID uId;
	
	private static final long serialVersionUID = -6540274176773646199L;

	
	private User associatedUser;
	
	private Calendar timestamp;
	
	private int minutesLate;
	
	private boolean firstToShowUp;
	
	private boolean lastToShowUp;
	

	public CheckIn(User associatedUser, Calendar timestamp, int minutesLate,
			boolean firstToShowUp, boolean lastToShowUp) {
		super();
		this.associatedUser = associatedUser;
		this.timestamp = timestamp;
		this.minutesLate = minutesLate;
		this.firstToShowUp = firstToShowUp;
		this.lastToShowUp = lastToShowUp;
	}

	public User getAssociatedUser() {
		return associatedUser;
	}

	public Calendar getTimestamp() {
		return timestamp;
	}

	public int getMinutesLate() {
		return minutesLate;
	}

	public boolean isFirstToShowUp() {
		return firstToShowUp;
	}

	public boolean isLastToShowUp() {
		return lastToShowUp;
	}

	@Override
	public UUID getId() {
		return this.uId;
	}

	@Override
	public void setId(UUID id) {
		this.uId = id;
	}

}
