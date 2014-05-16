package org.syncServer.Controller.REST;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Controller
public class WelcomeController {

	@Value("${application.message:Hello World}")
	private String message = "Hello World";
	
	

	@RequestMapping(value="/" , method=RequestMethod.GET )
	public String welcome(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		ServletContext serletContext = req.getSession().getServletContext();
		
        WebContext ctx = new WebContext(req, resp, serletContext, req.getLocale());
	    ctx.setVariable("today", Calendar.getInstance());
	    ctx.setVariable("contextValue", "Store Context Value");
		req.getSession().setAttribute("sessionValue", "Store Session Value");
		
		//get the current user and put it into the model
		
		model.put("time", new Date());
		model.put("message", message);
		model.put("user", "TestUseDefinedInWelcomeController");
		return "welcome";
	}


	@RequestMapping(value = "/test1", method=RequestMethod.GET)
	public String layout(){
		
		return "test1";
			
	}
	
}
