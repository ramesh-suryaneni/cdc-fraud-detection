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

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
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
		public BiFunction<KStream<String, String>, GlobalKTable<String, String>, KStream<String, String>> process() {
			
			return (userTransactionsStream, allUsersTable) -> userTransactionsStream
					.leftJoin(allUsersTable,
							(name,value) -> name,
							(transactions, user) -> new UserWithTransactions(user == null ? "UNKNOWN" : user, transactions)
							)
					.map((user, userWithTransactions) -> new KeyValue<>(userWithTransactions.getUser(), userWithTransactions.getTransactions()))
					.groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
					.reduce((firstTransactions, secondTransactions) -> firstTransactions + secondTransactions)
					.toStream();
		}


		}

	private static final class UserWithTransactions {

		private final String user;
		private final String transactions;

		public UserWithTransactions(String user, String transactions) {
			if (user == null || user.isEmpty()) {
				throw new IllegalArgumentException("user must be set");
			}
			if (transactions== null || transactions.isEmpty()) {
				throw new IllegalArgumentException("Transactions must not be negative");
			}
			this.user = user;
			this.transactions = transactions;
		}

		public String getUser() {
			return user;
		}

		public String getTransactions() {
			return transactions;
		}

	}
}
