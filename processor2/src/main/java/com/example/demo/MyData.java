package com.example.demo;

public class MyData {
		private  String userId;
		private  String name;
		
		private  String transactionId;
		private  long amount;
		private  String transactionType;
		private  String location;
		private  String timestamp;
		
		public MyData() {}
		
		public MyData(String userId, String name, String transactionId, long amount, String transactionType,
				String location, String timestamp) {
			super();
			this.userId = userId;
			this.name = name;
			this.transactionId = transactionId;
			this.amount = amount;
			this.transactionType = transactionType;
			this.location = location;
			this.timestamp = timestamp;
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

		public String getTransactionId() {
			return transactionId;
		}

		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}

		public long getAmount() {
			return amount;
		}

		public void setAmount(long amount) {
			this.amount = amount;
		}

		public String getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
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
