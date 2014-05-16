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
@EnableWebMvc
@ComponentScan(basePackages = "org.syncServer")
public class MVCConfig  extends WebMvcConfigurerAdapter{
	
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        
    }
	
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
      stringConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
      converters.add(stringConverter);

    }
    

	
	
//	
//    Template cache is true by default. Set to false if you want
//    templates to be automatically updated when modified.    
    
//	@Bean
//	public ServletContextTemplateResolver templateResolver() {
//		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
////		resolver.setPrefix("/WEB-INF/templates/");
//		//resolver.setPrefix("/resources/templates/");
//    	resolver.setPrefix("/resources/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode("HTML5");
//	    resolver.setCacheable(false);
//	    return resolver;
//	}
//	

//	@Bean
//	public org.thymeleaf.spring4.SpringTemplateEngine templateEngine(){
//		org.thymeleaf.spring4.SpringTemplateEngine engin = new  SpringTemplateEngine();
//		engin.setTemplateResolver(templateResolver());
//		engin.addDialect(new LayoutDialect());  //this will is used for Thymeleaf Hierarchical-style layouts
//		return engin;
//	}
//	
//	
//	@Bean
//	public org.thymeleaf.spring4.view.ThymeleafViewResolver thymeleafViewResolver(){
//		org.thymeleaf.spring4.view.ThymeleafViewResolver resolver = new  ThymeleafViewResolver();
//		resolver.setTemplateEngine(templateEngine());
//		resolver.setCharacterEncoding("UTF-8");
//		resolver.setContentType("text/html; charset=UTF-8");
//		resolver.setCache(false);
//		resolver.setOrder(1);
//
//		return resolver;
//	}
//	
  
		
	@Override
  	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	  	registry.addResourceHandler("/pdfs/**").addResourceLocations("/pdfs/").setCachePeriod(31556926);
	  	registry.addResourceHandler("/pdfs/**").addResourceLocations("/pdfs/").setCachePeriod(0);
	  	registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(0);
	  	registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(0);
	  	registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(0);
 
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
