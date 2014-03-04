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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import se.kth.mobdev.ruontime.persistence.GroupDao;
import se.kth.mobdev.ruontime.persistence.IGenericDao;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.UserDao;
import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.Meeting;
import se.kth.mobdev.ruontime.persistence.model.User;
import se.kth.mobdev.ruontime.util.CalendarHelper;

/**
 * @author Jasper
 *
 */

@ManagedBean(name = "creationBean")
@ViewScoped
public class CreationBean {


	private User newUser;
	
	private Group newGroup;
	
	public String createUser(){
		UserDao userDao = PersistenceFactory.getUserDao();
		userDao.save(newUser);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User created", "A new user called " + newUser.getUserName() + " has been created!");
        FacesContext.getCurrentInstance().addMessage(null, msg);  
		return "groups.xhtml";
	}
	
	public String createGroup(){
		GroupDao groupDao = PersistenceFactory.getGroupDao();
		groupDao.save(newGroup);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Group created", "A new group called " + newGroup.getName() + " has been created!");
        FacesContext.getCurrentInstance().addMessage(null, msg);  
		return "groups.xhtml";
	}


	
	@PostConstruct
	public void init(){
		newUser =  new User();
		newGroup = new Group();
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public Group getNewGroup() {
		return newGroup;
	}

	public void setNewGroup(Group newGroup) {
		this.newGroup = newGroup;
	}

}
