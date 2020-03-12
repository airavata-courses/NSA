package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerDataRetrieval {

	
	public static String postProcessAckMsg="failure";
	
	@KafkaListener(topics="postprocess-messagehandler", groupId ="group_id")
	public void consumePPResult(String message) {		//replace with the data returned by PP module
		
		String resp= new String("success");
		message = message.substring(1,message.length()-1);
		
		System.out.println("Come inside the Kafka Login feeback "+message);
		System.out.println("message is "+ message + " resp is "+resp);
		if(message.equals(resp)) {
			System.out.println("The post processing feeback message is success? "+message);
			postProcessAckMsg="success";
			System.out.println("Message set to: " + postProcessAckMsg);
		}else {
			System.out.println("The post processing feeback message is failed? "+message);	
			postProcessAckMsg="fail";
		}
		
	}
	
	public String returnFeedback() {
		
		System.out.println("Value being returned is from PP feedback "+postProcessAckMsg);
		return postProcessAckMsg;
		
	}
}
