package com.example.cloud.springsloudapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
TASK:SPRING-CLOUD-API-GATEWAY
Add 1. web flux 2. api gateway

GET - http://localhost:8082/USER-WS/users/status-check
spring.cloud.gateway.discovery.locator.enabled=true
GET - http://localhost:8082/user-ws/users/status-check
spring.cloud.gateway.discovery.locator.lower-case-service-id=true

Routes : GET - http://localhost:8082/users/status-check

spring.cloud.gateway.routes[0].filters[1]=RewritePath=/user-ws/users/status-check,/users/status-check
RewritePath : GET - http://localhost:8082/user-ws/users/status-check

*/

@SpringBootApplication
@EnableDiscoveryClient
public class SpringSloudApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSloudApiGatewayApplication.class, args);
	}

}
