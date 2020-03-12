package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.google.gson.JsonArray;



import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONArray;
import org.json.JSONObject;

@Configuration
public class KafkaProducerConfiguration {
	
	public static String LOGIN_SUCCESS="LOGIN_SUCCESS";
	public static String LOGIN_FAIL="LOGIN_FAIL";
	
	public static String REGISTER_SUCCESS="REGISTER_SUCCESS";
	public static String REGISTER_FAIL="REGISTER_FAIL";

	@Bean 
	public  ProducerFactory producerFactory(){
		
		Map<String,Object> config= new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka:9092" );
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class );
				
		return new DefaultKafkaProducerFactory<>(config);
				
	}
	
	
	
	@Bean
	public KafkaTemplate<String,User> kafkaTemplateLoginRegister(){
		return new KafkaTemplate<String,User>(producerFactory());
	}
	

	  @Bean public KafkaTemplate<String,SessionRequestTemplate> kafkaTemplateSession(){ return
	  new KafkaTemplate<String,SessionRequestTemplate>(producerFactory()); }
	 
	  @Bean public KafkaTemplate<String,DataRetrievalTemplate> kafkaTemplateDataRetrieval(){ return
			  new KafkaTemplate<String,DataRetrievalTemplate>(producerFactory()); }
			 
	
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		Map<String,Object> config =new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		
		return new DefaultKafkaConsumerFactory<>(config);
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String,String> factory= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
	}
}