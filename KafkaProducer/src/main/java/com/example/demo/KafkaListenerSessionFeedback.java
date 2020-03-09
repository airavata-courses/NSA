package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerSessionFeedback {
	
	public static String sessionfeedback="failure";
	
	@KafkaListener(topics="sessionhistory-ui", groupId ="group_id")
	public void consume(String message) {
		
		String resp= new String("success");
		message = message.substring(1,message.length()-1);
		
		System.out.println("Come inside the Kafka session management feeback "+message);
		System.out.println("message is "+ message + " resp is "+resp);
		if(message.equals(resp)) {
			System.out.println("The session management message is success? "+message);
			sessionfeedback="success";
		}else {
			System.out.println("The session management feeback message is failed? "+message);	
			sessionfeedback="fail";
		}
		
	}
	
	public String returnFeedback() {
		
		System.out.println("Value being returned is "+sessionfeedback);
		return sessionfeedback;
		
	}
	
}
