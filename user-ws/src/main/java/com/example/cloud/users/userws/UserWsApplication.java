package com.example.cloud.users.userws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//TASK : ZUUL GATEWAY Spring Boot Initializer
//To use Zuul Api We need to Add zuul in pom.xml and annotate with @EnableZuulProxy in new Repository
//Now we can access registered web services with http://localhost:8081/USER-WS/users/status-check

//mvn spring-boot:run -Dspring-boot.run.arguments= --spring.application.instance_id=vinay
//mvn spring-boot:run -Dspring-boot.run.arguments= "--spring.application.instance_id=vinay2 --server.port=8999"

@SpringBootApplication
@EnableDiscoveryClient
public class UserWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserWsApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
