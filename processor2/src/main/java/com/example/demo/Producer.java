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

public class Producer {

	public static void main(String... args) {

		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
		props.put(ProducerConfig.RETRIES_CONFIG, 0);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		MyData json1,json2,json3,json4;
		json1 = new MyData("100", "name", "1", 5, "2", "hyd", null);
		json2 = new MyData("101", "name2", "2", 10, "2", "chennai", null);
		json3 = new MyData("100", "name3", "3", 15, "2", "delhi", null);
		json4 = new MyData("101", "name4", "4", 20, "2", "chennai", null);
		
		
		/* insert user tx - run this block first */ 		

		List<KeyValue<String, MyData>> userTxs = Arrays.asList(
				new KeyValue<>("100", json1),
				new KeyValue<>("101", json2),
				new KeyValue<>("100", json3),
				new KeyValue<>("101", json4)
		);
		
		DefaultKafkaProducerFactory<String, MyData> pf0 = new DefaultKafkaProducerFactory<>(props);
		KafkaTemplate<String, MyData> template0 = new KafkaTemplate<>(pf0, true);
		template0.setDefaultTopic("temp-topic");
		
		for (KeyValue<String,MyData> keyValue : userTxs) {
			template0.sendDefault(keyValue.key, keyValue.value);
		}
		
						
	}

}
