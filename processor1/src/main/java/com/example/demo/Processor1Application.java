/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.demo;

import java.util.function.BiFunction;

import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Processor1Application {

	public static void main(String[] args) {
		SpringApplication.run(Processor1Application.class, args);
	}

	public static class KStreamToTableJoinApplication {
		
				
		@Bean
		public BiFunction<KStream<String, String>, KTable<String, String>, KStream<String, UserWithTransaction>> process() {
		    return (transactionStream, userProfileTable) -> transactionStream
		        // Left join based on user ID (assuming both have String keys)
		        .leftJoin(userProfileTable, (transaction, userProfile) -> new UserWithTransaction(userProfile, transaction));
		}

	}

	private static final class UserWithTransaction {

		private final String userProfile;
		private final String transaction;
		
		public String getUserProfile() {
			return userProfile;
		}
		public String getTransaction() {
			return transaction;
		}
		public UserWithTransaction(String userProfile, String transaction) {
			this.userProfile = userProfile;
			this.transaction = transaction;
		}

		

	}
	class Transaction {
		
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
	
	class UserProfile {
		
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
	    
	    
	    
}
