package com.example.demo;

public class UserProfile {
	
	private String user_id;
	private String name;
	private String surname;
	private String middle_name;
	private String suspicious_activity;
	
	
	
	public UserProfile(String user_id, String name, String surname, String middle_name, String suspicious_activity) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.surname = surname;
		this.middle_name = middle_name;
		this.suspicious_activity = suspicious_activity;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getSuspicious_activity() {
		return suspicious_activity;
	}
	public void setSuspicious_activity(String suspicious_activity) {
		this.suspicious_activity = suspicious_activity;
	}

}
