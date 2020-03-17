package com.example.demo;

public class DataRetrievalTemplate {

	
	private String day;
	private String month;
	private String year;
	private String radarID;
	private String userID;
	
	
	public DataRetrievalTemplate( String day, String month, String year, String radarID,
			String userID) {
		super();
		
		this.day = day;
		this.month = month;
		this.year = year;
		this.radarID = radarID;
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
	public String getuserID() {
		return userID;
	}
	public void setuserID(String userID) {
		this.userID = userID;
	}
	
	 
	
}
