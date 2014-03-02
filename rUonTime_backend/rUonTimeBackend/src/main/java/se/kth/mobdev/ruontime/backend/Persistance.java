package se.kth.mobdev.ruontime.backend;

import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import se.kth.mobdev.ruontime.model.Meeting;


@ManagedBean(name="persistence")
@ApplicationScoped
public class Persistance implements IStorage {

	@Override
	public void createNewMeeting(Meeting newMeeting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMeeting(Meeting meeting) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMeeting(UUID meetingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retrieveMeeting(UUID meetingId) {
		// TODO Auto-generated method stub
		
	}

}
