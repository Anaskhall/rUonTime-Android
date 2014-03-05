package com.android.ruontime.model;

public class User {

	private String username;
	private String pwHash;
	private String firstName;
	private String lastName;

	public User(String username, String password) {
		super();
		this.username = username;
		this.pwHash = password;
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
	
	
}
