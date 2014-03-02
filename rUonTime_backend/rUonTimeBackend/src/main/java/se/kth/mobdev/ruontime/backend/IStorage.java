/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.UUID;

import se.kth.mobdev.ruontime.model.Meeting;

/**
 * @author Jasper
 *
 */
public interface IStorage {
	
	public void createNewMeeting(Meeting newMeeting);
	
	void updateMeeting(Meeting meeting);
	
	void deleteMeeting(UUID meetingId);
	
	void retrieveMeeting(UUID meetingId);

}
