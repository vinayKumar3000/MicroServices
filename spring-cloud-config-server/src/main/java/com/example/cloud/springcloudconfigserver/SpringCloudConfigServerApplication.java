package com.example.cloud.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//config server gets higher priority than local application.properties file
//add spring-cloud-config-server pom.xml
//add annotation 
//add git configuration in application.properties 
//In the client side
//add spring-cloud-starter-config and spring-cloud-starter-bootstrap pom.xml
//add spring.config.import=optional:configserver:http://localhost:8012 in application.properties
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudConfigServerApplication.class, args);
	}

}
