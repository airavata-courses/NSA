package com.example.demo;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.common.requests.ApiVersionsResponse;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ApiGatewayResource {

	private static final String TOPIC_LOGIN_MESSAGE= "KafkaLoginMessage";
	private static final String TOPIC_REGISTER_MESSAGE= "KafkaRegisterMessage";
	private static final String TOPIC_DATARETRIVE_MESSAGE= "messagehandler-dataretrieval";
	private static final String TOPIC_SESSION_MESSAGE = "ui-sessionhistory";
	private static final String TOPIC_SESSION_LOGIN_MESSAGE = "login-sessionmgmt";
	
	
	Logger logger = LoggerFactory.getLogger(ApiGatewayResource.class);
	
	@Autowired
	KafkaListenerRegisterFeedback registerAcknowledgement;
	@Autowired
	KafkaListenerLoginFeedback loginAcknowledgement;
	@Autowired
	KafkaListenerDataRetrieval dataAcknowledgement;
	@Autowired
	KafkaListenerSessionFeedback sessionAcknowledgement;


	@Autowired
	KafkaTemplate<String,User> kafkaTemplateLogin;
	@Autowired
	KafkaTemplate<String,User> kafkaTemplateRegister;
	
	 @Autowired 
	 KafkaTemplate<String,SessionRequestTemplate> kafkaTemplateSession;
	 @Autowired 
	 KafkaTemplate<String,DataRetrievalTemplate> kafkaTemplateDataRetrieval;
	  
	
	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@RequestMapping(value = "/login" , method = RequestMethod.POST, consumes = "application/json")
	public String login(@RequestBody User message ) throws InterruptedException, URISyntaxException, JSONException, ExecutionException{

		System.out.println("Welcome in login : "+message );
		kafkaTemplateLogin.send(TOPIC_LOGIN_MESSAGE,message);
		SessionRequestTemplate request= new SessionRequestTemplate(message.getUserName(), "","","","","",
				"", "login");
		kafkaTemplateSession.send(TOPIC_SESSION_LOGIN_MESSAGE,request);	
		System.out.println("Entered inside the login: "+message );

		TimeUnit.SECONDS.sleep(2);
		String ack= loginAcknowledgement.returnFeedback();
		
		return ack;
	}
	
	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@RequestMapping(value="/register", method = RequestMethod.POST, consumes = "application/json")
	public String register(@RequestBody User message) throws InterruptedException, URISyntaxException, JSONException, ExecutionException {
		kafkaTemplateRegister.send(TOPIC_REGISTER_MESSAGE,message);
		System.out.println("Entered inside the register: "+message );
		TimeUnit.SECONDS.sleep(2);
		String ack= registerAcknowledgement.returnFeedback();
		
		return ack;
	}

	
	
	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@RequestMapping(value = "/dataretrieval")
	public String dataRetrival(@RequestBody DataRetrievalTemplate request) throws InterruptedException, URISyntaxException, JSONException, ExecutionException{
		
		kafkaTemplateDataRetrieval.send(TOPIC_DATARETRIVE_MESSAGE,request);
		logger.debug("Request message to data retrieval service is: "+request);
		
		String dataRetrievalAck= dataAcknowledgement.returnFeedback();
		TimeUnit.SECONDS.sleep(2);
		dataRetrievalAck= dataAcknowledgement.returnFeedback();
		logger.debug("Acknowledgement received from data retrieval is "+dataRetrievalAck);
		
		return dataRetrievalAck;
	}
	

	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@RequestMapping(value = "/sessionmgmt")

	public SessionRequestTemplate sessionManagement( @RequestBody SessionRequestTemplate request )
			throws InterruptedException, URISyntaxException, JSONException, ExecutionException {
		System.out.println("Inside session-mgmt and following is the request: "+request);
		kafkaTemplateSession.send(TOPIC_SESSION_MESSAGE,request);
		System.out.println("Request message to session management service is: "+request);

		
		//Acknowledgment received from session retrieval service
		SessionRequestTemplate sessionAck = sessionAcknowledgement.returnFeedback();
		TimeUnit.SECONDS.sleep(10);
		sessionAck = sessionAcknowledgement.returnFeedback();

		return sessionAck;
	}
	
	
	@RequestMapping("/")
	public String showLoginForm() {

		return "Login.jsp";
	}
}