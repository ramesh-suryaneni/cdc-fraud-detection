package com.example.demo;

public class UserProfile {
	
	private String userId;
	private String name;
	private String surname;
	private String middleName;
	private String suspiciousActivity;
	
	
	public UserProfile(String userId, String name, String surname, String middleName, String suspiciousActivity) {
		super();
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.middleName = middleName;
		this.suspiciousActivity = suspiciousActivity;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getSuspiciousActivity() {
		return suspiciousActivity;
	}


	public void setSuspiciousActivity(String suspiciousActivity) {
		this.suspiciousActivity = suspiciousActivity;
	}
	
	
}