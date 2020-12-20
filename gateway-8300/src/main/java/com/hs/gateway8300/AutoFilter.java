package com.hs.gateway8300;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;

/**
 * @author 华生
 * 2020/12/20
 */
@Configuration
public class AutoFilter {

    @Bean
    @Order(10)
    public GlobalFilter b() {
        return (exchange, chain) -> {
            HttpHeaders httpHeaders = exchange.getResponse().getHeaders();
            //返回数据格式
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            //业务逻辑

            return chain.filter(exchange);
        };
    }

}
