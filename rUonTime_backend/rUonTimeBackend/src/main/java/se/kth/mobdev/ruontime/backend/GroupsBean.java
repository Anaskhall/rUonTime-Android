/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DualListModel;

import se.kth.mobdev.ruontime.model.Group;
import se.kth.mobdev.ruontime.model.User;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;

/**
 * @author Jasper
 *
 */

@ManagedBean(name = "groupsBean")
public class GroupsBean {
	
//	@ManagedProperty(value = "persistence")
	Persistance store;
	
	private DualListModel<User> assignedUsers;

	private List<Group> allGroups;
	
	private Group selectedGroup;
	
	private boolean showUserlist;
	
	private List<User> allUsers;
	
	private List<User> groupUsers;


	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}
	
	@PostConstruct
	public void init(){
		//fetch all Groups from DataBase
		//allGroups = PersistenceFactory.getGroupDao().getAll();
		//allUsers = PersistenceFactory.getUserDao().getAll();
		this.showUserlist = false;
		
		//FIXME, TESTING ONLY
		allGroups = new ArrayList<Group>();
		allGroups.add(new Group("group 1", null));
		allGroups.add(new Group("group 2", null));
		allGroups.add(new Group("group 3", null));
		Group group4 = new Group("group 4", null);
		selectedGroup = group4;
		allGroups .add(group4);
		
		List<User> source = new ArrayList<User>();
		source.add(new User("firstName", "lastName", 1));
		source.add(new User("fasdairstName", "zlagergerstName", 12));
		source.add(new User("firstdqwfwqName", "aaalastName", 21));
		List<User> target = new ArrayList<User>();
		this.setAssignedUsers(new DualListModel<User>(source, target)); 
		
	}

	public void fetchUsersForGroup(){
		this.showUserlist = true;
		//extract group members
		List<User> participants = selectedGroup.getParticipants();
		this.assignedUsers.setTarget(participants);
		//remove members from list of all members
		this.assignedUsers.getSource().removeAll(participants);
	}
	
	public void saveChanges() {
		//set updated list of members
		System.out.println("the selected group: " + selectedGroup);
		//List<User> newListofParticipants = this.assignedUsers.getTarget();
		//this.selectedGroup.setParticipants(newListofParticipants);
		//persist changes
//		PersistenceFactory.getGroupDao().save(selectedGroup);
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
	
	
}
