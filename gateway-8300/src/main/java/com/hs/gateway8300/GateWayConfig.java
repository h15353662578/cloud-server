package com.hs.gateway8300;

import org.springframework.context.annotation.Configuration;
import com.hs.gateway8300.IpFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 华生
 * 2020/12/20
 */

@Configuration
public class GateWayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        RouteLocator build = builder.routes()
                //设置拦截接口
                .route(r -> r.path("/api/user/register/**")
                        //去除路径上第一个参数/api
                        .filters(f -> f.stripPrefix(1)
                                //自定义的filetr
                                .filter(new IpFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("lb://lagou-service-user8080")
                        .order(0)
                        .id("lagou-service-user80801")
                )
                .build();
        return build;
    }


}
