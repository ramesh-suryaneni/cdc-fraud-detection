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
		//public BiFunction<KStream<String, String>, KTable<String, String>, KStream<String, UserWithTransaction>> process() {
		public BiFunction<KStream<String, Transaction>, KTable<String, User>, KStream<String, UserWithTransaction>> process() {
		    return (transactionStream, userTable) -> transactionStream
		        // Left join based on user ID (assuming both have String keys)
		        .leftJoin(userTable, (transaction, user) -> new UserWithTransaction(transaction.getUserId() == null? "UNKNOWN" :transaction.getUserId(),user, transaction));
		    	//.leftJoin(userTable, (transaction, user) -> new JoinRecord(user, transaction));
		    
		}

	}
	
	private static final class JoinRecord {
		private final String user;
		private final String transaction;
		public JoinRecord(String user, String transaction) {
			super();
			this.user = user;
			this.transaction = transaction;
		}
		public String getUser() {
			return user;
		}
		public String getTransaction() {
			return transaction;
		}
		
		
	}

	private static final class UserWithTransaction {

		private final String userId;
		private final User user;
		private final Transaction transaction;
		public UserWithTransaction(String userId, User user, Transaction transaction) {
			super();
			this.userId = userId;
			this.user = user;
			this.transaction = transaction;
		}
		public String getUserId() {
			return userId;
		}
		public User getUser() {
			return user;
		}
		public Transaction getTransaction() {
			return transaction;
		}
		
		
	}
	
}
	    
	    
	    

