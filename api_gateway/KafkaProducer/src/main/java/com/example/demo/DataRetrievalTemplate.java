package com.example.demo;

public class DataRetrievalTemplate {

	
	private String day;
	private String month;
	private String year;
	private String radarID;
	private String userName;
	
	
	public DataRetrievalTemplate( String day, String month, String year, String radarID,
			String userName) {
		super();
		
		this.day = day;
		this.month = month;
		this.year = year;
		this.radarID = radarID;
		this.userName = userName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	 
	
}
