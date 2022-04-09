package com.example.cloud.springsloudapigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class CustomPostFilter implements GlobalFilter, Ordered {

  final Logger logger = LoggerFactory.getLogger(CustomPostFilter.class);

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

    return chain.filter(exchange).then(Mono.fromRunnable(() -> {
      logger.info("Custom Post Filter");
    }));
  }

  @Override
  public int getOrder() {
    // less the number last the execution
    return 0;
  }

}
