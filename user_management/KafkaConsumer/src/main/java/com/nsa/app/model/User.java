package com.nsa.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "firstName")
	@NotEmpty(message = "*Please provide your first name")
	private String firstName;
	
	@Column(name = "userID")
	@NotEmpty(message = "*Please provide your user id")
	private String userID;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "emailId")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public User(int id, String firstName, String lastName, String email,String userID,String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userID=userID;
		this.password=password;
		
	}
	
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


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		this.userID= userID;
	}
	

}
