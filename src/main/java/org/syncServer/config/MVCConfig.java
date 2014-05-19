package org.syncServer.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@Configuration
@ComponentScan(basePackages = "org.syncServer")
public class MVCConfig  extends WebMvcConfigurerAdapter{
	
	private static final Charset UTF8 = Charset.forName("UTF-8");
	

	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
      stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
      converters.add(stringConverter);

    }
    
	
	@Bean
	public LocalValidatorFactoryBean validator(){
	return new LocalValidatorFactoryBean();
}

	
	@Bean
	public MethodValidationPostProcessor mvpp() {
	  MethodValidationPostProcessor mvpp = new MethodValidationPostProcessor();
	  mvpp.setValidator((javax.validation.Validator) validator());
	  return mvpp;
	}
	
}
