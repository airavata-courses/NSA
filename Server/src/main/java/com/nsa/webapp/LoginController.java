package com.nsa.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
public class LoginController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	UserRepository userRepository;
	
	@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
	@RequestMapping("/login")
	public String callLoginMethod(@RequestBody String input) throws JsonMappingException, JsonProcessingException {
		//String status=restTemplate.getForObject("http://localhost:8082/login/shivali", String.class);
//		RestTemplate template = new RestTemplate();
//		template
	//	System.out.println("user is"+user.getFirstName());
		//Code for login validation with user
		
		//userRepository.save(user);
		/*
		 * String password= userRepository.findByUserName(user.getFirstName());
		 * System.out.println("Password is "+password);
		 */
		//if(user)
		
		System.out.println("Input is"+input);
        User user = new User();
        user.setEmail("default@gmail.com");
        
        JsonObject jsonObject = new JsonParser().parse(input).getAsJsonObject();
        System.out.println(jsonObject.get("name").toString());
        
        //System.out.println("User model created"+user.getFirstName());

		
		return "input reached till server";
	}

}
