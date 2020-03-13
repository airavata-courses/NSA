package com.example.demo;

public class SessionRequestTemplate {
	//\"userID\":\"shivali\",\"sessID\":\"\",\"input\":\"\",\"output"\:\"\",\"status\":\"\",\"timeStamp\":\"\" }"
	
	

	private String userID;
	
	private String input;
	private String output;
	private String status;
	
	
	public SessionRequestTemplate( String userID,String input, String output, String status) {
		super();
		this.userID = userID;
		
		this.input = input;
		this.output = output;
		this.status = status;
		
	}
	
	
	

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
}
