package com.mobilebuilds.mobilebuilds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EntityScan(basePackages = "com.mobilebuilds.model")
@ComponentScan(basePackages = {"com.*"})
@EnableJpaRepositories(basePackages = {"com.mobilebuilds.repository"})
@EnableWebMvc
public class MobilebuildsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilebuildsApplication.class, args);
	}

}
