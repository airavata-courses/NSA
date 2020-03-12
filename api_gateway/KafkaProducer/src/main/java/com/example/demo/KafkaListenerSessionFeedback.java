package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerSessionFeedback {
	
	SessionRequestTemplate sessionData;
	
	@KafkaListener(topics="sessionhistory-ui", groupId ="group_id")
	public void consume(SessionRequestTemplate message) {
			System.out.println("The session management feeback"+message);
			sessionData=message;
	}
	
	public SessionRequestTemplate returnFeedback() {
		System.out.println("Value returned from session management "+sessionData);
		return sessionData;
	}
	
}