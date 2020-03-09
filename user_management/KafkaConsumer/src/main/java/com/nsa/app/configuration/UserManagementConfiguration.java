package com.nsa.app.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.nsa.app.UserInput;
import com.nsa.app.model.SessionLog;
import com.nsa.app.model.User;

@EnableKafka
@Configuration
public class UserManagementConfiguration {
	
	private static final String TOPIC_LOGIN_ACK= "LoginSuccessMessage";
	private static final String TOPIC_REGISTER_ACK= "RegisterSuccessMessage";
	private static final String TOPIC_SESSION_LOG="SessionLog";

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
	public DefaultKafkaConsumerFactory<String, UserInput> userconsumerFactory(){
		Map<String,Object> config =new HashMap<>();
		
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.example.demo.User");
		
		return new DefaultKafkaConsumerFactory<String,UserInput>(config, new StringDeserializer(), new JsonDeserializer<>(UserInput.class,false));
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,UserInput> userKafkaListenerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String,UserInput> factory= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(userconsumerFactory());
		
		return factory;
		
	}
	
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
		
		ConcurrentKafkaListenerContainerFactory<String,String> factory= new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		
		return factory;
		
	}
	
	
	// Usermanagement producer to return the response of login
	@Bean 
	public  ProducerFactory producerFactory(){
		
		Map<String,Object> config= new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka:9092" );
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
				
		return new DefaultKafkaProducerFactory<>(config);
				
	}
	
	
	@Bean 
	public  ProducerFactory producerFactorySessionLog(){
		
		Map<String,Object> config= new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"kafka:9092" );
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class );
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class );
				
		return new DefaultKafkaProducerFactory<>(config);
				
	}
	
	@Bean
	public KafkaTemplate<String,String> kafkaTemplate(){
		return new KafkaTemplate<String,String>(producerFactory());
	}
	
	
	@Bean
	public KafkaTemplate<String,SessionLog> kafkaTemplateSessionLog(){
		return new KafkaTemplate<String,SessionLog>(producerFactorySessionLog());
	}
	
}
