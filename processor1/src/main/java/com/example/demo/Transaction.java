package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {
	
	private String id;
	@JsonProperty("user_id")
	private String userId;  
	private String amount;
	@JsonProperty("tx_type")
	private String TxType;
	private String location;
	private String timestamp;
	
	public Transaction() {} // Default no-argument constructor

	public Transaction(String id, String userId, String amount, String txType, String location, String timestamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		TxType = txType;
		this.location = location;
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

	public String getTxType() {
		return TxType;
	}

	public void setTxType(String txType) {
		TxType = txType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
		
		

}
