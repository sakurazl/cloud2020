package com.zl.springcloud.config;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zl
 * @description: 配置类
 * @date: 2020/3/7
 * @Return
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //@LoadBalanced  若自己定义了负载规则，可注释
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
