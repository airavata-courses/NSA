package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerRegisterFeedback {
	
	public static String registerAckMsg=KafkaProducerConfiguration.REGISTER_FAIL;

	@KafkaListener(topics="RegisterSuccessMessage", groupId ="group_id")
	public String consume(String message) {
		
		String resp= "REGISTER_SUCCESS";
		
		
		System.out.println("Come inside the Kafka Login feeback "+message);
		System.out.println("message is "+ message + " resp is "+resp);
		if(message.equals(resp)) {
			System.out.println("The register feeback message is success? "+message);
			registerAckMsg=KafkaProducerConfiguration.REGISTER_SUCCESS;
		}else {
			System.out.println("The register feeback message is failed? "+message);	
			registerAckMsg=KafkaProducerConfiguration.REGISTER_FAIL;
		}
		return registerAckMsg;
		
	}
	
	
public String returnFeedback() {
		
		System.out.println("Value from register feedback "+registerAckMsg);
		return registerAckMsg;
		
	}
}
