package com.aditazz.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(scanBasePackages= {"com.aditazz.*"})
@Configuration
@EnableWebMvc
public class AditazzApplication {

	public static void main(String[] args) {
		SpringApplication.run(AditazzApplication.class, args);
	}

}

