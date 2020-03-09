package com.zl.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  //使用consul或zookeeper作为注册中心时注册服务
public class ZkPaymentMain8004 {


    public static void main(String[] args) {
        SpringApplication.run(ZkPaymentMain8004.class,args);
    }
}
