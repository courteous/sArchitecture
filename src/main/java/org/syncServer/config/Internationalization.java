package org.syncServer.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class Internationalization {

	
	//the parameter "language" is what the interceptor is 'intercepting' from the html page , i.e. the language in the html page
	//must match with this one.
	@Bean
	public org.springframework.web.servlet.i18n.LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor ior = new LocaleChangeInterceptor();
		ior.setParamName("Language");
		System.out.println("INTERCEPTOR INTERNATIONALIZATION  CALLED");
	    return ior;
	}
	
	@Bean
	public org.springframework.web.servlet.i18n.SessionLocaleResolver localeResolver() {
		SessionLocaleResolver lr = new SessionLocaleResolver();
		lr.setDefaultLocale(defaultLocale());
		System.out.println("LOCALE_RESOLVER INTERNATIONALIZATION  CALLED");
	    return lr;
	}
	
	@Bean
	public Locale defaultLocale() {
		Locale locale = new Locale("en");
		System.out.println("DEFAULT LOCALE CALLED");
		return locale;	
	}
	
	
	
	//this bean is connected hold the file where all the messages are stored i.e. "messages"
	@Bean
	org.springframework.context.support.ResourceBundleMessageSource messageSource(){
		org.springframework.context.support.ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(false);
		return messageSource;
	}
	
}
