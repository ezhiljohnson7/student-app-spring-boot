package com.johnsontraining.studentapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class StudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}
	
//	@Bean
//	public ServletWebServerFactory servletContainer() {
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//	      @Override
//	      protected void postProcessContext(Context context) {
//	        SecurityConstraint securityConstraint = new SecurityConstraint();
//	        securityConstraint.setUserConstraint("CONFIDENTIAL");
//	        SecurityCollection collection = new SecurityCollection();
//	        collection.addPattern("/*");
//	        securityConstraint.addCollection(collection);
//	        context.addConstraint(securityConstraint);
//	      }
//	    };
//	   
//	  tomcat.addAdditionalTomcatConnectors(redirectConnector());
//	  return tomcat;
//	}
//	 
//	private Connector redirectConnector() {
//	  Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//	  connector.setScheme("http");
//	  connector.setPort(8081);
//	  connector.setSecure(false);
//	  connector.setRedirectPort(8443);
//	   
//	  return connector;
//	}

}
