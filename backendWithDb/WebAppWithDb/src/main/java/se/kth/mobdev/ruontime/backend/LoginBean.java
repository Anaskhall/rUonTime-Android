/**
 * 
 */
package se.kth.mobdev.ruontime.backend;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import se.kth.mobdev.ruontime.persistence.IGenericDao;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.User;
import se.kth.mobdev.ruontime.service.ServiceFactory;
import se.kth.mobdev.ruontime.service.UserAuthenticationService;

/**
 * @author Jasper
 * 
 */

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable{

	private static final long serialVersionUID = -5120910124095398793L;

	private String username;

	private String pw;
	
	private User loggedInUser;
	
	@PostConstruct
	public void init(){
		System.out.println("LoginBean created!");
		//retireve DAO to fetch user from DB
//		IGenericDao<User> userDao = PersistenceFactory.getUserDao();
//		User testUser =  new User("Jas", "H", 22);
//		userDao.save(testUser);
//		System.out.println("persisted user!");
//		
//		List<User> all = userDao.getAll();
//		System.out.println("all users in DB: " + all);
	}

	public String login() {  
        RequestContext context = RequestContext.getCurrentInstance();  
        FacesMessage msg = null;  
        boolean loggedIn = false;  
        UserAuthenticationService userAuthService = ServiceFactory.getUserAuthService();
        loggedIn = userAuthService.login(username, pw);
        if(loggedIn) {  
        	loggedInUser = userAuthService.getUser(username);
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome ", username);  
        } else {  
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid username/password combination");  
        }  
          
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        context.addCallbackParam("loggedIn", loggedIn);
        
        this.setLoggedInUser(loggedInUser);
        
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
