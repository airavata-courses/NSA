package com.nsa.app;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nsa.app.UserInput;
import com.nsa.app.model.*;

import com.nsa.app.repository.UserRepository;

@Service
public class KafkaConsumer {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	private static final String TOPIC_LOGIN_ACK= "LoginSuccessMessage";
	private static final String TOPIC_REGISTER_ACK= "RegisterSuccessMessage";
	private static final String TOPIC_SESSION_LOG="SessionLog";
	
	@Autowired
	KafkaTemplate<String,SessionLog> kafkaTemplateSessionLog;

	@KafkaListener(topics="KafkaLoginMessage", containerFactory="userKafkaListenerFactory")
	public void consumeLogin(UserInput message) throws JSONException {		
	
		System.out.println("message received is in login"+message);
		
		String username=message.getUserName();
		String password=message.getPassword();
	
		  User user= userRepository.findByuserName(username);
		  
		  
		  if(user != null ) {
				System.out.println("db pass "+user.getPassword()+ "db username "+user.getUserName());
				System.out.println("value pass "+message.getPassword()+ "value username "+message.getUserName());
				if((user.getPassword().equals(message.getPassword())) && (user.getUserName().equals(message.getUserName()))) {
					System.out.println("SUCESSS>>>>>>>>>>>>>>>>>>>>>");
					kafkaTemplate.send(TOPIC_LOGIN_ACK,"LOGIN_SUCCESS");
					
					// send message to session-mgmt userid+ login_success
					 
					SessionLog sessionLog = new SessionLog();
					sessionLog.setStatus("LOGIN_SUCCESS");
					sessionLog.setUserName(username);
					
//					JsonObject sessionlogObj = new JsonObject();
//					sesessionlogObj.put("status","LOGIN_SUCCESS");
//		
					kafkaTemplateSessionLog.send(TOPIC_SESSION_LOG,sessionLog);
				}
				else {
					System.out.println("Faileeeeeeeeeeeeeeeeee");
					kafkaTemplate.send(TOPIC_LOGIN_ACK,"LOGIN_FAIL");
				}
			}
			else {
				System.out.println("HSSKVFPdkl");
				kafkaTemplate.send(TOPIC_LOGIN_ACK,"LOGIN_FAIL");

		}	
	}

	
	@KafkaListener(topics="KafkaRegisterMessage", containerFactory="userKafkaListenerFactory")
	public void consumeRegister(UserInput message) {
		
		System.out.println("Come inside the Kafka consumer register method");

		User user = new User();
		user.setUserName(message.getUserName());
		user.setFirstName(message.getFirstName());
		user.setLastName(message.getLastName());
		user.setEmail(message.getEmail());
		user.setPassword(message.getPassword());
		
		if(userRepository.findByuserName(message.getUserName())== null) {
			System.out.println("User doesnot exist, hence ");
		userRepository.save(user);
		kafkaTemplate.send(TOPIC_REGISTER_ACK,"REGISTER_SUCCESS");
		}
		else {
			kafkaTemplate.send(TOPIC_REGISTER_ACK,"REGISTER_FAIL");
		}
	}
}
