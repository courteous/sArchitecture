package org.syncServer.config;

import java.util.Arrays;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.SpringServletContainerInitializer;

@Configuration
@Profile("HTTPOnly")
public class TomcatEmbededHTTPOnly extends SpringServletContainerInitializer{

	


//	final String ciphers = "ECDHE-RSA-AES256-GCM-SHA384,ECDHE-RSA-AES128-GCM-SHA256,DHE-RSA-AES256-GCM-SHA384,DHE-RSA-AES128-GCM-SHA256

	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		
		
		
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(8080);
		
	
		factory.setTomcatContextCustomizers(
				Arrays.asList (
						new TomcatContextCustomizer[]{ 
								tomcatContextCustomizer() 
								} 
							)
						);
		
		factory.addConnectorCustomizers( new TomcatConnectorCustomizer() {
			@Override
			public void customize(Connector con) {
				 	
				con.setScheme("http");
				con.setSecure(false);
				con.setPort(8080);
				System.out.println("INIT HTTP");
				
				}
			}
		);
		
	
		System.out.println("TOMCAT CUSTOME SETTINGS INITILIZED");
		
	    return factory;
	}


	
	@Bean 
	public TomcatContextCustomizer tomcatContextCustomizer() {
		System.out.println("TOMCATCONTEXTCUSTOMIZER INITILIZED");
		return new TomcatContextCustomizer() {
			
			@Override
			public void customize(Context context) {
				// TODO Auto-generated method stub
				context.addServletContainerInitializer(new WsSci(), null);
				
				
			}
		};
	}
		
	
}
