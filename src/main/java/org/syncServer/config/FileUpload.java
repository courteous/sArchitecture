package org.syncServer.config;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.embedded.MultiPartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUpload {
	
	
	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultiPartConfigFactory factory = new MultiPartConfigFactory();
        factory.setMaxFileSize("800KB");
        factory.setMaxRequestSize("800KB");
        return factory.createMultipartConfig();
    }

}

