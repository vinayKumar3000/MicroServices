package com.example.cloud.springsloudapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import reactor.core.publisher.Mono;

//Pre filter --> less the order first executed
//Post Filter --> more the order first executed

@Configuration
public class PreAndPostFilterConfiguration {

  final Logger logger = LoggerFactory.getLogger(PreAndPostFilterConfiguration.class);

  @Order(1)
  @Bean
  public GlobalFilter secondPreFilter() {

    return (exchange, chain) -> {

      logger.info("Second Pre Filter");

      return chain.filter(exchange).then(Mono.fromRunnable(() -> {

        logger.info("Second Post Filter");

      }));
    };
  }

  @Order(2)
  @Bean
  public GlobalFilter thirdPreFilter() {

    return (exchange, chain) -> {

      logger.info("third Pre Filter");

      return chain.filter(exchange).then(Mono.fromRunnable(() -> {

        logger.info("third Post Filter");

      }));
    };
  }

  @Order(3)
  @Bean
  public GlobalFilter forthPreFilter() {

    return (exchange, chain) -> {

      logger.info("forth Pre Filter");

      return chain.filter(exchange).then(Mono.fromRunnable(() -> {

        logger.info("forth Post Filter");

      }));
    };
  }

}
