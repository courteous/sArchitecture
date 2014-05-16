package org.syncServer.core;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@ComponentScan(basePackages = "org.syncServer.*")
@Configuration
@EnableWebSocketMessageBroker
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {

	@Bean
	public ServletRegistrationBean dispatcherRegistration() {

		System.out.println("SERVLET REGISTRATION");
		ServletRegistrationBean registration = new ServletRegistrationBean(
				dispatcherServlet());

		System.out.println("SERVLET REGISTERED NAME is: "
				+ registration.getServletName().toString());
		registration.addUrlMappings("/");

		return registration;
	}


	
	@Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		System.out.println("DISPATCHER INIT");

		return new DispatcherServlet();

	}

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
				System.out.println("SERVLET CONTEXT initialized");
				logger.info("SERVLET CONTEXT initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				System.out.println("SERVLET CONTEXT initialized");
				logger.info("SERVLET CONTEXT destroyed");
			}
		};
	}



	public static void main(String[] args) {

		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("PRINTING the beans provided by Spring Boot:");
		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}

		System.out.println("PRINTING SELECTED VIEW RESOLVERS ORDER:");
		Map<String, ViewResolver> resolvers = ctx
				.getBeansOfType(org.springframework.web.servlet.ViewResolver.class);

		for (Entry<String, ViewResolver> entry : resolvers.entrySet()) {
			System.out.println("key=" + entry.getKey() + ", value="
					+ entry.getValue());

		}

		// InternalResourceViewResolver ire =
		// ctx.getBean(InternalResourceViewResolver.class);
		// int order = ire.getOrder();
		// System.out.println("InternalResourceViewResolver Order is:" + order);

		// ThymeleafViewResolver tre = ctx.getBean(ThymeleafViewResolver.class);
		// int torder = tre.getOrder();
		// System.out.println("ThymeleafViewResolver Order is:" + torder);

		System.out.println("Start Tests");
		

		

	}

}
