package com.zl.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {


    /**
     * 配置了2个路由规则
     * 当访问localhost:9527/guonei的时候，将会转发至https://news.baidu.com/guonei
     * 当访问localhost:9527/guonji的时候，将会转发至http://news.baidu.com/guoji
     * 以编码的方式实现路由转发，若yml配置文件里也配置了路由规则，都会生效
     * @param routeLocatorBuilder
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        return routes.route("path_route_1", r -> r.path("/guonei").uri("https://news.baidu.com/guonei"))
                .route("path_route_2", r -> r.path("/guoji").uri("http://news.baidu.com/guoji")).build();
    }
}
