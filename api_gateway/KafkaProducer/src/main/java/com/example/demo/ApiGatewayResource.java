package com.example.demo;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.common.requests.ApiVersionsResponse;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("kafka")
public class ApiGatewayResource {

	@Autowired
	KafkaTemplate<String,String> kafkaTemplate;
	private static final String TOPIC_LOGIN_MESSAGE= "KafkaLoginMessage";
	private static final String TOPIC_REGISTER_MESSAGE= "KafkaRegisterMessage";
	private static final String TOPIC_DATARETRIVE_MESSAGE= "messagehandler-dataretrieval";
	private static final String TOPIC_SESSION_MESSAGE = "ui-sessionhistory";
	
	@Autowired
	KafkaListenerRegisterFeeback registerAckService;
	@Autowired
	KafkaListenerLoginFeeback loginAckService;
	@Autowired
	KafkaListenerDataRetrieval dataAckService;
	@Autowired
	KafkaListenerSessionFeedback sessionAckService;

	
	  @Autowired KafkaTemplate<String,SessionRequestTemplate> kafkaTemplateSession;
	  
	
	@CrossOrigin(origins = "http://ui:3000", maxAge = 3600)
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes = "application/json")
	public String login(@RequestBody String message) throws InterruptedException {
		//String message=user.getFirstName();
		System.out.println("**************** OTIIII **********"+ message);
		kafkaTemplate.send(TOPIC_LOGIN_MESSAGE,message);
		
		String ack= loginAckService.returnFeedback();
		System.out.println("ack is"+ack);
		System.out.println("condition is"+ ack.equalsIgnoreCase("checking"));
	//	while(ack.equalsIgnoreCase("checking")) {
		TimeUnit.SECONDS.sleep(2);
		ack= loginAckService.returnFeedback();
		//}
		return ack;
	}
	
	@CrossOrigin(origins = "http://ui:3000", maxAge = 3600)
	@RequestMapping(value="/register", method = RequestMethod.POST, consumes = "application/json")
	public String register(@RequestBody String message) throws InterruptedException {
		kafkaTemplate.send(TOPIC_REGISTER_MESSAGE,message);
		
		String ack= registerAckService.returnFeedback();
		System.out.println("ack is"+ack);
		System.out.println("condition is"+ ack.equalsIgnoreCase("failure"));
	//	while(ack.equalsIgnoreCase("checking")) {
		TimeUnit.SECONDS.sleep(2);
		ack= registerAckService.returnFeedback();
		
		return ack;
	}

	
	
	@CrossOrigin(origins = "http://ui:3000", maxAge = 3600)
	@RequestMapping(value="/dataretrieval", method = RequestMethod.POST, consumes = "application/json")
	public String dataRetrival(@RequestBody String message) throws InterruptedException {
		kafkaTemplate.send(TOPIC_DATARETRIVE_MESSAGE,message);
		//Just push the data to message broker for dataa
		
		String ack= dataAckService.returnFeedback();
		System.out.println("ack is"+ack);
		System.out.println("condition is"+ ack.equalsIgnoreCase("failure"));
	//	while(ack.equalsIgnoreCase("checking")) {
		TimeUnit.SECONDS.sleep(2);
		ack= dataAckService.returnFeedback();
		
		return ack;
	}
	

	@CrossOrigin(origins = "http://ui:3000", maxAge = 3600)
	@RequestMapping(value = "/sessionmgmt"/* , method = RequestMethod.POST, consumes = "application/json" */)
	public String sessionManagement(/* @RequestBody String sessionDetails */)
			throws InterruptedException, URISyntaxException, JSONException, ExecutionException {
		
	
		SessionRequestTemplate request= new SessionRequestTemplate("shivali","shiv","shiv","shiv");
		
		System.out.println(" Entered the session mgmt method in api gateway "+request); // message will be username
		System.out.println(kafkaTemplateSession.send(TOPIC_SESSION_MESSAGE, request));
		
		String ack = sessionAckService.returnFeedback();
		System.out.println("ack is" + ack);
		System.out.println("condition is" + ack.equalsIgnoreCase("failure"));
		TimeUnit.SECONDS.sleep(10);
		ack = sessionAckService.returnFeedback();

		return ack;
	}
	
	
	@RequestMapping("/")
	public String showLoginForm() {

		return "Login.jsp";
	}
}
