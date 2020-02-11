package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerRegisterFeeback {
	
	public static String registerAckMsg="failure";

	@KafkaListener(topics="RegisterSuccessMessage", groupId ="group_id")
	public String consume(String message) {
		
		String resp= new String("success");
		message = message.substring(1,message.length()-1);
		
		System.out.println("Come inside the Kafka Login feeback "+message);
		System.out.println("message is "+ message + " resp is "+resp);
		if(message.equals(resp)) {
			System.out.println("The register feeback message is success? "+message);
			registerAckMsg="success";
		}else {
			System.out.println("The register feeback message is failed? "+message);	
			registerAckMsg="fail";
		}
		return registerAckMsg;
		
	}
	
	
public String returnFeedback() {
		
		System.out.println("Value from register feedback "+registerAckMsg);
		return registerAckMsg;
		
	}
}
