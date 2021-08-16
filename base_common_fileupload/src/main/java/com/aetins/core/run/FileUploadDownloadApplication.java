package com.aetins.core.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author avinash
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.aetins.*"})
@EnableAutoConfiguration
public class FileUploadDownloadApplication extends SpringBootServletInitializer implements WebApplicationInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FileUploadDownloadApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(FileUploadDownloadApplication.class, args);
		System.out.println("App Started ");
	}

}
