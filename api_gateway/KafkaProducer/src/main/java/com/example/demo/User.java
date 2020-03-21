package com.example.demo;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


public class User {
//	
//	private int id;
//	

	private String firstName;
	
	
	private String userID;
	
	
	private String lastName;
	
	
	private String email;
	
	
	private String password;
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public User(int id, String firstName, String lastName, String email,String
	 * userName,String password) { super(); // this.id = id; this.firstName =
	 * firstName; this.lastName = lastName; this.email = email;
	 * this.userName=userName; this.password=password;
	 * 
	 * }
	 */
	
	public User( String firstName, String lastName, String email,String userID,String password) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userID=userID;
		this.password=password;
		
	}
	
	
	
	public User() {
	}


	/*
	 * public int getId() { return id; } public void setId(int id) { this.id = id; }
	 */
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	

}
