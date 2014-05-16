package org.syncServer.config;


import javax.net.ssl.KeyManagerFactory;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class TomcatConnectorProperties {
	private String algorithm = KeyManagerFactory.getDefaultAlgorithm();
	final boolean clientAuth = false;
	final boolean sslEnabled = false;
    
	final String keystoreFile = "/home/tito/Projects/syncServer/Server/certificate/sync.keystore";
	final String keystorePass = "changeit";
	final String keystoreType = "JKS";
	final String keystoreProvider = "SUN";
	final String keystoreAlias = "tomcat";
    
    final String protocol = "TLS";
//    private String ciphers = "TLS_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_RSA_WITH_AES_128_CBC_SHA,TLS_DHE_DSS_WITH_AES_128_CBC_SHA,SSL_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA,SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA";
    final String ciphers = "TLS_RSA_WITH_AES_128_CBC_SHA";
    final String https_scheme = "https";
    final String http_scheme = "http";
    final int http_port = 8080;
    final int http_port_internal = 7777;
    
    final int https_port = 8888;
    final int https_port_internal = 9999;
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public boolean isClientAuth() {
		return clientAuth;
	}
	public boolean isSslEnabled() {
		return sslEnabled;
	}
	public String getKeystoreFile() {
		return keystoreFile;
	}
	public String getKeystorePass() {
		return keystorePass;
	}
	public String getKeystoreType() {
		return keystoreType;
	}
	public String getKeystoreProvider() {
		return keystoreProvider;
	}
	public String getKeystoreAlias() {
		return keystoreAlias;
	}
	public String getProtocol() {
		return protocol;
	}
	public String getCiphers() {
		return ciphers;
	}
	public String getHttps_scheme() {
		return https_scheme;
	}
	public String getHttp_scheme() {
		return http_scheme;
	}
	public int getHttp_port() {
		return http_port;
	}
	public int getHttp_port_internal() {
		return http_port_internal;
	}
	public int getHttps_port() {
		return https_port;
	}
	public int getHttps_port_internal() {
		return https_port_internal;
	}
    
    
    

}
