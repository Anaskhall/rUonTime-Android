/**
 * 
 */
package se.kth.mobdev.ruontime.model;

import java.util.UUID;

import javax.persistence.Id;

import se.kth.mobdev.ruontime.persistence.IEntity;

/**
 * @author Jasper
 *
 */
public class User implements IEntity{

	private static final long serialVersionUID = 8580240078002020951L;

	@Id
	private UUID id;

	private String username;
	
	private String pwHash;
	
	private String firstName;
	private String lastName;
	
	private int age;
	

	public User(String firstName, String lastName, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean cheackPassword(String pw) {
		return true;
	}

	@Override
	public String toString() {
		return getFullName() + " (" + getUsername() +")";
		
	}
	public String getPwHash() {
		return pwHash;
	}

	public void setPwHash(String pwHash) {
		this.pwHash = pwHash;
	}
	
	@Override
	public UUID getId() {
		return this.id;
	}

	@Override
	public void setId(UUID id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
