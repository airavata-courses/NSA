package com.nsa.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
}
