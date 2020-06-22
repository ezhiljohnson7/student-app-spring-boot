package com.johnsontraining.studentapplication.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	/**
	 * Docket to set all the swagger configurations based on which the swagger is
	 * started.
	 * 
	 * @return docket bean.
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.johnsontraining.studentapplication"))
//				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.pathMapping("/");
//				.apiInfo(apiInfo());
	}

//	/**
//	 * API information which is displayed in the swagger UI.
//	 * 
//	 * @return apiInfo, such as version, contact, etc.
//	 */
//	private ApiInfo apiInfo() {
//
//		return new ApiInfo("Student Application",
//				"Sample application to create, read, update and delete students.", "1.0",
//				"Terms of Service", new Contact("Johnson", "http://localhost:8080", "johnsontraining7@gmail.com"),
//				"Apache License", "https://www.apache.org/license.html", new ArrayList<VendorExtension>());
//	}
}