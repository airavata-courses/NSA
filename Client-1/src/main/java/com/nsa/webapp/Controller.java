package com.nsa.webapp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Controller {

	RestTemplate restTemplate = new RestTemplate();
//	
//	@RequestMapping("/login/{username}")
//	public User getUser(@PathVariable("username") String username) {
//		return new  User(1,"Shivali","Jejurkar","s@gmail.com");
//	}
	
	//Call server ms and pass user information over there
	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@RequestMapping("/login")
	public String callLogin(@RequestBody String input) {
//
//		String status=restTemplate.postForObject("http://localhost:8082/login",input, String.class);
//		System.out.println("Controller 1"+input);
		
		// PASS to Kafka producer 
		SimpleProducer kafkaProducer = new SimpleProducer();
		kafkaProducer.inputToProducer(input,"LoginKafkaMsg");
       
		return "from client/apigateway";
	}
	
	
	@RequestMapping("/")
	public String showLoginForm() {

		return "Login.jsp";
	}
	
}
