package com.example.demo;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class KafkaListenerLoginFeedback {
	
	public static String loginAckMsg=KafkaProducerConfiguration.LOGIN_FAIL;
	
	@KafkaListener(topics="LoginSuccessMessage", groupId ="group_id")
	public void consume(String message) {
		
		String resp= "LOGIN_SUCCESS";
		
		
		System.out.println("Come inside the Kafka Login feeback "+message);
		System.out.println("message is "+ message + " resp is "+resp);
		if(message.equals(resp)) {
			System.out.println("The login feeback message is success? "+message);
			loginAckMsg=KafkaProducerConfiguration.LOGIN_SUCCESS;
		}else {
			System.out.println("The login feeback message is failed? "+message);	
			loginAckMsg=KafkaProducerConfiguration.LOGIN_FAIL;
		}
		
	}
	
	public String returnFeedback() {
		
		System.out.println("[LOGIN] Value being returned is "+loginAckMsg);
		return loginAckMsg;
		
	}
	
}
