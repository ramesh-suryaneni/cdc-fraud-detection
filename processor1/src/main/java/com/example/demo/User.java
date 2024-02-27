package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty("user_id")
	private String userId;
	private String name;
	@JsonProperty("account_locked")
	private String accountLocked;
	
	public User() {} // Default no-argument constructor

	public User(String userId, String name, String accountLocked) {
		super();
		this.userId = userId;
		this.name = name;
		this.accountLocked = accountLocked;
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

	public String getAccountLocked() {
		return accountLocked;
	}

	public void setAccountLocked(String accountLocked) {
		this.accountLocked = accountLocked;
	}
	
	
}