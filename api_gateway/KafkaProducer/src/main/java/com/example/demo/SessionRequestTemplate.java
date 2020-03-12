package com.example.demo;

public class SessionRequestTemplate {
	//\"userID\":\"shivali\",\"sessID\":\"\",\"input\":\"\",\"output"\:\"\",\"status\":\"\",\"timeStamp\":\"\" }"
	


	private String userID;


	private String day;
	private String month;
	private String year;
	private String radarID;
	private String output;
	private String jobstatus;
	private String jobtype;

	

	
	public SessionRequestTemplate(String userID,String day, String month, String year, String radarID, String output,
			String jobstatus, String jobtype) {
		super();
		this.userID = userID;
		this.day = day;
		this.month = month;
		this.year = year;
		this.radarID = radarID;
		this.output = output;
		this.jobstatus = jobstatus;
		this.jobtype = jobtype;
		
	}

	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getRadarID() {
		return radarID;
	}
	public void setRadarID(String radarID) {
		this.radarID = radarID;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getJobstatus() {
		return jobstatus;
	}
	public void setJobstatus(String jobstatus) {
		this.jobstatus = jobstatus;
	}
	public String getJobtype() {
		return jobtype;
	}
	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
	}
	
}
	
	
	
