package com.nsa.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.nsa.app.model.User;
import com.nsa.app.repository.UserRepository;

@Service
public class KafkaConsumer {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	private static final String TOPIC_LOGIN_ACK= "LoginSuccessMessage";
	private static final String TOPIC_REGISTER_ACK= "RegisterSuccessMessage";

	@KafkaListener(topics="KafkaLoginMessage", groupId ="group_id")
	public void consumeLogin(String message) {
//		
		System.out.println("Come inside the Kafka consumer ");
//		
//		// Use repository to check database based on the value given from MB to service
		User answer= userRepository.findByuserName(message);
		System.out.println("Consumed Message is"+message);
		String newmessage= message.substring(4,message.length()-4);
		
		
		String [] values= newmessage.split(" ");
		System.out.println("db check output is"+values);
		
		User user= userRepository.findByuserName(values[0]);
		System.out.println("Values from database: "+userRepository.findByuserName(values[0]));	
		//boolean userExists=userRepository.existsById(1);
//		
		if(user != null ) {
			System.out.println("db pass "+user.getPassword()+ "db username "+user.getUserName());
			System.out.println("value pass "+values[0]+ "value username "+values[4]);
			if((user.getPassword().equals(values[4])) && (user.getUserName().equals(values[0]))) {
				kafkaTemplate.send(TOPIC_LOGIN_ACK,"success");
			}
			else {
				kafkaTemplate.send(TOPIC_LOGIN_ACK,"fail");
			}
		}
		else {
			kafkaTemplate.send(TOPIC_LOGIN_ACK,"fail");
//			// return message
	}
//		
	}
//	
//	
	
	@KafkaListener(topics="KafkaRegisterMessage", groupId ="group_id")
	public void consumeRegister(String message) {
		
		System.out.println("Come inside the Kafka consumer register method");
		
		// Use repository to check database based on the value given from MB to service
		
		System.out.println("Input for register is "+message);
		String newmessage= message.substring(4,message.length()-4);
		String [] values= newmessage.split(" ");
		System.out.println("db check output is"+values);
		User user = new User();
		user.setUserName(values[0]);
		user.setFirstName(values[1]);
		user.setLastName(values[2]);
		user.setEmail(values[3]);
		user.setPassword(values[4]);
		
		if(userRepository.findByuserName(values[0])== null) {
			System.out.println("User doesnot exist, hence ");
		
		userRepository.save(user);
		kafkaTemplate.send(TOPIC_REGISTER_ACK,"success");
		}
		else {
			kafkaTemplate.send(TOPIC_REGISTER_ACK,"error");
		}
	}
	
	
//	@KafkaListener(topics="KafkaDRMessage", groupId ="group_id")
//	public void consumeDataRetrieval(String message) {
//		
//		System.out.println("Come inside the Kafka consumer data retrieval method");
//		
//		// Use repository to check database based on the value given from MB to service
//		
//		System.out.println("Input for DATA RETRIEVAL is "+message);
//		
//		
//		
//	}
}
