/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import se.kth.mobdev.ruontime.model.User;
import se.kth.mobdev.ruontime.persistence.IGenericDao;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;

/**
 * @author Jasper
 * 
 */

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	private String username;

	private String pw;
	
	private User loggedInUser;
	
	@PostConstruct
	public void init(){
		System.out.println("LoginBean created!");
		//retireve DAO to fetch user from DB
		IGenericDao<User> userDao = PersistenceFactory.getUserDao();
		User testUser =  new User("Jas", "H", 22);
		userDao.save(testUser);
		System.out.println("persisted user!");
		
		List<User> all = userDao.getAll();
		System.out.println("all users in DB: " + all);
	}

	public String login() {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
          
        if(username != null  && username.equals("admin") && pw != null  && pw.equals("admin")) {  
            loggedIn = true;  
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome ", username);  
        } else {  
            loggedIn = false;  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid username/password combination");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);
        
        this.setLoggedInUser(new User("A", "B", 20));
        
        return "welcome.xhtml";
        
    }	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

}
