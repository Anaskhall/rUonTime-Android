package se.kth.mobdev.ruontime.persistence.model;
import javax.persistence.Column;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;  
import javax.persistence.Id;  
import javax.persistence.Table;  
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import se.kth.mobdev.ruontime.persistence.IEntity;
  
@Entity  
@Table(name="users")
public class User implements IEntity{  
      
    @Id  
    @GeneratedValue 
    @Column(name="user_id")
    private Integer id;  
    
    @Column(unique = true)
    private String userName;
      
    private String firstName;
    private String lastName;  
    
    @Column(name = "password")
    private String  pwHash;
      
    public User() {};  
      
    public User(String userName, String firstName, String lastName, String pwHash) {  
        this.userName = userName;
        this.setFirstName(firstName);  
        this.setLastName(lastName);
        this.pwHash =pwHash;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwHash() {
		return pwHash;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}  
      
	@Transient
    public String getFullName(){
    	return this.firstName + " " + lastName;
    }
  
} 