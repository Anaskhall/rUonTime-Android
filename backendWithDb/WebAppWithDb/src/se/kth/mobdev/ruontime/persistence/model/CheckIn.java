package se.kth.mobdev.ruontime.persistence.model;

import java.util.Calendar;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
@Entity
@Table(name="checkin")
public class CheckIn implements IEntity{

	@Id
	@Column(name = "check_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private static final long serialVersionUID = -6540274176773646199L;

	@OneToOne
    @JoinColumn(name = "user_ref", referencedColumnName = "user_id")
	private User associatedUser;	
	
	@Column(name = "time")
	private Calendar timestamp;
	
	@Column(name = "late")
	private int minutesLate;
	
	@Column(name = "FirstToShow")
	private boolean firstToShowUp;
	
	@Column(name = "LastToShow")
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

	public void setTimestamp(Calendar timestamp) {
		this.timestamp = timestamp;
	}

	public int getMinutesLate() {
		return minutesLate;
	}

	public void setMinutesLate(int minutesLate) {
		this.minutesLate = minutesLate;
	}

	public boolean isFirstToShowUp() {
		return firstToShowUp;
	}

	public void setFirstToShowUp(boolean firstToShowUp) {
		this.firstToShowUp = firstToShowUp;
	}

	public boolean isLastToShowUp() {
		return lastToShowUp;
	}

	public void setLastToShowUp(boolean lastToShowUp) {
		this.lastToShowUp = lastToShowUp;
	}

	public void setAssociatedUser(User associatedUser) {
		this.associatedUser = associatedUser;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
