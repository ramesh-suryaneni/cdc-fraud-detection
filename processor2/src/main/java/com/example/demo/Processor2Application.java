package com.example.demo;

import java.time.Duration;
import java.util.function.Function;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Processor2Application {
	public static void main(String[] args) {
		SpringApplication.run(Processor2Application.class, args);
	}
	
	@Bean
	public Function<KStream<String, MyData>, KStream<String, Long>> process() {
		return sourceStream -> {
			// Step 1: Group by key and save state in memory
			KGroupedStream<String, MyData> groupedStream = sourceStream.groupByKey();
			// Step 2: Apply 1-hour sliding window and aggregate sum
			KTable<Windowed<String>, Long> windowedAggregation = groupedStream
					.windowedBy(TimeWindows.of(Duration.ofHours(1)))
					.aggregate(
		                    () -> 0L, // Initializer: start with 0
		                    (key, value, aggregate) -> aggregate + value.getAmount(), // Aggregator: sum the amounts
		                    Materialized.with(Serdes.String(), Serdes.Long()) // Serdes for the result
		            );
			// Convert the windowed KTable to a KStream
			KStream<String, Long> outputStream = windowedAggregation.toStream()
			    .map((key, value) -> new KeyValue<>(key.key(), value));
					
			return outputStream;

		};
	}
	
}
