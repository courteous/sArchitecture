package org.syncServer.Controller.REST;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.context.WebContext;

@Controller
public class IndexController {

	
		
	@RequestMapping(value = "/layout", method=RequestMethod.GET)
	public String layout(){
		
		return "layout";
			
	}
	
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public String registration(){
		
		System.out.println("INDEX has been hit");

		return "register";
			
	}
	
	@RequestMapping(value = "/index", method=RequestMethod.GET)
	public String indexPage(){
		
		System.out.println("INDEX has been hit");

		return "index";
			
	}
	
	@RequestMapping(value = "/wsTest", method=RequestMethod.GET)
	public String websocketTest(){
		
		System.out.println("INDEXS WEBSOCKET PAGE");

		return "websocketTest";
			
	}
	
	@RequestMapping(value = "/test", method=RequestMethod.GET)
	public String manualTests(){
		return "test";
			
	}
	
}
