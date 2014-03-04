/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.DualListModel;

import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.User;

/**
 * @author Jasper
 *
 */

@ManagedBean(name = "groupsBean")
@ViewScoped
public class GroupsBean {
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	private DualListModel<User> assignedUsers;

	private List<Group> allGroups;
	
	private Group selectedGroup;
	
	private boolean showUserlist;
	
	private List<User> allUsers;
	
	private List<User> groupUsers;

	public void deleteGroup() {
		System.out.println("deleting group");
		PersistenceFactory.getGroupDao().delete(selectedGroup);
		selectedGroup = null;
	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
//		System.out.println("setting selected group: " + selectedGroup);
		this.showUserlist = true;
		this.selectedGroup = selectedGroup;
	}
	
	@PostConstruct
	public void init(){
		//fetch all Groups from DataBase
		this.showUserlist = false;
		
		//FIXME, TESTING ONLY
//		allGroups = ServiceFactory.getGroupService().getAllGroups(this.loginBean.getLoggedInUser());
		allGroups = PersistenceFactory.getGroupDao().getAll();
		
		List<User> source = PersistenceFactory.getUserDao().getAll();
		this.allUsers = source;
		List<User> target = new ArrayList<User>();
		if(!allGroups.isEmpty()) {
			target  = allGroups.get(0).getParticipants();
		}
		this.setAssignedUsers(new DualListModel<User>(source, target )); 
		
	}

	public void fetchUsersForGroup(){
		this.showUserlist = true;
		//extract group members
//		System.out.println("fetching users for group - the selected group: " + selectedGroup);
		List<User> participants = new ArrayList<User>();
		if(selectedGroup!=null){
			 participants = selectedGroup.getParticipants();
		}
//		System.out.println("participants of group:" + participants);
		this.assignedUsers.setTarget(participants);
		//remove members from list of all members
		List<User> source = this.assignedUsers.getSource();
//		System.out.println("available users:" + participants);
		source.removeAll(participants);
	}
	
	public void saveChanges() {
		//set updated list of members
		System.out.println("the selected group: " + selectedGroup);
		List<User> newListofParticipants = this.assignedUsers.getTarget();
		this.selectedGroup.setParticipants(newListofParticipants);
		//persist changes
		PersistenceFactory.getGroupDao().save(selectedGroup);
	}
	
	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public List<User> getGroupUsers() {
		return groupUsers;
	}

	public void setGroupUsers(List<User> groupUsers) {
		this.groupUsers = groupUsers;
	}

	public DualListModel<User> getAssignedUsers() {
		return assignedUsers;
	}

	public void setAssignedUsers(DualListModel<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}

	public List<Group> getAllGroups() {
		return allGroups;
	}

	public void setAllGroups(List<Group> allGroups) {
		this.allGroups = allGroups;
	}

	public boolean isShowUserlist() {
		return showUserlist;
	}

	public void setShowUserlist(boolean showUserlist) {
		this.showUserlist = showUserlist;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}
	
	
}
