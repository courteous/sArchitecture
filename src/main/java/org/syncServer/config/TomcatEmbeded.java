package org.syncServer.config;

import java.io.FileNotFoundException;
import java.util.Arrays;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.websocket.server.WsSci;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.SpringServletContainerInitializer;


@Configuration
@Profile("Production")
public class TomcatEmbeded extends SpringServletContainerInitializer {
	
	
	final int http_port = 8080;
	final int https_port = 8888;
	final int https_port_internal = 9999;
	final String keystoreFile = "/home/tito/Projects/syncServer/Server/certificate/sync.keystore";
	final String keystorePass = "changeit";
	final String keystoreType = "JKS";
	final String keystoreProvider = "SUN";
	final String keystoreAlias = "tomcat";
	final String https_scheme = "https";
	final String http_scheme = "http";
//	final String ciphers = "TLS_RSA_WITH_AES_128_CBC_SHA";
	final String ciphers = "ECDHE-RSA-AES256-GCM-SHA384,ECDHE-RSA-AES128-GCM-SHA256,DHE-RSA-AES256-GCM-SHA384,DHE-RSA-AES128-GCM-SHA256";   


	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		
		
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(http_port);
		
		

	   final TomcatConnectorProperties tomcatProperties = new TomcatConnectorProperties();
		
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
				 	Http11NioProtocol proto = (Http11NioProtocol) con.getProtocolHandler();

				 	try {
				 		
				 		con.setPort(https_port);
				        con.setSecure(true);
				        con.setScheme("https");
				        con.setAttribute("keyAlias", keystoreAlias);
				        con.setAttribute("keystorePass", keystorePass.toString());
		                try {
		                	con.setAttribute("keystoreFile", ResourceUtils.getFile(keystoreFile).getAbsolutePath());
		                } catch (FileNotFoundException e) {
		                    throw new IllegalStateException("Cannot load keystore", e);
		                }
		                
		                con.setAttribute("clientAuth", "false");
		                con.setAttribute("sslProtocol", "TLS");
		                con.setAttribute("SSLEnabled", true);
				        
					 	proto.setSSLEnabled(true);
				        proto.setKeystoreFile(keystoreFile);
				        proto.setKeystorePass(keystorePass);
				        proto.setKeystoreType(keystoreType);
				        proto.setCiphers(tomcatProperties.getCiphers());
				        proto.setProperty("keystoreProvider", keystoreProvider.toString());
				        proto.setKeyAlias(keystoreAlias);
						
					} catch (Exception ex) {
				        throw new IllegalStateException("can't access keystore: [" + "keystore"
				                + "] or truststore: [" + "keystore" + "]", ex);
					}
				 	System.out.println("INIT HTTPS");
				
				}
			}
		);
		
		
		factory.addAdditionalTomcatConnectors(httpConnector());
		factory.addAdditionalTomcatConnectors(httpsConnectorInternal());
// 		factory.addErrorPages(new ErrorPage(HttpStatus.404, "/notfound.html");
	    System.out.println("TOMCAT CUSTOME SETTINGS INITILIZED");
		
	    return factory;
	}

	
	private Connector httpConnector() {
	    Connector connector = new Connector();
	    connector.setScheme(this.http_scheme);
        connector.setSecure(true);
        connector.setPort(this.http_port);
        System.out.println("INIT port HTTP");
        return connector;
	}
	
	
	private Connector httpsConnectorInternal() {
		Connector connector = new Connector();
		Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();
		
		try {
			
			connector.setScheme(this.https_scheme);
		    connector.setPort(this.https_port_internal);
		    connector.setSecure(true);
	        
		    connector.setAttribute("keyAlias", keystoreAlias);
		    connector.setAttribute("keystorePass", keystorePass.toString());
	        try {
	        	connector.setAttribute("keystoreFile", ResourceUtils.getFile(keystoreFile).getAbsolutePath());
	        } catch (FileNotFoundException e) {
	            throw new IllegalStateException("Cannot load keystore", e);
	        }
	        
	        connector.setAttribute("clientAuth", "false");
	        connector.setAttribute("sslProtocol", "TLS");
	        connector.setAttribute("SSLEnabled", true);
	        
		 	proto.setSSLEnabled(true);
	        proto.setKeystoreFile(keystoreFile);
	        proto.setKeystorePass(keystorePass);
	        proto.setKeystoreType(keystoreType);
	        proto.setProperty("keystoreProvider", keystoreProvider.toString());
	        proto.setKeyAlias(keystoreAlias);
			
		} catch (Exception ex) {
	        throw new IllegalStateException("can't access keystore: [" + "keystore"
	                + "] or truststore: [" + "keystore" + "]", ex);
		}
		
	    
        System.out.println("INIT port HTTPS INTERNAL");
        return connector;
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
