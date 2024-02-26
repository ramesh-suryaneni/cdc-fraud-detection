package com.example.demo;

public class Transaction {
	
	private String id;
	private String userId;  
	private String amount;
	private String currency;
	private String type;
	private String country;
	private String timestamp;
		
	public Transaction(String id, String userId, String amount, String currency, String type, String country,
			String timestamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.currency = currency;
		this.type = type;
		this.country = country;
		this.timestamp = timestamp;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	

}
