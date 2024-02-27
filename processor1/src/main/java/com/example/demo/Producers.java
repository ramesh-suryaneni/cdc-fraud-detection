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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KeyValue;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Producers {

	public static void main(String... args) {

		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		User json1,json2;
		Transaction json3,json4;
		json1 = new User("100", "name", null);
		json2 = new User("101", "name1", null);
		
		json3 = new Transaction("1", "100", "5", "Txtype", "location",null);
		json4 = new Transaction("2", "100", "10", "Txtype", "location",null);
		
		
		/* insert user profile to Table - run this block first */ 		

		List<KeyValue<String, User>> users = Arrays.asList(
				new KeyValue<>("100", json1),
				new KeyValue<>("101", json2)
		);
		
		DefaultKafkaProducerFactory<String, User> pf0 = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, User> template0 = new KafkaTemplate<>(pf0, true);
		template0.setDefaultTopic("mysql.mydb.user");
		
		for (KeyValue<String,User> keyValue : users) {
			template0.sendDefault(keyValue.key, keyValue.value);
		}
		
		/* insert transactions to stream - run this block second by commenting out above user profile */
		List<KeyValue<String, Transaction>> transactions = Arrays.asList(
			
				new KeyValue<>("100", json3),
				new KeyValue<>("100", json4)
		);
		
		DefaultKafkaProducerFactory<String, Transaction> pf1 = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, Transaction> template1 = new KafkaTemplate<>(pf1, true);
		template1.setDefaultTopic("mysql.mydb.transactions");

		for (KeyValue<String,Transaction> keyValue : transactions) {
			template1.sendDefault(keyValue.key, keyValue.value);
		}
				
	}

}
